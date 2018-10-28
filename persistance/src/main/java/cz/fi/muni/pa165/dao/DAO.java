package java.cz.fi.muni.pa165.dao;

import java.util.List;

/**
 *
 * @author Matúš Čongrády
 */

public interface DAO<T> {
    /**
     * Creates a persistent representation of an entity in the database.
     *
     * @param entity entity to be persisted
     */
    public void create(T t);

    /**
     * Updates an entity in the database.
     *
     * @param entity entity to be updated
     */
    public void update(T entity);

    /**
     * Deletes an entity from the database.
     *
     * @param entity to be deleted
     */
    public void remove(T entity);

    /**
     * Finds an entity in the database by the given ID.
     *
     * @param id ID of the entity to be found
     * @return an entity with the given ID
     */
    public T findById(Long id);

    /**
     * Returns a list of all entities in the database.
     *
     * @return a list of all entities in the database
     */
    public List<T> findAll();
}
