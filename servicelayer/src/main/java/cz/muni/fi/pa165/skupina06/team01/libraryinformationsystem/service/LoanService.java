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
    /**
     * Adds book into a loan
     *
     * @param loan
     * @param book
     * @throws BookNotAvailableException - when book is not available
     * @throws DataAcessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the id is null or not present in the database
     */
    void addLoanedBook(Loan loan, Book book) throws BookNotAvailableException, DataAccessException, IllegalArgumentException;

    /**
     * Creates a loan with multiple books for a customer
     * @param customer
     * @param books
     * @return created loan
     * @throws BookNotAvailableException - when at least one book is not available
     * @throws DataAcessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the id is null or not present in the database
     */
    Loan loanBooks(Customer customer, List<Book> books) throws BookNotAvailableException, DataAccessException, IllegalArgumentException;


    /**
     * Finds a loan by its ID.
     *
     * @param id the Loan's ID
     * @return loan with the given id
     * @throws DataAcessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the id is null or not present in the database
     */
    Loan findById(Long id) throws DataAccessException, IllegalArgumentException;

    /**
     * Returns all loans
     *
     * @return loans
     * @throws DataAcessException in case of any failure on the persistence layer
     */
    List<Loan> findAll() throws DataAccessException;

    /**
     * Creates a new Loan in the system.
     *
     * @param loan to be created
     * @throws DataAcessException       in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the Loan is null or not present in the database
     */
    void create(Loan loan) throws DataAccessException, IllegalArgumentException;

    /**
     * Removes the specified Loan from the system.
     *
     * @param loan to be removed
     * @throws DataAcessException       in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the Loan is null or not present in the database
     */
    void remove(Loan loan) throws DataAccessException, IllegalArgumentException;

    /**
     * Updates the specified Loan
     *
     * @param loan to be updated
     * @throws DataAcessException       in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the Loan is null or not present in the database
     */
    void update(Loan loan) throws DataAccessException, IllegalArgumentException;

}
