package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Book;

/**
 * @author Matúš Čongrády
 */
@Repository
public class BookDAOImpl implements BookDAO {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Book book) {
        em.persist(book);
    }

    @Override
    public void update(Book book) {
        em.merge(book);
    }

    @Override
    public void remove(Book book) {
        em.remove(book);
    }

    @Override
    public Book findById(Long id) {
        return em.find(Book.class, id);
    }

    @Override
    public Book findByName(String name) {
        try {
            return em.createQuery("SELECT b FROM Book b WHERE b.name = :name", Book.class).setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Book> findAll() {
        return em.createQuery("SELECT b FROM Book b WHERE b.condition is not cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.BookCondition.DISCARDED", Book.class).getResultList();
    }
}