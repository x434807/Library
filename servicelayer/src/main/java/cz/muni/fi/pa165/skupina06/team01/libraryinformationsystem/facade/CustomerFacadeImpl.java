/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.LoanDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service.BeanMappingService;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service.CustomerService;
import java.util.List;
import javax.transaction.Transactional;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrej Sokolík
 */

@Service
@Transactional
public class CustomerFacadeImpl implements CustomerFacade{
    private static final Logger logger = LoggerFactory.getLogger(CustomerFacadeImpl.class);
    
    @Inject
    private CustomerService custService;
    @Inject
    private BeanMappingService beanMappServ;

    @Override
    public CustomerDTO registerCustomer(CustomerDTO customerDTO) {
        logger.debug("Creating the customer {}", customerDTO);
        Customer customer = beanMappServ.mapTo(customerDTO, Customer.class);
        custService.registerCustomer(customer);
        customerDTO.setId(customer.getId());
        return customerDTO;
    }

    @Override
    public void removeCustomer(CustomerDTO customerDTO) {
        logger.debug("Removing the customer {}", customerDTO);
        Customer customer = beanMappServ.mapTo(customerDTO, Customer.class);
        custService.removeCustomer(customer);
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        logger.debug("Updating the customer {}", customerDTO);
        Customer customer = beanMappServ.mapTo(customerDTO, Customer.class);
        custService.updateCustomer(customer);
    }

    @Override
    public List<CustomerDTO> getAllPeople() {
        logger.debug("Getting all customers");
        List<Customer> customers = custService.getAllCustomers();
        if(customers == null){
            return null;
        }
        return beanMappServ.mapTo(customers, CustomerDTO.class);
    }

    @Override
    public boolean authenticate(String login, String password) {
        logger.debug("Authenticating the customer {} with the password {}", login, password);
        return custService.authenticate(login, password);
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        logger.debug("Getting a customer with with id {}", id);
        return beanMappServ.mapTo(custService.findCustomerById(id), CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> findCustomerByName(String name) {
        logger.debug("Getting a customer with with the name {}", name);
        List<Customer> custs = custService.findCustomerByName(name);
        return custs == null ? null : beanMappServ.mapTo(custs, CustomerDTO.class);
    }

    @Override
    public CustomerDTO findCustomerByLogin(String login) {
        logger.debug("Getting a customer with with the username {}", login);
        return beanMappServ.mapTo(custService.findCustomerByLogin(login), CustomerDTO.class);
    }

    @Override
    public List<LoanDTO> findCustomersLoans(CustomerDTO customerDTO) {
        logger.debug("Getting all customer's {} loans", customerDTO);
        Customer customer = beanMappServ.mapTo(customerDTO, Customer.class);
        return beanMappServ.mapTo(custService.findCustomersLoans(customer), LoanDTO.class);
    }
    
}
