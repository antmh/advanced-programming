package lab8.dao;

public interface Repository<T> {
    void create(T item);

    void update(T item);

    void delete(T item);
}
