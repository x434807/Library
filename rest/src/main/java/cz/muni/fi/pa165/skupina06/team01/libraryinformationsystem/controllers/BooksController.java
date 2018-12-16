/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.ApiContract;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.BookDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade.BookFacade;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade.CustomerFacade;
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
 * @author Matúš Čongrády
 */
@RestController
@RequestMapping(ApiContract.ROOT_URI_BOOKS)
public class BooksController {

    final static Logger logger = LoggerFactory.getLogger(BooksController.class);
    
    @Inject
    private BookFacade bookFacade;
    
    /**
     * returns all books 
     *
     * @return list of BookDTOs
     * @throws JsonProcessingException
     */

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<BookDTO> getBooks() throws JsonProcessingException {
        
        logger.debug("rest getBooks()");
        return bookFacade.getAllBooks();
    }
    
    /**
     *
     * getting book according to id
     * 
     * @param id user identifier
     * @return BookDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final BookDTO getBook(@PathVariable("id") long id) throws Exception {

        logger.debug("rest getBook({})", id);

        try {
            BookDTO bookDTO = bookFacade.findBookById(id);
            return bookDTO;
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }

    }
}
