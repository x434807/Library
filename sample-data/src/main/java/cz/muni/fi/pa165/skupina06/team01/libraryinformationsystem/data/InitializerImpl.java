package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.data;


import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Book;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Loan;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.BookCondition;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.exceptions.BookNotAvailableException;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service.BookService;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service.CustomerService;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service.LoanService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Martin Piatka
 *
 */

@Component
@Transactional
public class InitializerImpl implements Initializer{

    @Inject
    CustomerService customerService;

    @Inject
    LoanService loanService;

    @Inject
    BookService bookService;

    @Override
    public void loadData() {
        loadBooks();
        loadCustomers();
        loadLoans();
    }

    void loadBooks(){
        createBook("Tester Testovics", "Testing 101", "3216546-56468", BookCondition.GOOD);
    }

    void loadCustomers(){
        createCustomer("Juraj", "Pokazil", "test123", "hunter2");
    }

    void loadLoans(){
        createLoan(1, Arrays.asList(1L));
    }

    void createLoan(long customerId, List<Long> bookIds){
        Customer customer = customerService.findCustomerById(customerId);
        List<Book> books = new ArrayList<>();
        for(Long id : bookIds){
            books.add(bookService.findBookById(id));
        }

        try {
            loanService.loanBooks(customer, books);
        } catch (BookNotAvailableException e) {
            e.printStackTrace();
        }
    }

    void createBook(String author, String title, String ISBN, BookCondition condition){
        Book book = new Book();

        book.setAuthor(author);
        book.setName(title);
        book.setISBN(ISBN);
        book.setCondition(condition);

        bookService.createBook(book);
    }

    void createCustomer(String name, String surname, String login, String password){
        Customer customer = new Customer();

        customer.setName(name);
        customer.setSurname(surname);
        customer.setLogin(login);
        customer.setPassword(password);

        customerService.registerCustomer(customer);
    }
}
