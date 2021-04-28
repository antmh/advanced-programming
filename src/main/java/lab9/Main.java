package lab9;

import java.util.Date;

import lab9.entities.Movie;
import lab9.repositories.FactoryProvider;

public class Main {
	public static void main(String[] args) {
		var factory = FactoryProvider.createFactory();
		var movieRepository = factory.createMovieRepository();
		var movie = new Movie();
		movie.setTitle("title");
		movie.setDate(new Date());
		movieRepository.create(movie);
		System.out.println(movieRepository.findByName("title").get(0).getTitle());
	}
}