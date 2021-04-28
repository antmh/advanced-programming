package lab9.repositories.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

class MoviesConnection {
	private static Connection connection = null;

	private MoviesConnection() throws SQLException {
		DriverManager.registerDriver(new Driver());
		connection = DriverManager.getConnection("jdbc:postgresql://localhost/movies", "username", "password");
	}

	public static Connection getInstance() {
		if (connection == null) {
			try {
				new MoviesConnection();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
		return connection;
	}
}
