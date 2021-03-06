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

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
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
        loanItem.setLoan(loan);
        loanItemDAO.create(loanItem);

        Customer customer = loan.getCustomer();

        book.setAvailable(false);
        book.setCustomer(customer);
        bookDAO.update(book);

        loan.addLoanItem(loanItem);
        loanDAO.update(loan);

        customer.addBorrowedBook(book);
        customerDAO.update(customer);
    }

    @Override
    public Loan loanBooks(Customer customer, List<Book> books) throws BookNotAvailableException, DataAccessException, IllegalArgumentException {
        Loan loan = new Loan(customer, Instant.now().toString());
        create(loan);

        for(Book b : books){
            addLoanedBook(loan, b);
        }
        update(loan);

        return loan;
    }

    @Override
    public Loan loanBooks(String customerLogin, List<Long> bookIds) throws BookNotAvailableException, DataAccessException, IllegalArgumentException {
        Customer customer = customerDAO.findByLogin(customerLogin);
        List<Book> books = new ArrayList<>();
        for(Long id : bookIds){
            books.add(bookDAO.findById(id));
        }


        return loanBooks(customer, books);
    }

    @Override
    public void returnBook(Long bookId, BookCondition returnCondition) throws DataAccessException, IllegalArgumentException {
        Book book = bookDAO.findById(bookId);
        Customer customer = book.getCustomer();

        if(book.isAvailable()){
            //book is not borrowed, do nothing
            return;
        }

        book.setCondition(returnCondition);
        book.setAvailable(true);
        customer.removeBorrowedBook(book);
        book.setCustomer(null);

        LoanItem loanItem = loanItemDAO.findByBookId(bookId);
        loanItem.setReturnCondition(returnCondition);

        bookDAO.update(book);
        customerDAO.update(customer);
        loanItemDAO.update(loanItem);
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
