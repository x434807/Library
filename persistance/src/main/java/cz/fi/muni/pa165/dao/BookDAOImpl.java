package java.cz.fi.muni.pa165.dao;

import java.cz.fi.muni.pa165.entity.Book;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

public class BookDAOImpl implements BookDAO {
    @Override
    public void create(Book book) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Book entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(Book entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Book findById(Long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Book findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Book> findAll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
