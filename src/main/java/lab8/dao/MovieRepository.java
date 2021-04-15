package lab8.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import lab8.MoviesConnection;
import lab8.model.Movie;

public class MovieRepository implements Repository<Movie> {
    private static final String CREATE_STATEMENT = "INSERT INTO movies (title, release_date, duration, score) "
            + "VALUES (?, ?, ?, ?)";
    private static final String UPDATE_STATEMENT = "UPDATE movies "
            + "SET title = ?, release_date = ?, duration = ?, score = ? WHERE id = ?";
    private static final String DELETE_STATEMENT = "DELETE FROM movies WHERE id = ?";
    private static final String FIND_ID_STATEMENT = "SELECT title, release_date, duration, score FROM movies "
            + "WHERE id = ?";
    private static final String FIND_TITLE_STATEMENT = "SELECT id, release_date, duration, score FROM movies "
            + "WHERE TITLE = ?";

    @Override
    public void create(Movie item) {
        try {
            PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(CREATE_STATEMENT);
            setStatement(statement, item);
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @Override
    public void update(Movie item) {
        try {
            PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(UPDATE_STATEMENT);
            setStatement(statement, item);
            statement.setInt(5, item.getId().get());
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @Override
    public void delete(Movie item) {
        try {
            PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(DELETE_STATEMENT);
            statement.setInt(1, item.getId().get());
            statement.execute();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public Optional<Movie> findById(int id) {
        try {
            PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(FIND_ID_STATEMENT);
            statement.setInt(1, id);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                Movie movie = new Movie();
                movie.setId(Optional.of(id));
                movie.setTitle(results.getString("title"));
                movie.setDate(results.getDate("release_date"));
                movie.setDuration(results.getInt("duration"));
                movie.setScore(results.getDouble("score"));
                return Optional.of(movie);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return Optional.empty();
    }

    public Optional<Movie> findByTitle(String title) {
        try {
            PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(FIND_TITLE_STATEMENT);
            statement.setString(1, title);
            ResultSet results = statement.executeQuery();
            while (results.next()) {
                Movie movie = new Movie();
                movie.setId(Optional.of(results.getInt("id")));
                movie.setDate(results.getDate("release_date"));
                movie.setDuration(results.getInt("duration"));
                movie.setScore(results.getDouble("score"));
                return Optional.of(movie);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return Optional.empty();
    }

    private void setStatement(PreparedStatement statement, Movie item) throws SQLException {
        statement.setString(1, item.getTitle());
        statement.setDate(2, new Date(item.getDate().getTime()));
        statement.setInt(3, item.getDuration());
        statement.setDouble(4, item.getScore());
    }
}
