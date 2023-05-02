package es.unex.cum.bd.practicapareja.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

	private static Connection connection;

	private Database(){

	}

	public static Connection getConnection(){
		return connection;
	}
	
	public static void setConnection(String url, String username, String password) throws SQLException {
		connection = DriverManager.getConnection(url, username, password);
	}
 
	public static void closeConnection() throws SQLException {
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
