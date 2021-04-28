package lab9.repositories.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lab9.entities.Movie;

class MovieRepository extends JDBCRepository<Movie> {
	@Override
	protected String getCreateStatement() {
		return "INSERT INTO movies (title, release_date, duration, score) VALUES (?, ?, ?, ?)";
	}

	@Override
	protected String getFindByNameStatement() {
		return "SELECT id, title, release_date, duration, score FROM movies WHERE TITLE = ?";
	}

	@Override
	protected String getFindByIdStatement() {
		return "SELECT id, title, release_date, duration, score FROM movies WHERE id = ?";
	}

	@Override
	protected void bindCreateStatement(PreparedStatement statement, Movie item) throws SQLException {
		statement.setString(1, item.getTitle());
		statement.setDate(2, new Date(item.getDate().getTime()));
		statement.setInt(3, item.getDuration());
		statement.setDouble(4, item.getScore());
	}

	@Override
	protected Movie extractFromFindResults(ResultSet results) throws SQLException {
		Movie movie = new Movie();
		movie.setId(results.getInt("id"));
		movie.setTitle(results.getString("title"));
		movie.setDate(results.getDate("release_date"));
		movie.setDuration(results.getInt("duration"));
		movie.setScore(results.getDouble("score"));
		return movie;
	}
}
