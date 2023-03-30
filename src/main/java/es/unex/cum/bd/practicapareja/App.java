package es.unex.cum.bd.practicapareja;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import es.unex.cum.bd.practicapareja.model.Address;

public class App {

    private Connection connection;
    private Scanner scanner;

    private App() {
        String url = "jdbc:sqlserver://localhost:1433";
        String username = "sa";
        String password = "GIT21psetraki";
        String sqlFilePath = "src/main/resources/es/unex/cum/bd/practicapareja/sql/tables.sql";

        try {
            // Connect to the MySQL database server using a try-with-resources statement
            connection = DriverManager.getConnection(url, username, password);
            try (Statement stmt = connection.createStatement()) {

                // Read the SQL script file using a try-with-resources statement
                try (BufferedReader reader = new BufferedReader(new FileReader(sqlFilePath))) {
                    String line;
                    StringBuilder sb = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        sb.append("\n");
                    }
                    String sqlScript = sb.toString();

                    // Execute the SQL commands to create the tables
                    stmt.executeUpdate(sqlScript);
                }
                System.out.println("La base de datos y las tablas han sido creadas correctamente");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        scanner = new Scanner(System.in);
    }

    
    private void performQuery() throws SQLException{
        Address.addAddress(connection, "C.Almorchón 13X");
        Address.addAddress(connection, "Avd. Eugennio Hermoso, 11");
        Address.addAddress(connection, "Avd. Del Peral, 1");
        Address.addAddress(connection, "C. Santa Eulalia, 8");

        for (Address address : Address.getAddresses(connection)) {
            System.out.println(address.getId() + " : " + address.getDenomination());
        }

        Address.deleteAddress(connection, 2);
        
        for (Address address : Address.getAddresses(connection)) {
            System.out.println(address.getId() + " : " + address.getDenomination());
        }
    }

    public static void main(String[] args) throws SQLException {
        App app = new App();
        app.performQuery();

    }
}
