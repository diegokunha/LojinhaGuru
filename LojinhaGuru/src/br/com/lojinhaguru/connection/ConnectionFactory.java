package br.com.lojinhaguru.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

public class ConnectionFactory {

	public Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new SQLException(e);
		}

		return (Connection) DriverManager.getConnection("jdbc:mysql://localhost/luciusfox", "root", "root");
	}

}
