package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao.LoanDAO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Loan;
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

    @InjectMocks
    @Autowired
    private LoanService loanService;

    private Customer cust1;
    private Customer cust2;

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

}
