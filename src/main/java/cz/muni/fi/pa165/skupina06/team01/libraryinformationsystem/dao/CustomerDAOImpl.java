package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;

/**
 *
 * @author Andrej Sokolík
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Customer customer) {
        entityManager.persist(customer);
    }

    @Override
    public void update(Customer customer) {
        entityManager.merge(customer);
    }

    @Override
    public void remove(Customer customer) {
        entityManager.remove(customer);
    }

    @Override
    public Customer findById(Long id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public List<Customer> findAll() {
        return entityManager.createQuery("SELECT c FROM Customer c", Customer.class).getResultList();
    }

    @Override
    public Customer findByLogin(String login) {
        if (login == null || login.isEmpty()) {
            throw new IllegalArgumentException("The login is null!");
        }
        try {
            return entityManager.createQuery("SELECT c FROM Customer c WHERE c.login = :login", Customer.class)
                    .setParameter("login", login).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
