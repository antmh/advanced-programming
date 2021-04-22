package lab9.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import lab9.MovieManagerFactory;
import lab9.entities.Movie;
import lab9.repositories.MovieRepository;

class MovieRepositoryTest {
	static MovieRepository repository;
	
	@BeforeAll
	static void beforeAll() {
		repository = new MovieRepository();
	}
	
	@AfterAll
	static void afterAll() {
		MovieManagerFactory.getInstance().close();
	}

	@Test
	void create() {
		var movie = new Movie();
		movie.setTitle("title");
		repository.create(movie);
		var results = repository.findByTitle("title");
		assertEquals(1, results.size());
		movie = results.get(0);
		assertEquals("title", movie.getTitle());
	}
	
	@Test
	void createWithDuration() {
		var movie = new Movie();
		movie.setTitle("title2");
		movie.setDuration(120);
		repository.create(movie);
		var results = repository.findByTitle("title2");
		assertEquals(1, results.size());
		movie = results.get(0);
		assertEquals("title2", movie.getTitle());
		assertEquals(120, movie.getDuration());
	}
}
