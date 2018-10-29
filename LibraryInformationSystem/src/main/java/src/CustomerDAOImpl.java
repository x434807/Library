/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import Interfaces.CustomerDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andrej Sokolík
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Customer t) {
        JpaHelper.executeInsideTransaction(entityManager, entityM -> entityM.persist(t));
    }

    @Override
    public void update(Customer entity) {
        JpaHelper.executeInsideTransaction(entityManager, entityM -> entityM.merge(entity));
    }

    @Override
    public void remove(Customer entity) {
        JpaHelper.executeInsideTransaction(entityManager, entityM -> entityM.remove(entity));
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
