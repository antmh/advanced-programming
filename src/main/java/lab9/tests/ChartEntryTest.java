package lab9.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import lab9.entities.Chart;
import lab9.entities.ChartEntry;
import lab9.entities.Movie;
import lab9.repositories.Repository;
import lab9.repositories.FactoryProvider;

class ChartEntryTest {
	static Repository<Movie> movieRepository;
	static Repository<Chart> chartRepository;

	@BeforeAll
	static void beforeAll() {
		var factory = FactoryProvider.createFactory();
		movieRepository = factory.createMovieRepository();
		chartRepository = factory.createChartRepository();
	}

	@Test
	void addMovieAndChart() {
		var movie = new Movie();
		movie.setTitle("title");
		movie.setDate(new Date());
		movieRepository.create(movie);
		var results = movieRepository.findByName("title");
		assertEquals(1, results.size());
		movie = results.get(0);
		assertEquals("title", movie.getTitle());
		var chart = new Chart();
		chart.setName("chart");
		var chartEntry = new ChartEntry();
		chartEntry.setMovie(movie);
		chartEntry.setChart(chart);
		chartEntry.setScore(9.5);
		chart.getChartsEntries().add(chartEntry);
		chart.setCreationDate(new Date());
		chartRepository.create(chart);
		var movies = movieRepository.findByName("title");
		assertEquals(1, movies.size());
	}
}
