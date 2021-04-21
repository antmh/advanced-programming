package lab8.dao;

import java.util.Date;

import org.junit.jupiter.api.Test;

import lab8.model.Actor;
import lab8.model.Movie;
import lab8.model.Person;

public class PersonRepositoryTest {
	private PersonRepository repository = new PersonRepository();

	@Test
	void delete() {
		var person = new Person();
		person.setName("name");
		person.setDateOfBirth(new Date());
		repository.create(person);
		repository.delete(person);
	}

	@Test
	void actor() {
		var movieRepository = new MovieRepository();
		var movie = new Movie();
		movie.setDate(new Date());
		movie.setDuration(120);
		movie.setScore(5);
		movie.setTitle("title");
		movieRepository.create(movie);
		var person = new Person();
		person.setName("name");
		person.setDateOfBirth(new Date());
		var actor = new Actor();
		actor.setMovieActedIn(movie);
		person.getRoles().add(actor);
		repository.create(person);
		repository.delete(person);
		movieRepository.delete(movie);
	}
}
