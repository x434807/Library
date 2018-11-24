package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.LoanItem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author Martin Piatka
 */

@Repository
public class LoanItemDAOImpl implements LoanItemDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(LoanItem loanItem) {
        em.persist(loanItem);
    }

    @Override
    public void update(LoanItem loanItem) {
        em.merge(loanItem);
    }

    @Override
    public void remove(LoanItem loanItem) {
        em.merge(loanItem);
    }

    @Override
    public LoanItem findById(Long id) {
        return em.find(LoanItem.class, id);
    }

    @Override
    public List<LoanItem> findAll() {
        return em.createQuery("SELECT e FROM LoanItem e", LoanItem.class).getResultList();
    }
}
