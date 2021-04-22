package lab9;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import lab9.entities.Movie;
import lab9.entities.Person;
import lab9.entities.Genre;

public class Main {
	public static void main(String[] args) {
		EntityManagerFactory factory = MovieManagerFactory.getInstance();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		var movie = new Movie();
		movie.setTitle("title");
		var genre = new Genre();
		genre.setName("genre");
		movie.getGenres().add(genre);
		var actor = new Person();
		actor.getMoviesActedIn().add(movie);
		actor.getMoviesDirected().add(movie);
		em.persist(genre);
		em.persist(movie);
		em.persist(actor);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}