/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Loan;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.Role;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Andrej Sokolík
 */
public interface CustomerService {
    /**
     * Creates a new customer in the system.
     *
     * @param customer to be created
     * @throws DataAcessException when there is any problem in Customer
     * @throws IllegalArgumentException if customer is not customer class or is null
     */
    void registerCustomer(Customer customer)throws DataAccessException,IllegalArgumentException;

    /**
     * Removes the given customer by his ID.
     *
     * @param id of the person to be removed
     * @throws DataAcessException when there is any problem in Customer
     * @throws IllegalArgumentException if customer is not customer class or is null
     */
    void removeCustomer(Long id)throws DataAccessException,IllegalArgumentException;

    /**
     * Updates the given customer.
     *
     * @param customer the customer to be updated
     * @throws DataAcessException when there is any problem in Customer
     * @throws IllegalArgumentException if customer is not customer class or is null
     */
    void updateCustomer(Customer customer)throws DataAccessException,IllegalArgumentException;

    /**
     * Returns a list of all customers in the system.
     *
     * @return the list of all customers
     * @throws DataAcessException when there is any problem in Customer
     */
    List<Customer> getAllPeople()throws DataAccessException;

    /**
     * Authenticates a customer in the system.
     *
     * @param login the username of the person to be authenticated
     * @param password the person's password
     * @return true if the authentication was successful, false otherwise
     * @throws DataAcessException when there is any problem in Customer
     * @throws IllegalArgumentException if customer is not customer class or is null
     */
    boolean authenticate(String login, String password)throws DataAccessException,IllegalArgumentException;

    /**
     * Checks whether a particular customer has the required rights to perform an action.
     *
     * @param person to be used
     * @param accessConstraints the constraints to be checked
     * @return true if the person has the required rights, false otherwise
     * @throws DataAcessException when there is any problem in Customer
     * @throws IllegalArgumentException if customer is not customer class or is null
     */
    boolean isAllowed(CustomerDTO person, List<Role> accessConstraints)throws DataAccessException,IllegalArgumentException;

    /**
     * Finds a customer by her/his ID.
     *
     * @param id the customer's ID
     * @return the found person@throws DataAcessException when there is any problem in Customer
     * @throws IllegalArgumentException if customer is not customer class or is null
     */
    Customer findCustomerById(Long id)throws DataAccessException,IllegalArgumentException;

    /**
     * Finds a customer by his name.
     *
     * @param name the person's name
     * @return the found person
     * @throws DataAcessException when there is any problem in Customer
     * @throws IllegalArgumentException if customer is not customer class or is null
     */
    List<Customer> findCustomerByName(String name)throws DataAccessException,IllegalArgumentException;

    /**
     * Finds a customer by his username in the system.
     *
     * @param login the person's username in the system
     * @return the found person
     * @throws DataAcessException when there is any problem in Customer
     * @throws IllegalArgumentException if customer is not customer class or is null
     */
    Customer findCustomerByLogin(String login)throws DataAccessException,IllegalArgumentException;

    /**
     *Finds all loans of a specific customer 
     * 
     * @param customer pecific person
     * @return list of all his loans
     * @throws DataAcessException when there is any problem in Customer
     * @throws IllegalArgumentException if customer is not customer class or is null
     */
    List<Loan> findCustomersLoans(Customer customer)throws DataAccessException,IllegalArgumentException;
}
