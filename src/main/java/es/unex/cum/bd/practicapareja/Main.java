package es.unex.cum.bd.practicapareja;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlAddressDao;
import es.unex.cum.bd.practicapareja.model.dao.mssql.MssqlProjectDao;
import es.unex.cum.bd.practicapareja.model.database.Database;
import es.unex.cum.bd.practicapareja.model.entities.Address;
import es.unex.cum.bd.practicapareja.model.entities.Project;

public class Main {

    private Scanner scanner;

    private Main() {
        scanner = new Scanner(System.in);
    }

    
    private void performQuery() throws SQLException{
        Database.setUsername("SA");
        Database.setPassword("<GIT21psetraki>");
        for (Project project : projectDao.getAll()) {
            System.out.println(project.toString());
        }

    }

    public static void main(String[] args) throws SQLException {
        Main app = new Main();
        app.performQuery();

    }
}
