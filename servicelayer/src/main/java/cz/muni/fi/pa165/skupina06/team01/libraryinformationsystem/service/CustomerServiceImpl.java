package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao.CustomerDAO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Loan;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej Sokolík
 */

@Service
public class CustomerServiceImpl implements CustomerService{

    @Inject
    private CustomerDAO customerDao;
    
    @Override
    public void registerCustomer(Customer customer) throws DataAccessException, IllegalArgumentException {
        if(customer == null){
            throw new IllegalArgumentException("Customer cannot be null!");
        }
        customerDao.create(customer);
    }

    @Override
    public void removeCustomer(Customer customer) throws DataAccessException, IllegalArgumentException {
        if(customer == null){
            throw new IllegalArgumentException("Customer cannot be null!");
        }
        customerDao.remove(customer);
    }

    @Override
    public void updateCustomer(Customer customer) throws DataAccessException, IllegalArgumentException {
        if(customer == null){
            throw new IllegalArgumentException("Customer cannot be null!");
        }
        customerDao.update(customer);
    }

    @Override
    public List<Customer> getAllCustomers() throws DataAccessException {
        return customerDao.findAll();
    }

    @Override
    public boolean authenticate(String login, String password) throws DataAccessException, IllegalArgumentException {
        Customer customer = findCustomerByLogin(login);
        return (customer.getPassword() == null ? password == null : customer.getPassword().equals(password));
    }

    

    @Override
    public Customer findCustomerById(Long id) throws DataAccessException, IllegalArgumentException {
        if(id == null){
            throw new IllegalArgumentException("ID cannot be null!");
        }
        return customerDao.findById(id);
    }

    @Override
    public List<Customer> findCustomerByName(String name) throws DataAccessException, IllegalArgumentException {
        return null;
    }

    @Override
    public Customer findCustomerByLogin(String login) throws DataAccessException, IllegalArgumentException {
        if(login.equals(null)){
            throw new IllegalArgumentException("Empty login is not allowed!");
        }
        return customerDao.findByLogin(login);
    }

    @Override
    public List<Loan> findCustomersLoans(Customer customer) throws DataAccessException, IllegalArgumentException {
        if(customer == null){
            throw new IllegalArgumentException("Customer cannot be null!");
        }
        return customer.getLoans();
    }
    
}
