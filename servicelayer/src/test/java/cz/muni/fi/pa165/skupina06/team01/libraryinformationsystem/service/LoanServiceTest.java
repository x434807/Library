package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao.BookDAO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao.CustomerDAO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao.LoanDAO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao.LoanItemDAO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Book;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Loan;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.BookCondition;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.exceptions.BookNotAvailableException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class LoanServiceTest extends AbstractServiceTest{
    @Mock
    private LoanDAO loanDAO;

    @Mock
    private LoanItemDAO loanItemDAO;

    @Mock
    private BookDAO bookDAO;

    @Mock
    private CustomerDAO customerDAO;

    @InjectMocks
    @Autowired
    private LoanService loanService;

    private Customer cust1;
    private Customer cust2;

    private Book book1;
    private Book book2;
    private Book book3;

    private Loan loan1;
    private Loan loan2;
    private List<Loan> emptyList = Collections.emptyList();
    private List<Loan> listOfLoans;


    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        cust1 = new Customer();
        cust1.setLogin("johan1");
        cust1.setName("Johan");
        cust1.setSurname("Krivý");
        cust1.setPassword("pass");

        cust2 = new Customer();
        cust2.setLogin("john2");
        cust2.setName("Johny");
        cust2.setSurname("Riko");
        cust2.setPassword("word");

        loan1 = new Loan(cust1);
        loan2 = new Loan(cust1);

        listOfLoans = new ArrayList<>();
        listOfLoans.add(loan1);
        listOfLoans.add(loan2);

        book1 = new Book();
        book1.setAuthor("Author 1");
        book1.setName("BookName 1");
        book1.setCondition(BookCondition.NEW);
        book1.setAvailable(true);
        book1.setISBN("ISBN-1");

        book2 = new Book();
        book2.setAuthor("Author 2");
        book2.setName("BookName 2");
        book2.setCondition(BookCondition.GOOD);
        book2.setAvailable(true);
        book2.setISBN("ISBN-2");

        book3 = new Book();
        book3.setAuthor("Author 3");
        book3.setName("BookName 3");
        book3.setCondition(BookCondition.GOOD);
        book3.setAvailable(false);
        book3.setISBN("ISBN-3");

    }

    @Test
    public void createLoanTest() {
        loanService.create(loan1);
        verify(loanDAO).create(loan1);
    }

    @Test
    public void updateLoanTest() {
        loanService.create(loan2);
        verify(loanDAO).create(loan2);

        loan2.setCustomer(cust1);

        loanDAO.update(loan2);
        verify(loanDAO).update(loan2);
    }

    @Test
    public void removeLoanTest() {
        loanService.create(loan2);
        verify(loanDAO).create(loan2);

        loanService.remove(loan2);
        verify(loanDAO).remove(loan2);
    }

    @Test
    public void getAllWithEmptyListReturnsEmptyListTest() {
        Mockito.when(loanDAO.findAll()).thenReturn(emptyList);

        assertThat(loanService.findAll()).isEqualTo(emptyList);
    }

    @Test
    public void getAllWithNonEmptyListReturnsNonEmptyListTest() {
        Mockito.when(loanDAO.findAll()).thenReturn(listOfLoans);

        assertThat(loanService.findAll()).isEqualTo(listOfLoans);
    }

    @Test
    public void findByIdWithWrongIdReturnsNullTest() {
        Long id = 0l;
        Mockito.when(loanDAO.findById(id)).thenReturn(null);

        assertThat(loanService.findById(id)).isNull();
    }

    @Test
    public void findByIdWithCorrectIdReturnsPersonTest() {
        Long id = 0l;
        Mockito.when(loanDAO.findById(id)).thenReturn(loan1);

        assertThat(loanService.findById(id)).isEqualTo(loan1);
    }

    @Test
    public void addLoanedBook() throws BookNotAvailableException {
        loanService.addLoanedBook(loan1, book1);
        loanService.addLoanedBook(loan1, book2);

        assertThat(loan1.getItems().size()).isEqualTo(2);
        assertThat(book1.isAvailable()).isEqualTo(false);
        assertThat(book2.isAvailable()).isEqualTo(false);
    }

    @Test(expectedExceptions = BookNotAvailableException.class)
    public void addLoanedBookUnavailable() throws BookNotAvailableException {
        loanService.addLoanedBook(loan1, book3);
    }

}
