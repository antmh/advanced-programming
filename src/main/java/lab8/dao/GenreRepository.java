package lab8.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import lab8.MoviesConnection;
import lab8.model.Genre;

public class GenreRepository implements Repository<Genre> {
	private static final String CREATE_STATEMENT = "INSERT INTO genres (name) VALUES (?) RETURNING id";
	private static final String UPDATE_STATEMENT = "UPDATE genres SET name = ? WHERE id = ?";
	private static final String DELETE_STATEMENT = "DELETE FROM genres WHERE id = ?";
	private static final String FIND_ID_STATEMENT = "SELECT name FROM genres WHERE id = ?";
	private static final String FIND_NAME_STATEMENT = "SELECT id FROM genres WHERE name = ?";

	@Override
	public void create(Genre item) {
		try {
			PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(CREATE_STATEMENT);
			statement.setString(1, item.getName());
			statement.execute();
			var results = statement.getResultSet();
			results.next();
			item.setId(results.getInt("id"));
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	@Override
	public void update(Genre item) {
		try {
			PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(UPDATE_STATEMENT);
			statement.setString(1, item.getName());
			statement.setInt(2, item.getId());
			statement.execute();
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	@Override
	public void delete(Genre item) {
		try {
			PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(DELETE_STATEMENT);
			statement.setInt(1, item.getId());
			statement.execute();
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	public Optional<Genre> findById(int id) {
		try {
			PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(FIND_ID_STATEMENT);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				Genre genre = new Genre();
				genre.setId(id);
				genre.setName(results.getString("name"));
				return Optional.of(genre);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return Optional.empty();
	}

	public Optional<Genre> findByName(String name) {
		try {
			PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(FIND_NAME_STATEMENT);
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				Genre genre = new Genre();
				genre.setId(results.getInt("id"));
				genre.setName(name);
				return Optional.of(genre);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return Optional.empty();
	}
}
