/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.LoanDTO;
import java.util.List;
/**
 *
 * @author Andrej Sokolík
 */
public interface CustomerFacade {
    /**
     * Creates a new customer in the system.
     *
     * @param customer to be created
     * @return the newly created person
     */
    CustomerDTO registerCustomer(CustomerDTO customer);

    /**
     * Removes the given customer by his ID.
     *
     * @param customer the customer to be removed
     */
    void removeCustomer(CustomerDTO customer);

    /**
     * Updates the given customer.
     *
     * @param customer the customer to be updated
     */
    void updateCustomer(CustomerDTO customer);

    /**
     * Returns a list of all customers in the system.
     *
     * @return the list of all customers
     */
    List<CustomerDTO> getAllPeople();

    /**
     * Authenticates a customer in the system.
     *
     * @param login the username of the person to be authenticated
     * @param password the person's password
     * @return true if the authentication was successful, false otherwise
     */
    boolean authenticate(String login, String password);


    /**
     * Finds a customer by her/his ID.
     *
     * @param id the customer's ID
     * @return the found person
     */
    CustomerDTO findCustomerById(Long id);

    /**
     * Finds a customer by his name.
     *
     * @param name the person's name
     * @return the found person
     */
    List<CustomerDTO> findCustomerByName(String name);

    /**
     * Finds a customer by his username in the system.
     *
     * @param login the person's username in the system
     * @return the found person
     */
    CustomerDTO findCustomerByLogin(String login);

    /**
     *Finds all loans of a specific customer 
     * 
     * @param customer pecific person
     * @return list of all his loans
     */
    List<LoanDTO> findCustomersLoans(CustomerDTO customer);
}
