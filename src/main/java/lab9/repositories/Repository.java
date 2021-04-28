package lab9.repositories;

import java.util.List;
import java.util.Optional;

public abstract class Repository<T> {
	public abstract void create(T item);
	public abstract List<T> findByName(String name);
	public abstract Optional<T> findById(int id);
}
