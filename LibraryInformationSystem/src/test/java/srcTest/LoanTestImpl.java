package srcTest;

import org.testng.annotations.BeforeMethod;
import src.Book;
import src.BookCondition;
import src.Customer;
import src.Loan;
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
    
    Book book1;
    Book book2;
    
    @BeforeMethod
    public void initCustomers(){
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
        
    }
    
    @BeforeMethod
    public void initBooks(){
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
    }
    
    @BeforeMethod
    public void initTest(){
        loan1 = new Loan(cust1);
        
    }
}
