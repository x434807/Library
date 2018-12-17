/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.ApiContract;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.LoginDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.exceptions.ResourceNotFoundException;

import java.util.*;
import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public final Map<String, String> createBook(@Valid @RequestBody LoginDTO loginDTO, BindingResult result) {
        if (result.hasErrors()) {
            //throw new Exception("return book error");
        }

        Map<String, String> res = new LinkedHashMap<>();

        try {
            boolean auth = customerFacade.authenticate(loginDTO.getLogin(), loginDTO.getPassword());
            if(auth){
                res.put("successfull", "yes");
                if(loginDTO.getLogin().equals("test123")){
                    res.put("admin", "yes");
                } else {
                    res.put("admin", "no");
                }

                return res;
            }
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return Collections.singletonMap("status", "not ok");
    }

}
