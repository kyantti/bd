package es.unex.cum.bd.practicapareja.model.database;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    
    private static String url = "jdbc:sqlserver://localhost:1433;databaseName=Proyectos";
    private static String username = "SA";
    private static String password = "<GIT21psetraki>";

	private Database() {
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
 
	public static void closeConnection(Connection connection) throws SQLException {
		connection.close();
	}

	public static void closeStatement(Statement statement) throws SQLException {
		statement.close();
	}

	public static void closePreparedStatement(PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.close();
	}

	public static void closeResultSet(ResultSet resultSet) throws SQLException {
		resultSet.close();
	}
}
