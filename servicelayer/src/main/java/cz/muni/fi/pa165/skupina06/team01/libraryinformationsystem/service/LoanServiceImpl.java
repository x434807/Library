package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao.BookDAO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao.CustomerDAO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao.LoanDAO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao.LoanItemDAO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Book;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Loan;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.LoanItem;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.BookCondition;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.exceptions.BookNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanDAO loanDAO;

    @Autowired
    private LoanItemDAO loanItemDAO;

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private BookDAO bookDAO;

    @Override
    public void addLoanedBook(Loan loan, Book book) throws BookNotAvailableException, IllegalArgumentException, DataAccessException{
        if (book == null)
            throw new IllegalArgumentException("Book can not be null!");

        if(!book.isAvailable()){
            throw new BookNotAvailableException("Unavailable book cannot be borrowed!");
        }
        BookCondition borrowCondition = book.getCondition();
        BookCondition returnCondition = BookCondition.UNKNOWN; // Not yet returned

        LoanItem loanItem = new LoanItem(book, borrowCondition, returnCondition);
        loanItemDAO.create(loanItem);

        book.setAvailable(false);
        bookDAO.update(book);

        loan.addLoanItem(loanItem);
        loanDAO.update(loan);

        Customer customer = loan.getCustomer();
        customer.addBorrowedBook(book);
        customerDAO.update(customer);
    }

    @Override
    public Loan loanBooks(Customer customer, List<Book> books) throws BookNotAvailableException, DataAccessException, IllegalArgumentException {
        Loan loan = new Loan(customer, ZonedDateTime.now());

        for(Book b : books){
            addLoanedBook(loan, b);
        }
        create(loan);

        return loan;
    }

    @Override
    public Loan findById(Long id) throws DataAccessException, IllegalArgumentException {
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }

        return loanDAO.findById(id);
    }

    @Override
    public List<Loan> findAll() throws DataAccessException {
        return loanDAO.findAll();
    }

    @Override
    public void create(Loan loan) throws DataAccessException, IllegalArgumentException {
        if(loan == null){
            throw new IllegalArgumentException("Loan cannot be null");
        }

        loanDAO.create(loan);
    }

    @Override
    public void remove(Loan loan) throws DataAccessException, IllegalArgumentException {
        if(loan == null){
            throw new IllegalArgumentException("Loan cannot be null");
        }

        loanDAO.remove(loan);
    }

    @Override
    public void update(Loan loan) throws DataAccessException, IllegalArgumentException {
        if(loan == null){
            throw new IllegalArgumentException("Loan cannot be null");
        }

        loanDAO.update(loan);
    }
}
