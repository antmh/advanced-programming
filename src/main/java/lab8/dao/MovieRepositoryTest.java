package lab8.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

import lab8.model.Genre;
import lab8.model.Movie;

class MovieRepositoryTest {
	private MovieRepository repository = new MovieRepository();

	@Test
	void delete() {
		var movie = new Movie();
		movie.setDate(new Date());
		movie.setDuration(120);
		movie.setTitle("movie title");
		movie.setScore(5);
		repository.create(movie);
		repository.delete(movie);
	}

	@Test
	void update() {
		var movie = new Movie();
		movie.setDate(new Date());
		movie.setDuration(120);
		movie.setTitle("movie title");
		movie.setScore(5);
		repository.create(movie);
		movie.setTitle("new title");
		repository.update(movie);
		assertEquals(movie.getTitle(), "new title");
		repository.delete(movie);
	}

	@Test
	void genres() {
		var movie = new Movie();
		movie.setDate(new Date());
		movie.setDuration(120);
		movie.setTitle("movie title");
		movie.setScore(5);
		var genre = new Genre();
		genre.setName("genre");
		var genreRepository = new GenreRepository();
		genreRepository.create(genre);
		repository.create(movie);
		movie.setTitle("new title");
		movie.getGenres().add(genre);
		repository.update(movie);
		assertEquals(movie.getGenres().get(0).getName(), "genre");
		repository.delete(movie);
		genreRepository.delete(genre);
	}
}
