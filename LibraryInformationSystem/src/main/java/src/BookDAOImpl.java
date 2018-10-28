package src;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class BookDAOImpl implements BookDAO {
    private EntityManager entityManager;

    @Override
    public void create(Book book) {
        JpaHelper.executeInsideTransaction(entityManager, em -> em.persist(book));
    }

    @Override
    public void update(Book entity) {
        JpaHelper.executeInsideTransaction(entityManager, em -> em.merge(entity));
    }

    @Override
    public void remove(Book entity) {
        JpaHelper.executeInsideTransaction(entityManager, em -> em.remove(entity));
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
        return entityManager.createQuery("SELECT p FROM Person p", Book.class).getResultList();
    }
}