/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade;

import java.util.List;

import javax.transaction.Transactional;

import javax.inject.Inject;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CreateBookDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.EditBookDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.BookCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.BookDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Book;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service.BeanMappingService;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service.BookService;

/**
 *
 * @author Matúš Čongrády
 */

@Service
@Transactional
public class BookFacadeImpl implements BookFacade {
    private static final Logger logger = LoggerFactory.getLogger(CustomerFacadeImpl.class);

    @Inject
    private BookService bookService;
    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public void createBook(BookDTO bookDTO) {
        logger.debug("Creating the book {}", bookDTO);
        Book book = beanMappingService.mapTo(bookDTO, Book.class);
        bookService.createBook(book);
    }

    @Override
    public void createBook(CreateBookDTO createBookDTO) {
        Book book = new Book();
        book.setCondition(BookCondition.NEW);
        book.setAvailable(true);
        book.setName(createBookDTO.getName());
        book.setAuthor(createBookDTO.getAuthor());
        book.setISBN(createBookDTO.getISBN());
        System.out.print("Creating book with ISBN");
        System.out.println(book);
        System.out.println(createBookDTO);

        bookService.createBook(book);
    }

    @Override
    public void removeBook(BookDTO bookDTO) {
        logger.debug("Removing the book with ID {}", bookDTO.getId());
        Book book = beanMappingService.mapTo(bookDTO, Book.class);
        bookService.removeBook(book);
    }

    @Override
    public void updateBook(BookDTO bookDTO) {
        logger.debug("Updating the book {}", bookDTO);
        Book book = beanMappingService.mapTo(bookDTO, Book.class);
        bookService.updateBook(book);
    }

    @Override
    public void updateBook(EditBookDTO editBookDTO) {
        Book book = bookService.findBookById(editBookDTO.getId());

        book.setISBN(editBookDTO.getISBN());
        book.setName(editBookDTO.getName());
        book.setAuthor(editBookDTO.getAuthor());

        bookService.updateBook(book);
    }

    @Override
    public void discardBook(BookDTO bookDTO) {
        Book book = bookService.findBookById(bookDTO.getId());

        book.setCondition(BookCondition.DISCARDED);

        bookService.updateBook(book);
    }

    @Override
    public BookDTO findBookById(Long id) {
        logger.debug("Getting a book with with id {}", id);
        return beanMappingService.mapTo(bookService.findBookById(id), BookDTO.class);
    }

    @Override
    public BookDTO findBookByName(String name) {
        logger.debug("Getting a book with with name {}", name);
        return beanMappingService.mapTo(bookService.findBookByName(name), BookDTO.class);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        logger.debug("Getting all books");
        List<Book> books = bookService.getAllBooks();
        if (books == null) {
            return null;
        }
        return beanMappingService.mapTo(books, BookDTO.class);
    }

    @Override
    public List<BookDTO> getAllBooksThatNeedRevision() {
        logger.debug("Getting all books that need revision");
        List<Book> books = bookService.getAllBooksThatNeedRevision();
        if (books == null) {
            return null;
        }
        return beanMappingService.mapTo(books, BookDTO.class);
    }

}
