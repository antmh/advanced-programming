package lab9.repositories.jpa;

import lab9.entities.Movie;

class MovieRepository extends JPARepository<Movie> {
	@Override
	protected String getPrefix() {
		return "movie";
	}

	@Override
	protected void persist(Movie item) {
		for (var genre : item.getGenres()) {
			entityManager.persist(genre);
		}
		for (var chartEntry : item.getChartEntries()) {
			entityManager.persist(chartEntry);
		}
		entityManager.persist(item);
	}
}
