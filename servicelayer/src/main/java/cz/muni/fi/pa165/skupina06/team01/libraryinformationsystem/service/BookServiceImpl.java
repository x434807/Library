package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.dozer.inject.Inject;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao.BookDAO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Book;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.BookCondition;

/**
 *
 * @author Matúš Čongrády
 */
@Service
public class BookServiceImpl implements BookService {

    @Inject
    private BookDAO bookDAO;

    @Override
    public void createBook(Book book) throws DataAccessException, IllegalArgumentException {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null!");
        }
        bookDAO.create(book);
    }

    @Override
    public void removeBook(Book book) throws DataAccessException, IllegalArgumentException {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null!");
        }
        bookDAO.remove(book);
    }

    @Override
    public void updateBook(Book book) throws DataAccessException, IllegalArgumentException {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null!");
        }
        bookDAO.update(book);
    }

    @Override
    public Book findBookById(Long id) throws DataAccessException, IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null!");
        }
        return bookDAO.findById(id);
    }

    @Override
    public Book findBookByName(String name) throws DataAccessException, IllegalArgumentException {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty!");
        }
        return bookDAO.findByName(name);
    }

    @Override
    public List<Book> getAllBooks() throws DataAccessException {
        return bookDAO.findAll();
    }

    @Override
    public List<Book> getAllBooksThatNeedRevision() throws DataAccessException {
        return bookDAO.findAll().stream().filter(
                book -> (book.getCondition() == BookCondition.BAD || book.getCondition() == BookCondition.UNKNOWN))
                .collect(Collectors.toList());
    }
}
