package social;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

public class PersonRepository {
	private EntityManager entityManager;

	public PersonRepository() {
		entityManager = SocialEntityManagerFactory.getInstance().createEntityManager();
	}

	public boolean create(Person person) {
		if (findByName(person.getName()).isPresent()) {
			return false;
		}
		entityManager.getTransaction().begin();
		entityManager.persist(person);
		entityManager.getTransaction().commit();
		return true;
	}

	public boolean updateName(String oldName, String newName) {
		var person = findByName(oldName);
		if (person.isEmpty()) {
			return false;
		}
		entityManager.getTransaction().begin();
		person.get().setName(newName);
		entityManager.persist(person.get());
		entityManager.getTransaction().commit();
		return true;
	}

	public boolean addFriend(String firstFriendName, String secondFriendName) {
		var firstFriend = findByName(firstFriendName);
		var secondFriend = findByName(secondFriendName);
		if (firstFriend.isEmpty() || secondFriend.isEmpty()) {
			return false;
		}
		if (!firstFriend.get().addFriend(secondFriend.get())) {
			return false;
		}
		entityManager.getTransaction().begin();
		entityManager.persist(firstFriend.get());
		entityManager.getTransaction().commit();
		return true;
	}

	public List<Person> findAll() {
		var query = entityManager.createQuery("SELECT p FROM Person p");
		return (List<Person>) query.getResultList();
	}

	public Optional<Person> findByName(String name) {
		var query = entityManager.createNamedQuery("person.find").setParameter("name", name);
		var results = query.getResultList();
		if (results.size() == 0) {
			return Optional.empty();
		}
		return Optional.of((Person) results.get(0));
	}

	public boolean delete(String name) {
		var person = findByName(name);
		if (person.isEmpty()) {
			return false;
		}
		entityManager.getTransaction().begin();
		entityManager.remove(person.get());
		entityManager.getTransaction().commit();
		return true;
	}
}
