package lab9;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MovieManagerFactory {
	private static EntityManagerFactory instance;
	
	private MovieManagerFactory() {
	}
	
	public static EntityManagerFactory getInstance() {
		if (instance == null) {
			instance = Persistence.createEntityManagerFactory("lab9");
		}
		return instance;
	}
}
