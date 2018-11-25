package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Book;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Loan;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.exceptions.BookNotAvailableException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Martin Piatka
 */

@Service
public interface LoanService {
    void addLoanedBook(Loan loan, Book book) throws BookNotAvailableException, DataAccessException, IllegalArgumentException;

    Loan loanBooks(Customer customer, List<Book> books) throws BookNotAvailableException, DataAccessException, IllegalArgumentException;

    Loan findById(Long id) throws DataAccessException, IllegalArgumentException;
    List<Loan> findAll() throws DataAccessException;
    void create(Loan loan) throws DataAccessException, IllegalArgumentException;
    void remove(Loan loan) throws DataAccessException, IllegalArgumentException;
    void update(Loan loan) throws DataAccessException, IllegalArgumentException;

}
