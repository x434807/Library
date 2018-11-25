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
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 * @author Martin Piatka
 */

public class LoanFacadeImpl implements LoanFacade{

    @Autowired
    LoanService loanService;
    @Autowired
    BeanMappingService beanMappingService;

    @Override
    public void addLoanedBook(LoanDTO loan, BookDTO book) throws BookNotAvailableException {
        loanService.addLoanedBook(beanMappingService.mapTo(loan, Loan.class),
                beanMappingService.mapTo(book, Book.class));
    }

    @Override
    public LoanDTO loanBooks(CustomerDTO customer, List<BookDTO> books) throws BookNotAvailableException {

        return beanMappingService.mapTo(loanService.loanBooks(beanMappingService.mapTo(customer, Customer.class),
                beanMappingService.mapTo(books, Book.class)), LoanDTO.class);
    }

    @Override
    public LoanDTO findById(Long id) {
        return beanMappingService.mapTo(loanService.findById(id), LoanDTO.class);
    }

    @Override
    public List<LoanDTO> findAll() {
        return beanMappingService.mapTo(loanService.findAll(), LoanDTO.class);
    }

    @Override
    public void create(LoanDTO loan) {
        loanService.create(beanMappingService.mapTo(loan, Loan.class));
    }

    @Override
    public void remove(LoanDTO loan) {
        loanService.remove(beanMappingService.mapTo(loan, Loan.class));
    }

    @Override
    public void update(LoanDTO loan) {
        loanService.update(beanMappingService.mapTo(loan, Loan.class));
    }
}
