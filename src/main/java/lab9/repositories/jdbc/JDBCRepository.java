package lab9.repositories.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lab9.repositories.Repository;

abstract class JDBCRepository<T> extends Repository<T> {
	@Override
	public void create(T item) {
		try {
			PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(getCreateStatement());
			bindCreateStatement(statement, item);
			statement.execute();
		} catch (SQLException e) {
			System.err.println(e);
		}
	}

	@Override
	public List<T> findByName(String name) {
		List<T> items = new ArrayList<T>();
		try {
			PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(getFindByNameStatement());
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				items.add(extractFromFindResults(results));
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return items;
	}

	@Override
	public Optional<T> findById(int id) {
		try {
			PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(getFindByIdStatement());
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				return Optional.of(extractFromFindResults(results));
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return Optional.empty();
	}

	protected abstract String getCreateStatement();

	protected abstract void bindCreateStatement(PreparedStatement statement, T item) throws SQLException;

	protected abstract String getFindByNameStatement();

	protected abstract T extractFromFindResults(ResultSet results) throws SQLException;

	protected abstract String getFindByIdStatement();
}
