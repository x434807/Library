/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Loan;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.Role;
import java.util.List;
/**
 *
 * @author Andrej Sokolík
 */
public interface CustomerFacade {
    /**
     * Creates a new person in the system.
     *
     * @param person to be created
     * @return the newly created person
     */
    CustomerDTO registerCustomer(CustomerDTO person);

    /**
     * Removes the given person by her/his ID.
     *
     * @param id of the person to be removed
     */
    void removeCustomer(Long id);

    /**
     * Updates the given person.
     *
     * @param person the person to be updated
     */
    void updateCustomer(CustomerDTO person);

    /**
     * Returns a list of all people in the system.
     *
     * @return the list of all people
     */
    List<CustomerDTO> getAllPeople();

    /**
     * Authenticates a person in the system.
     *
     * @param login the username of the person to be authenticated
     * @param password the person's password
     * @return true if the authentication was successful, false otherwise
     */
    boolean authenticate(String login, String password);

    /**
     * Checks whether a particular person has the required rights to perform an action.
     *
     * @param person to be used
     * @param accessConstraints the constraints to be checked
     * @return true if the person has the required rights, false otherwise
     */
    boolean isAllowed(CustomerDTO person, List<Role> accessConstraints);

    /**
     * Finds a person by her/his ID.
     *
     * @param id the person's ID
     * @return the found person
     */
    CustomerDTO findPersonById(Long id);

    /**
     * Finds a person by her/his name.
     *
     * @param name the person's name
     * @return the found person
     */
    List<CustomerDTO> findPersonByName(String name);

    /**
     * Finds a person by her/his username in the system.
     *
     * @param login the person's username in the system
     * @return the found person
     */
    CustomerDTO findPersonByLogin(String login);

    /**
     *Finds all loans of a specific customer 
     * 
     * @param customer pecific person
     * @return list of all his loans
     */
    List<Loan> findCustomersLoans(CustomerDTO customer);
}
