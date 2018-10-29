package src;

import Interfaces.DAO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class LoanDAOImpl implements DAO<Loan> {

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
