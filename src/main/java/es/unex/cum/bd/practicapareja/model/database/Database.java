package es.unex.cum.bd.practicapareja.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    
	private static String host;
    private static String username;
    private static String password;

	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		Database.host = host;
	}

	public  String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		Database.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Database.password = password;
	}
	
	public static Connection getConnection(String host, String username, String password) throws SQLException {
		return DriverManager.getConnection(host, username, password);
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
