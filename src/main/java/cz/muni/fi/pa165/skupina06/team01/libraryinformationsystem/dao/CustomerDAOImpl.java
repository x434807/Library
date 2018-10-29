/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;
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
