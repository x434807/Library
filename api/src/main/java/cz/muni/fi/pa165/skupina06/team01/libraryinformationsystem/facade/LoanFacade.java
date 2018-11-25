package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.BookDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.LoanDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Book;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.exceptions.BookNotAvailableException;

import java.util.List;

/**
 *
 * @autor Martin Piatka
 */

public interface LoanFacade {

    void addLoanedBook(LoanDTO loan, BookDTO book) throws BookNotAvailableException;
    LoanDTO loanBooks(CustomerDTO customer, List<BookDTO> books) throws BookNotAvailableException;

    LoanDTO findById(Long id);
    List<LoanDTO> findAll();
    void create(LoanDTO loan);
    void remove(LoanDTO loan);
    void update(LoanDTO loan);
}
