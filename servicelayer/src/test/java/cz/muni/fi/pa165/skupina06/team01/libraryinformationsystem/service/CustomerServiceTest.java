/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service;

import static org.assertj.core.api.Java6Assertions.assertThat;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao.CustomerDAO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author Andrej Sokolík
 */
public class CustomerServiceTest extends AbstractServiceTest {

    @Mock
    private CustomerDAO customerDao;

    @InjectMocks
    @Autowired
    private CustomerServiceImpl customerService;

    private Customer cust1;
    private Customer cust2;
    private List<Customer> listOfCustomers;
    private List<Customer> emptyList = Collections.emptyList();

 
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

        listOfCustomers = new ArrayList<>();
        listOfCustomers.add(cust1);
        listOfCustomers.add(cust2);

       
    }

    @Test
    public void registerCustomerTest() {
        customerService.registerCustomer(cust2);
        verify(customerDao).create(cust2);
    }

    @Test
    public void updatePersonTest() {
        customerService.registerCustomer(cust2);
        verify(customerDao).create(cust2);

        cust2.setName("Jano");

        customerService.updateCustomer(cust2);
        verify(customerDao).update(cust2);
    }

    @Test
    public void removePersonTest() {
        customerService.registerCustomer(cust2);
        verify(customerDao).create(cust2);
        
        customerService.removeCustomer(cust2);
        verify(customerDao).remove(cust2);
    }

    @Test
    public void getAllWithEmptyListReturnsEmptyListTest() {
        Mockito.when(customerDao.findAll()).thenReturn(emptyList);

        assertThat(customerService.getAllPeople()).isEqualTo(emptyList);
    }

    @Test
    public void getAllWithNonEmptyListReturnsNonEmptyListTest() {
        Mockito.when(customerDao.findAll()).thenReturn(listOfCustomers);

        assertThat(customerService.getAllPeople()).isEqualTo(listOfCustomers);
    }

    @Test
    public void findByIdWithWrongIdReturnsNullTest() {
        Long id = 0l;
        Mockito.when(customerDao.findById(id)).thenReturn(null);

        assertThat(customerService.findCustomerById(id)).isNull();
    }

    @Test
    public void findByIdWithCorrectIdReturnsPersonTest() {
        Long id = 0l;
        Mockito.when(customerDao.findById(id)).thenReturn(cust1);

        assertThat(customerService.findCustomerById(id)).isEqualTo(cust1);
    }

    
}
