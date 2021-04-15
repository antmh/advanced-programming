package lab8.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

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
        movie = repository.findByTitle("movie title").get();
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
        movie = repository.findByTitle("movie title").get();
        movie.setTitle("new title");
        repository.update(movie);
        movie = repository.findById(movie.getId().get()).get();
        assertEquals(movie.getTitle(), "new title");
        repository.delete(movie);
    }
}
