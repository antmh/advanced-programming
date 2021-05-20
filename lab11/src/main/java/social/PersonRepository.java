package social;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import exceptions.FriendAlreadyAddedException;
import exceptions.PersonAlreadyExistsException;
import exceptions.PersonNotFoundException;

public class PersonRepository {
	private EntityManager entityManager;

	public PersonRepository() {
		entityManager = SocialEntityManagerFactory.getInstance().createEntityManager();
	}

	public boolean create(Person person) throws PersonAlreadyExistsException {
		if (exists(person.getName())) {
			throw new PersonAlreadyExistsException();
		}
		entityManager.getTransaction().begin();
		entityManager.persist(person);
		entityManager.getTransaction().commit();
		return true;
	}

	private boolean exists(String name) {
		try {
			findByName(name);
		} catch (PersonNotFoundException e) {
			return false;
		}
		return true;
	}

	public boolean updateName(String oldName, String newName)
			throws PersonNotFoundException, PersonAlreadyExistsException {
		if (exists(newName)) {
			throw new PersonAlreadyExistsException();
		}
		var person = findByName(oldName);
		entityManager.getTransaction().begin();
		person.setName(newName);
		entityManager.persist(person);
		entityManager.getTransaction().commit();
		return true;
	}

	public void addFriend(String firstFriendName, String secondFriendName)
			throws PersonNotFoundException, FriendAlreadyAddedException {
		var firstFriend = findByName(firstFriendName);
		var secondFriend = findByName(secondFriendName);
		if (!firstFriend.addFriend(secondFriend)) {
			throw new FriendAlreadyAddedException();
		}
		entityManager.getTransaction().begin();
		entityManager.persist(firstFriend);
		entityManager.getTransaction().commit();
	}

	public boolean addMessage(String name, String message) throws PersonNotFoundException {
		var person = findByName(name);
		entityManager.getTransaction().begin();
		for (var friendName : person.getFriends()) {
			var friend = findByName(friendName);
			entityManager.persist(new Message(person, friend, message));
		}
		entityManager.getTransaction().commit();
		return true;
	}

	public List<Person> findAll() {
		var query = entityManager.createQuery("SELECT p FROM Person p");
		return (List<Person>) query.getResultList();
	}

	public List<Person> getMostConnected(int k) {
		return findAllSortedByNumberOfConnections().skip(Math.max(0, findAll().size() - k))
				.collect(Collectors.toList());
	}

	public List<Person> getLeastConnected(int k) {
		return findAllSortedByNumberOfConnections().limit(k).collect(Collectors.toList());
	}

	private Stream<Person> findAllSortedByNumberOfConnections() {
		return findAll().stream().sorted(this::compareByNumberOfConnections);
	}

	private int compareByNumberOfConnections(Person first, Person second) {
		return Integer.compare(first.getFriends().size(), second.getFriends().size());
	}

	public Person findByName(String name) throws PersonNotFoundException {
		var query = entityManager.createNamedQuery("person.find").setParameter("name", name);
		var results = query.getResultList();
		if (results.size() == 0) {
			throw new PersonNotFoundException();
		}
		return (Person) results.get(0);
	}

	public boolean delete(String name) throws PersonNotFoundException {
		var person = findByName(name);
		entityManager.getTransaction().begin();
		entityManager.remove(person);
		entityManager.getTransaction().commit();
		return true;
	}
}
