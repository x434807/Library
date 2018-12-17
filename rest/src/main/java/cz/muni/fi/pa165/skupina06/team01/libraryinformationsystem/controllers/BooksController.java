/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.controllers;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;

import com.fasterxml.jackson.core.JsonProcessingException;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CreateBookDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.ApiContract;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.BookDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade.BookFacade;;

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
     * get book by id
     * 
     * @param id book identifier
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

    /**
     * finds book by it's name
     *
     * @param name book name
     * @return BookDTO
     * @throws JsonProcessingException
     */
    @RequestMapping(value = "/search/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final BookDTO findBookByName(@PathVariable("name") String name) throws JsonProcessingException {
        logger.debug("rest findBookByName({})", name);
        try {
            BookDTO bookDTO = bookFacade.findBookByName(name);
            return bookDTO;
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public final Map<String, String> returnBook(@Valid @RequestBody CreateBookDTO createBookDTO, BindingResult result) {
        if (result.hasErrors()) {
            //throw new Exception("return book error");
        }

        try {
            bookFacade.createBook(createBookDTO);
            return Collections.singletonMap("status", "ok");
        } catch (DataAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return Collections.singletonMap("status", "not ok");
    }

}
