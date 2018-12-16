/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.ApiContract;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.LoanDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade.LoanFacade;
import java.util.Collection;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Martin Piatka
 */
@RestController
@RequestMapping(ApiContract.ROOT_URI_LOANS)
public class LoansController {
    final static Logger logger = LoggerFactory.getLogger(LoansController.class);
    
    @Inject
    private LoanFacade loanFacade;
    
    /**
     * returns all loans 
     *
     * @return list of LoanDTOs
     * @throws JsonProcessingException
     */

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<LoanDTO> getLoans() throws JsonProcessingException {
        
        logger.debug("rest getLoans()");
        return loanFacade.findAll();
    }
    
    /**
     *
     * getting loan according to id
     * 
     * @param id user identifier
     * @return LoanDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final LoanDTO getLoan(@PathVariable("id") long id) throws Exception {

        logger.debug("rest getLoan({})", id);

        try {
            LoanDTO loanDTO = loanFacade.findById(id);
            return loanDTO;
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }

    }
}