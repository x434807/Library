package Interfaces;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    /**
     * Gets entity with given id
     * @param id
     * @return
     */
    Optional<T> get(long id);

    /**
     * Gets all entities
     * @return List containing all entities
     */
    List<T> getAll();

    /**
     * Saves entity to database
     * @param t
     */
    void save(T t);

    /**
     * Removes entity from database
     * @param t
     */
    void delete(T t);
}
