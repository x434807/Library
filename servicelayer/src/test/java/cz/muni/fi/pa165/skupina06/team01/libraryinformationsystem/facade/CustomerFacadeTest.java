/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service.CustomerService;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Java6Assertions.assertThat;
import org.testng.annotations.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeMethod;

/**
 *
 * @author Andrej Sokolík
 */
public class CustomerFacadeTest extends AbstractFacadeTest{
    @Mock
    private CustomerService customerService;

    @Autowired
    @InjectMocks
    private CustomerFacadeImpl customerFacade;

    private Customer customer;
    private CustomerDTO customerDTO;
    private List<Customer> people;

    private Long customerId = 1L;
    private String customerName = "Matej";

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        customerFacade = (CustomerFacadeImpl) unwrapProxy(customerFacade);
        ReflectionTestUtils.setField(customerFacade, "custService", customerService);
        ReflectionTestUtils.setField(customerFacade, "beanMappServ", beanMappingService);

        customer = new Customer();
        customer.setName(customerName);
        customer.setPassword("pass");

        customerDTO = new CustomerDTO();
        customerDTO.setName(customerName);
        customerDTO.setPassword("pass");
    }

    @Test
    public void testCreateCustomer() {
        when(beanMappingService.mapTo(customerDTO, Customer.class)).thenReturn(customer);
        customerFacade.registerCustomer(customerDTO);

        //verify(sustomerService).registerCustomer(customer, "pass");
        verify(beanMappingService).mapTo(customerDTO, Customer.class);
    }

    @Test
    public void testUpdateCustomer() {
        when(beanMappingService.mapTo(customerDTO, Customer.class)).thenReturn(customer);
        customerFacade.registerCustomer(customerDTO);

        //verify(sustomerService).registerCustomer(customer, "pass");
        verify(beanMappingService).mapTo(customerDTO, Customer.class);

        customerDTO.setSurname("Maly");

        customerFacade.updateCustomer(customerDTO);
        verify(customerService).updateCustomer(customer);
        verify(beanMappingService, times(2)).mapTo(customerDTO, Customer.class);
    }

    @Test
    public void testDeleteCustomer() {
        when(beanMappingService.mapTo(customerDTO, Customer.class)).thenReturn(customer);
        customerFacade.registerCustomer(customerDTO);

        //verify(sustomerService).registerCustomer(customer, "pass");
        verify(beanMappingService).mapTo(customerDTO, Customer.class);

        customerFacade.removeCustomer(customerId);
        verify(customerService).removeCustomer(customer);
        verify(beanMappingService.mapTo(customerDTO, Customer.class));
    }

    @Test
    public void testFindAll() {
        when(customerService.getAllCustomers()).thenReturn(Collections.singletonList(customer));
        when(beanMappingService.mapTo(Collections.singletonList(customer), CustomerDTO.class)).thenReturn(Collections.singletonList(customerDTO));

        Collection<CustomerDTO> customers = customerFacade.getAllPeople();
        List<CustomerDTO> per = (List<CustomerDTO>) customers;

        assertThat(customer.getName()).isEqualTo(per.get(0).getName());
        verify(customerService).getAllCustomers();
        verify(beanMappingService).mapTo(Collections.singletonList(customer), CustomerDTO.class);
    }

    @Test
    public void testFindAllWithNull() {
        when(customerService.getAllCustomers()).thenReturn(null);

        Collection<CustomerDTO> customers = customerFacade.getAllPeople();

        assertThat(customers).isNull();
        verify(customerService).getAllCustomers();
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindById() {
        when(customerService.findCustomerById(customerId)).thenReturn(customer);

        CustomerDTO customerDTO = customerFacade.findCustomerById(customerId);

        assertThat(customerDTO).isNotNull();
        assertThat(customer.getName()).isEqualTo(customerDTO.getName());
        verify(customerService).findCustomerById(customerId);
        verify(beanMappingService).mapTo(customer, CustomerDTO.class);
    }

    @Test
    public void testFindByIdWithNull() {
        when(customerService.findCustomerById(customerId)).thenReturn(null);

        CustomerDTO customerDTO = customerFacade.findCustomerById(customerId);

        assertThat(customerDTO).isNull();
        verify(customerService).findCustomerById(customerId);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindByName() {
        when(customerService.findCustomerByName(customerName)).thenReturn(people);

        List<CustomerDTO> customerDTO = customerFacade.findCustomerByName(customerName);

        assertThat(customerDTO).isNotNull();
        assertThat(customer.getName()).isEqualTo(customerDTO.get(0).getName());
        verify(customerService).findCustomerByName(customerName);
        verify(beanMappingService).mapTo(customer, CustomerDTO.class);
    }

    @Test
    public void testFindByNameWithNull() {
        when(customerService.findCustomerByName(customerName)).thenReturn(null);

        List<CustomerDTO> customerDTO = customerFacade.findCustomerByName(customerName);

        assertThat(customerDTO).isEmpty();
        verify(customerService).findCustomerByName(customerName);
        verify(beanMappingService, never()).mapTo(any(), any());
    }
}
