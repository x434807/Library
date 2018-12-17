/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.ApiContract;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.exceptions.ResourceNotFoundException;
import java.util.Collection;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade.CustomerFacade;
/**
 *
 * @author Andrej Sokolík
 */
@RestController
@RequestMapping(ApiContract.ROOT_URI_CUSTOMERS)
public class CustomersController {
    
    final static Logger logger = LoggerFactory.getLogger(CustomersController.class);
    
    @Inject
    private CustomerFacade customerFacade;
    
    /**
     * returns all customers 
     *
     * @return list of CustomerDTOs
     * @throws JsonProcessingException
     */

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<CustomerDTO> getCustomers() throws JsonProcessingException {
        
        logger.debug("rest getCustomers()");
        return customerFacade.getAllPeople();
    }
    
    /**
     *
     * getting customer according to id
     * 
     * @param id user identifier
     * @return CustomerDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO getCustomer(@PathVariable("id") long id) throws Exception {

        logger.debug("rest getCustomer({})", id);

        try {
            CustomerDTO customerDTO = customerFacade.findCustomerById(id);
            return customerDTO;
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }

    }
    
     /**
     *
     * getting customer according to id
     * 
     * @param login user identifier
     * @return CustomerDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CustomerDTO isAdmin(@PathVariable("login") String login) throws Exception {

        logger.debug("rest isAdmin({})", login);

        try {
            CustomerDTO customerDTO = customerFacade.findCustomerByLogin(login);
            return customerDTO;
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }

    }
    
}
