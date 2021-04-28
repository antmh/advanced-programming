package lab9.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import lab9.entities.Movie;
import lab9.repositories.FactoryProvider;
import lab9.repositories.Repository;

class MovieRepositoryTest {
	static Repository<Movie> repository;
	
	@BeforeAll
	static void beforeAll() {
		var factory = FactoryProvider.createFactory();
		repository = factory.createMovieRepository();
	}

	@Test
	void create() {
		var movie = new Movie();
		movie.setTitle("title");
		movie.setDate(new Date());
		repository.create(movie);
		var results = repository.findByName("title");
		assertEquals(1, results.size());
		movie = results.get(0);
		assertEquals("title", movie.getTitle());
	}
	
	@Test
	void createWithDuration() {
		var movie = new Movie();
		movie.setTitle("title2");
		movie.setDuration(120);
		movie.setDate(new Date());
		repository.create(movie);
		var results = repository.findByName("title2");
		assertEquals(1, results.size());
		movie = results.get(0);
		assertEquals("title2", movie.getTitle());
		assertEquals(120, movie.getDuration());
	}
}
