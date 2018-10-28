/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import Interfaces.DAO;
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
    public Optional<Customer> get(long id) {
        return Optional.ofNullable(entityManager.find(Customer.class,id));
    }

    @Override
    public List<Customer> getAll() {
        Query q = entityManager.createQuery("SELECT e FROM Customer e");
        return q.getResultList();
    }

    @Override
    public void save(Customer t) {
        JpaHelper.executeInsideTransaction(entityManager, em -> em.persist(t));
    }

    @Override
    public void delete(Customer t) {
        JpaHelper.executeInsideTransaction(entityManager, em -> em.remove(t));
    }

}
