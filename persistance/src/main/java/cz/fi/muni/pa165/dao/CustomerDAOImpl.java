package java.cz.fi.muni.pa165.dao;

import java.cz.fi.muni.pa165.entity.Customer;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author Andrej Sokolík
 */ 
public class CustomerDAOImpl implements DAO<Customer>{
    private EntityManager entityManager;

    @Override
    public void create(Customer t) {
        executeInsideTransaction(entityManager -> entityManager.persist(t));
    }

    @Override
    public Customer findById(long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public List<Customer> findAll() {
        Query q = entityManager.createQuery("SELECT e FROM Customer e");
        return q.getResultList();
    }

    @Override
    public void delete(Customer t) {
        executeInsideTransaction(entityManager -> entityManager.remove(t));
    }

    private void executeInsideTransaction(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            action.accept(entityManager);
            tx.commit(); 
        }
        catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }
    
}
