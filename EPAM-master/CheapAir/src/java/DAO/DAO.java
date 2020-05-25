package DAO;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Optional;

public interface DAO<K, T> {

    Optional<T> get(K key);
    Hashtable<K, T> getAll();

    void save() throws IOException;

    void add(T obj) throws IOException;

    void update (T obj, K key) throws IOException;

    void delete(K key) throws IOException;
}
