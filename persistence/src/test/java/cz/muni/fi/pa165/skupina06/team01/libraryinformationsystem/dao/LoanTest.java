package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao;

import java.time.Instant;
import java.time.ZonedDateTime;
import static org.assertj.core.api.Java6Assertions.assertThat;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Book;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Loan;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.LoanItem;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.BookCondition;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.exceptions.BookNotAvailableException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej Sokolík
 */
public class LoanTest extends AbstractDaoTest{
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

        loan1 = new Loan(cust1, ("2016-12-04T22:27:17.783Z"));
        loan2 = new Loan(cust1, ("2006-02-19T15:42:17.783Z"));
        loan3 = new Loan(cust2, ("2009-06-09T16:15:17.783Z"));

        loan1.addLoanItem(new LoanItem(book1));
        loan2.addLoanItem(new LoanItem(book1));
        loan3.addLoanItem(new LoanItem(book1));

        bookDAO.create(book1);
        bookDAO.create(book2);

        customerDAO.create(cust1);
        customerDAO.create(cust2);

        loanDao.create(loan1);
        loanDao.create(loan2);
        loanDao.create(loan3);
    }

    public void createLoanTest() throws BookNotAvailableException{
        Loan loan = new Loan(cust1);
        loan.addLoanItem(new LoanItem(book1));
        
        loanDao.create(loan);
        
        Loan test = loanDao.findById(loan.getId());
        System.out.println("asdadaasd");
        assertThat(test).isNotNull();
        assertThat(test).isEqualTo(loan);
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
