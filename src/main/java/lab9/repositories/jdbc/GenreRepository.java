package lab9.repositories.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lab9.entities.Genre;

class GenreRepository extends JDBCRepository<Genre> {
	@Override
	protected String getCreateStatement() {
		return "INSERT INTO genres (name) VALUES (?)";
	}

	@Override
	protected String getFindByNameStatement() {
		return "SELECT id, name FROM genres WHERE name = ?";
	}

	@Override
	protected String getFindByIdStatement() {
		return "SELECT id, name FROM genres WHERE id = ?";
	}

	@Override
	protected void bindCreateStatement(PreparedStatement statement, Genre item) throws SQLException {
		statement.setString(1, item.getName());
	}

	@Override
	protected Genre extractFromFindResults(ResultSet results) throws SQLException {
		Genre genre = new Genre();
		genre.setId(results.getInt("id"));
		genre.setName(results.getString("name"));
		return genre;
	}
}
