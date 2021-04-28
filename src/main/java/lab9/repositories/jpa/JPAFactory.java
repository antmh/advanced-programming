package lab9.repositories.jpa;

import lab9.entities.Chart;
import lab9.entities.Genre;
import lab9.entities.Movie;
import lab9.entities.Person;
import lab9.repositories.AbstractFactory;
import lab9.repositories.Repository;

public class JPAFactory extends AbstractFactory {
	@Override
	public Repository<Genre> createGenreRepository() {
		return new GenreRepository();
	}

	@Override
	public Repository<Movie> createMovieRepository() {
		return new MovieRepository();
	}

	@Override
	public Repository<Person> createPersonRepository() {
		return new PersonRepository();
	}

	@Override
	public Repository<Chart> createChartRepository() {
		return new ChartRepository();
	}
}
