package lab8.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import lab8.MoviesConnection;
import lab8.model.Actor;
import lab8.model.Director;
import lab8.model.Person;

public class PersonRepository implements Repository<Person> {
	private static String CREATE_STATEMENT = "INSERT INTO people (name, date_of_birth) VALUES (?, ?) RETURNING id";
	private static String UPDATE_STATEMENT = "UPDATE people SET id = ?";
	private static String DELETE_STATEMENT = "DELETE FROM people WHERE id = ?";
	private static String DELETE_ACTORS_STATEMENT = "DELETE FROM actors WHERE person_id = ?";
	private static String DELETE_DIRECTORS_STATEMENT = "DELETE FROM directors WHERE person_id = ?";
	private static String INSERT_ACTOR_STATEMENT = "INSERT INTO actors (movie_id, person_id) VALUES (?, ?)";
	private static String INSERT_DIRECTOR_STATEMENT = "INSERT INTO directors (movie_id, person_id) VALUES (?, ?)";
	private static String SELECT_ID_STATEMENT = "SELECT name, date_of_birth FROM people WHERE id = ?";
	private static String SELECT_NAME_STATEMENT = "SELECT id, date_of_birth FROM people " + "WHERE name = ?";
	private static String SELECT_MOVIE_ACTED_IN_STATEMENT = "SELECT movie_id FROM actors WHERE person_id = ?";
	private static String SELECT_MOVIE_DIRECTED_STATEMENT = "SELECT movie_id FROM directors WHERE person_id = ?";

	@Override
	public void create(Person item) {
		try {
			PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(CREATE_STATEMENT);
			setStatement(statement, item);
			statement.execute();
			var results = statement.getResultSet();
			results.next();
			item.setId(results.getInt("id"));
			insertRoles(item);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(Person item) {
		try {
			PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(UPDATE_STATEMENT);
			statement.setInt(1, item.getId());
			statement.execute();
			statement = MoviesConnection.getInstance().prepareStatement(DELETE_ACTORS_STATEMENT);
			statement.setInt(1, item.getId());
			statement.execute();
			statement = MoviesConnection.getInstance().prepareStatement(DELETE_DIRECTORS_STATEMENT);
			statement.setInt(1, item.getId());
			statement.execute();
			insertRoles(item);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Person item) {
		try {
			PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(DELETE_STATEMENT);
			statement.setInt(1, item.getId());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Optional<Person> findById(int id) {
		try {
			PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(SELECT_ID_STATEMENT);
			statement.setInt(1, id);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				var person = new Person();
				person.setId(id);
				person.setDateOfBirth(results.getDate("date_of_birth"));
				person.setName(results.getString("name"));
				getRoles(person);
				return Optional.of(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	public Optional<Person> findByName(String name) {
		try {
			PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(SELECT_NAME_STATEMENT);
			statement.setString(1, name);
			ResultSet results = statement.executeQuery();
			while (results.next()) {
				var person = new Person();
				person.setName(name);
				person.setDateOfBirth(results.getDate("date_of_birth"));
				person.setId(results.getInt("id"));
				getRoles(person);
				return Optional.of(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	private void setStatement(PreparedStatement statement, Person item) throws SQLException {
		statement.setString(1, item.getName());
		statement.setDate(2, new Date(item.getDateOfBirth().getTime()));
	}

	void insertRoles(Person item) throws SQLException {
		PreparedStatement statement;
		for (var role : item.getRoles()) {
			if (role instanceof Actor) {
				statement = MoviesConnection.getInstance().prepareStatement(INSERT_ACTOR_STATEMENT);
				statement.setInt(1, ((Actor) role).getMovieActedIn().getId());
			} else if (role instanceof Director) {
				statement = MoviesConnection.getInstance().prepareStatement(INSERT_DIRECTOR_STATEMENT);
				statement.setInt(1, ((Director) role).getMovieDirected().getId());
			} else {
				throw new IllegalArgumentException("Role " + role.getClass().getName() + " not supported");
			}
			statement.setInt(2, item.getId());
			statement.execute();
		}
	}

	private void getRoles(Person item) throws SQLException {
		var movieRepository = new MovieRepository();
		PreparedStatement statement = MoviesConnection.getInstance().prepareStatement(SELECT_MOVIE_ACTED_IN_STATEMENT);
		statement.setInt(1, item.getId());
		ResultSet results = statement.executeQuery();
		while (results.next()) {
			int id = results.getInt("movie_id");
			var actor = new Actor();
			actor.setMovieActedIn(movieRepository.findById(id).get());
			item.getRoles().add(actor);
		}
		statement = MoviesConnection.getInstance().prepareStatement(SELECT_MOVIE_DIRECTED_STATEMENT);
		statement.setInt(1, item.getId());
		results = statement.executeQuery();
		while (results.next()) {
			int id = results.getInt("movie_id");
			var director = new Director();
			director.setMovieDirected(movieRepository.findById(id).get());
			item.getRoles().add(director);
		}
	}
}
