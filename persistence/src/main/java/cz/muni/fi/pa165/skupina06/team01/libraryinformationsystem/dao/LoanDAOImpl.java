package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Loan;

/**
 *
 * @author Martin Piatka
 */

@Repository
public class LoanDAOImpl implements LoanDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Loan findById(Long id) {
        return em.find(Loan.class, id);
    }

    @Override
    public List<Loan> findAll() {
        return em.createQuery("SELECT e FROM Loan e", Loan.class).getResultList();
    }

    @Override
    public void create(Loan loan) {
        em.persist(loan);
    }

    @Override
    public void remove(Loan loan) {
        em.remove(loan);
    }

    @Override
    public void update(Loan loan) {
        em.merge(loan);
    }
}
