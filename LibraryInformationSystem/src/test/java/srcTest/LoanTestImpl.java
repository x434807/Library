package srcTest;

import Exceptions.BookNotAvailableException;
import java.time.ZonedDateTime;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import src.Book;
import src.BookCondition;
import src.Customer;
import src.Loan;
import src.LoanDAOImpl;
//import org.assertj.core.api.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej Sokolík
 */
public class LoanTestImpl {
    private Loan loan1;
    private Loan loan2;
    private Loan loan3;
    
    private Customer cust1;
    private Customer cust2;
    
    private Book book1;
    private Book book2;
    
    private LoanDAOImpl loanDao;
    
    @BeforeMethod
    public void initTest(){
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
        book1.setIsAvailable(true);
        
        book1 = new Book();
        book1.setAuthor("Dan Brown");
        book1.setISBN("0-5632-7324-8");
        book1.setName("Da Vinci Code");
        book1.setCondition(BookCondition.BAD);
        book1.setIsAvailable(false);
        
        
        loan1 = new Loan(cust1,ZonedDateTime.parse("2007-12-03T10:15:30+01:00[Europe/Paris]"));
        loan2 = new Loan(cust1, ZonedDateTime.parse("2008-12-03T10:15:30+01:00[Europe/Paris]"));
        loan3 = new Loan(cust2, ZonedDateTime.parse("2009-12-03T10:15:30+01:00[Europe/Paris]"));
        
        loan1.addLoanedBook(book1);
        loan2.addLoanedBook(book1);
        loan3.addLoanedBook(book1);
        
        loanDao.create(loan1);
        loanDao.create(loan2);
        loanDao.create(loan3);
    }
    
    @Test(dependsOnMethods = {"getLoanByIdTest"})
    public void createLoanTest(){
        Loan loan = new Loan(cust1);
        loan.addLoanedBook(book1);
        
        loanDao.create(loan);
        
        Loan test = loanDao.findById(loan.getId());
        
        assertThat(test).isNotNull();
        assertThat(test).isEqualTo(loan);
    }
    
    @Test(expectedExceptions = BookNotAvailableException.class)
    public void addInAvailableBook(){
        Loan loan = new Loan(cust2);
        loan.addLoanedBook(book2);
    }
    
    @Test
    public void getLoanByIdTest() {
        Loan loan = loanDao.findById(loan1.getId());
        assertThat(loan1).isEqualTo(loan);
    }
    
}
