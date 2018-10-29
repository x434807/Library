package src;

import java.util.List;
import Interfaces.BookDAO;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class BookDAOImpl implements BookDAO {
    private EntityManager entityManager;

    @Override
    public void create(Book book) {
        JpaHelper.executeInsideTransaction(entityManager, em -> em.persist(book));
    }

    @Override
    public void update(Book book) {
        JpaHelper.executeInsideTransaction(entityManager, em -> em.merge(book));
    }

    @Override
    public void remove(Book book) {
        JpaHelper.executeInsideTransaction(entityManager, em -> em.remove(book));
    }

    @Override
    public Book findById(Long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public Book findByName(String name) {
        try {
            return entityManager.createQuery("SELECT * FROM Book WHERE Book.name = :name", Book.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    @Override
    public List<Book> findAll() {
        return entityManager.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
}