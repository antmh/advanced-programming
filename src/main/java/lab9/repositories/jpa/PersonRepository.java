package lab9.repositories.jpa;

import lab9.entities.Person;

class PersonRepository extends JPARepository<Person> {
	@Override
	protected String getPrefix() {
		return "person";
	}

	@Override
	protected void persist(Person item) {
		for (var movieActedIn : item.getMoviesActedIn()) {
			entityManager.persist(movieActedIn);
		}
		for (var movieDirected : item.getMoviesActedIn()) {
			entityManager.persist(movieDirected);
		}
		entityManager.persist(item);
	}
}
