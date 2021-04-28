package lab9.repositories;

import lab9.entities.Chart;
import lab9.entities.Genre;
import lab9.entities.Movie;
import lab9.entities.Person;

public abstract class AbstractFactory {
	public abstract Repository<Genre> createGenreRepository();

	public abstract Repository<Movie> createMovieRepository();

	public abstract Repository<Person> createPersonRepository();

	public abstract Repository<Chart> createChartRepository();
}
