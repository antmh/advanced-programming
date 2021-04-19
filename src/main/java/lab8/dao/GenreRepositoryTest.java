package lab8.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lab8.model.Genre;

class GenreRepositoryTest {
	private GenreRepository repository = new GenreRepository();

	@Test
	void delete() {
		var genre = new Genre();
		genre.setName("name");
		repository.delete(genre);
	}

	@Test
	void update() {
		var genre = new Genre();
		genre.setName("name");
		repository.create(genre);
		genre.setName("new name");
		repository.update(genre);
		genre = repository.findById(genre.getId()).get();
		assertEquals(genre.getName(), "new name");
		repository.delete(genre);
	}
}
