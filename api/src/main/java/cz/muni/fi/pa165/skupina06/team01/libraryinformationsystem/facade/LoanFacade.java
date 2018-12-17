package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade;

import java.util.List;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.BookDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CreateLoanDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.LoanDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.exceptions.BookNotAvailableException;

/**
 *
 * @autor Martin Piatka
 */

public interface LoanFacade {

    /**
     * Adds book into a loan
     *
     * @param loan
     * @param book
     * @throws BookNotAvailableException - when book is not available
     */
    void addLoanedBook(LoanDTO loan, BookDTO book) throws BookNotAvailableException;

    /**
     * Creates a loan with multiple books for a customer
     * @param customer
     * @param books
     * @return created loan
     * @throws BookNotAvailableException - when at least one book is not available
     */
    LoanDTO loanBooks(CustomerDTO customer, List<BookDTO> books) throws BookNotAvailableException;

    LoanDTO loanBooks(CreateLoanDTO createLoanDTO) throws BookNotAvailableException;


    /**
     * Finds loan by id
     * @param id
     * @return loan
     */
    LoanDTO findById(Long id);

    /**
     * Returns all loans
     * @return all loans
     */
    List<LoanDTO> findAll();

    /**
     * Creates a new loan in the system
     *
     * @param loan
     */
    void create(LoanDTO loan);

    /**
     * Removes the specified loan from the system
     *
     * @param loan
     */
    void remove(LoanDTO loan);

    /**
     * Updates the specified loan
     *
     * @param loan
     */
    void update(LoanDTO loan);
}
