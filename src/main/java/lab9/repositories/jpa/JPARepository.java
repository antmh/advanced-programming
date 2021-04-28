package lab9.repositories.jpa;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import lab9.repositories.Repository;

abstract class JPARepository<T> extends Repository<T> {
	protected EntityManager entityManager;

	public JPARepository() {
		entityManager = MovieManagerFactory.getInstance().createEntityManager();
	}

	@Override
	public void create(T item) {
		entityManager.getTransaction().begin();
		persist(item);
		entityManager.getTransaction().commit();
	}

	@Override
	public Optional<T> findById(int id) {
		var query = entityManager.createNamedQuery(getPrefix() + ".findById").setParameter("id", id);
		var results = query.getResultList();
		if (results.size() == 0) {
			return Optional.empty();
		}
		return Optional.of((T) results.get(0));
	}

	@Override
	public List<T> findByName(String name) {
		var entityManager = MovieManagerFactory.getInstance().createEntityManager();
		var query = entityManager.createNamedQuery(getPrefix() + ".findByName").setParameter("name", name);
		var results = query.getResultList();
		entityManager.close();
		return results;
	}

	protected abstract String getPrefix();

	protected void persist(T item) {
		entityManager.persist(item);
	}
}
