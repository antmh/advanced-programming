package lab9.repositories;

import java.util.List;
import java.util.Optional;

import lab9.MovieManagerFactory;
import lab9.entities.Movie;

public class MovieRepository {
	public void create(Movie movie) {
		var entityManager = MovieManagerFactory.getInstance().createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(movie);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public Optional<Movie> findById(int id) {
		var entityManager = MovieManagerFactory.getInstance().createEntityManager();
		var query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.id = :id").setParameter("id", id);
		var results = query.getResultList();
		entityManager.close();
		if (results.size() == 0) {
			return Optional.empty();
		}
		return Optional.of((Movie) results.get(0));
	}
	
	public List<Movie> findByTitle(String title) {
		var entityManager = MovieManagerFactory.getInstance().createEntityManager();
		var query = entityManager.createNamedQuery("findByTitle").setParameter("title", title);
		var results = query.getResultList();
		entityManager.close();
		return results;
	}
}
