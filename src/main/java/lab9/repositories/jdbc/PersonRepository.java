package lab9.repositories.jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lab9.entities.Person;

class PersonRepository extends JDBCRepository<Person> {
	@Override
	protected String getCreateStatement() {
		return "INSERT INTO people (name, date_of_birth) VALUES (?, ?, ?)";
	}

	@Override
	protected String getFindByNameStatement() {
		return "SELECT id, name, date_of_birth FROM people WHERE name = ?";
	}

	@Override
	protected String getFindByIdStatement() {
		return "SELECT id, name, date_of_birth FROM people WHERE id = ?";
	}

	@Override
	protected void bindCreateStatement(PreparedStatement statement, Person item) throws SQLException {
		statement.setString(1, item.getName());
		statement.setDate(2, new Date(item.getDateOfBirth().getTime()));
	}

	@Override
	protected Person extractFromFindResults(ResultSet results) throws SQLException {
		Person person = new Person();
		person.setId(results.getInt("id"));
		person.setName(results.getString("name"));
		person.setDateOfBirth(results.getDate("data_of_birth"));
		return person;
	}
}
