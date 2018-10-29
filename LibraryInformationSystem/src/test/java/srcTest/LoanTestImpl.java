package srcTest;

import Exceptions.BookNotAvailableException;
import java.time.ZonedDateTime;
import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import src.Book;
import src.BookCondition;
import src.Customer;
import src.Loan;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej Sokolík
 */
public class LoanTestImpl extends AbstractDaoTest{
    private Loan loan1;
    private Loan loan2;
    private Loan loan3;

    private Customer cust1;
    private Customer cust2;

    private Book book1;
    private Book book2;


    @BeforeMethod
    public void initTest() throws BookNotAvailableException {
        cust1 = new Customer();
        cust1.setName("Jozef");
        cust1.setSurname("Naked");
        cust1.setLogin("admin");
        cust1.setPassword("password");

        cust2 = new Customer();
        cust2.setName("Adam");
        cust2.setSurname("Fisrt");
        cust2.setLogin("adam");
        cust2.setPassword("firstman");

        book1 = new Book();
        book1.setAuthor("Dan Brown");
        book1.setISBN("0-7432-7506-3");
        book1.setName("Angels & Demons");
        book1.setCondition(BookCondition.GOOD);
        book1.setAvailable(true);

        book2 = new Book();
        book2.setAuthor("Dan Brown");
        book2.setISBN("0-5632-7324-8");
        book2.setName("Da Vinci Code");
        book2.setCondition(BookCondition.BAD);
        book2.setAvailable(false);

        loan1 = new Loan(cust1, ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]"));
        loan2 = new Loan(cust1, ZonedDateTime.parse("2008-12-03T10:15:30+01:00[Europe/Paris]"));
        loan3 = new Loan(cust2, ZonedDateTime.parse("2009-12-03T10:15:30+01:00[Europe/Paris]"));

        loan1.addLoanedBook(book1);
        loan2.addLoanedBook(book1);
        loan3.addLoanedBook(book1);

        loanDao.create(loan1);
        loanDao.create(loan2);
        loanDao.create(loan3);
    }

    public void createLoanTest() throws BookNotAvailableException{
        Loan loan = new Loan(cust1);
        loan.addLoanedBook(book1);
        
        loanDao.create(loan);
        
        Loan test = loanDao.findById(loan.getId());
        System.out.println("asdadaasd");
        assertThat(test).isNotNull();
        assertThat(test).isEqualTo(loan);
    }

    @Test(expectedExceptions = BookNotAvailableException.class)
    public void addInAvailableBook() throws BookNotAvailableException {
        Loan loan = new Loan(cust2);
        loan.addLoanedBook(book2);
    }

    @Test
    public void getLoanByIdTest() {
        Loan loan = loanDao.findById(loan1.getId());
        assertThat(loan1).isEqualTo(loan);
    }

    @Test
    public void getAllLoansTest() {
        assertThat(loanDao.findAll()).hasSize(3);
    }

    @Test(dependsOnMethods = { "getAllLoansTest" })
    public void updateLoansTest() {
        loan1.setCustomer(cust2);

        loanDao.update(loan1);

        assertThat(loanDao.findAll().size()).isEqualTo(3);
        assertThat(loanDao.findAll().get(0).getCustomer()).isEqualTo(cust2);
    }

    @Test(dependsOnMethods = { "getAllLoansTest" })
    public void removeLoanTest() {
        assertThat(loanDao.findAll().size()).isEqualTo(3);

        loanDao.remove(loan3);
        assertThat(loanDao.findAll().size()).isEqualTo(2);
    }
}
