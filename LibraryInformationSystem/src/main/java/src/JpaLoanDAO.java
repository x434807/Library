package src;

import Interfaces.DAO;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class JpaLoanDAO implements DAO<Loan> {

    private EntityManager entityManager;

    public JpaLoanDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Loan> get(long id) {
        return Optional.ofNullable(entityManager.find(Loan.class,id));
    }

    @Override
    public List<Loan> getAll() {
        Query q = entityManager.createQuery("SELECT e FROM Loan e");
        return q.getResultList();
    }

    @Override
    public void save(Loan loan) {
        JpaHelper.executeInsideTransaction(entityManager, em -> em.persist(loan));

    }

    @Override
    public void delete(Loan loan) {
        JpaHelper.executeInsideTransaction(entityManager, em -> em.remove(loan));
    }

}
