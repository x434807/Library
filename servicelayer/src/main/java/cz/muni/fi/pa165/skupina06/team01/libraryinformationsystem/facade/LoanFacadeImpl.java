package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.BookDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.LoanDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Book;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Loan;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.exceptions.BookNotAvailableException;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service.BeanMappingService;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * @author Martin Piatka
 */

public class LoanFacadeImpl implements LoanFacade{
    private static final Logger logger = LoggerFactory.getLogger(CustomerFacadeImpl.class);

    @Autowired
    LoanService loanService;
    @Autowired
    BeanMappingService beanMappingService;

    @Override
    public void addLoanedBook(LoanDTO loan, BookDTO book) throws BookNotAvailableException {
        logger.debug("Adding book {} to loan {}", book, loan);
        loanService.addLoanedBook(beanMappingService.mapTo(loan, Loan.class),
                beanMappingService.mapTo(book, Book.class));
    }

    @Override
    public LoanDTO loanBooks(CustomerDTO customer, List<BookDTO> books) throws BookNotAvailableException {
        logger.debug("Loaning books {} for customer {}", books, customer);

        return beanMappingService.mapTo(loanService.loanBooks(beanMappingService.mapTo(customer, Customer.class),
                beanMappingService.mapTo(books, Book.class)), LoanDTO.class);
    }

    @Override
    public LoanDTO findById(Long id) {
        logger.debug("Getting loan with id {}", id);
        Loan loan = loanService.findById(id);
        return loan == null ? null : beanMappingService.mapTo(loan, LoanDTO.class);
    }

    @Override
    public List<LoanDTO> findAll() {
        logger.debug("Finding all loans");
        List<Loan> loans = loanService.findAll();
        return loans == null ? null : beanMappingService.mapTo(loans, LoanDTO.class);
    }

    @Override
    public void create(LoanDTO loan) {
        logger.debug("Creating the loan {}", loan);
        loanService.create(beanMappingService.mapTo(loan, Loan.class));
    }

    @Override
    public void remove(LoanDTO loan) {
        logger.debug("Removing the loan {}", loan);
        loanService.remove(beanMappingService.mapTo(loan, Loan.class));
    }

    @Override
    public void update(LoanDTO loan) {
        logger.debug("Updating the loan {}", loan);
        loanService.update(beanMappingService.mapTo(loan, Loan.class));
    }
}
