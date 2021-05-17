package social;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SocialEntityManagerFactory {
	private static EntityManagerFactory instance;

	private SocialEntityManagerFactory() {
	}

	public static EntityManagerFactory getInstance() {
		if (instance == null) {
			instance = Persistence.createEntityManagerFactory("social");
		}
		return instance;
	}
}
