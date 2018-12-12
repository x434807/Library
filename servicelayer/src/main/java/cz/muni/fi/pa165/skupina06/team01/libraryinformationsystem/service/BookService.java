/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Book;

/**
 *
 * @author Matúš Čongrády
 */
public interface BookService {
    /**
     * Creates a new Book in the system.
     *
     * @param book to be created
     * @throws DataAcessException       in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the Book is null or not present in the database
     */
    void createBook(Book book) throws DataAccessException, IllegalArgumentException;

    /**
     * Removes the given Book by his ID.
     *
     * @param book is a Book which is going to be removed
     * @throws DataAcessException       in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the Book is null or not present in the database
     */
    void removeBook(Book book) throws DataAccessException, IllegalArgumentException;

    /**
     * Updates the given Book.
     *
     * @param book the Book to be updated
     * @throws DataAcessException       in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the Book is null or not present in the database
     */
    void updateBook(Book book) throws DataAccessException, IllegalArgumentException;

    /**
     * Finds a book by its ID.
     *
     * @param id the Book's ID
     * @return book with the given id
     * @throws DataAcessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the id is null or not present in the database
     */
    Book findBookById(Long id) throws DataAccessException, IllegalArgumentException;

    /**
     * Finds a Book by its ID.
     *
     * @param name the name of the book
     * @return book with the given name
     * @throws DataAcessException in case of any failure on the persistence layer
     * @throws IllegalArgumentException if the Book is null or not present in the database
     */
    Book findBookByName(String name) throws DataAccessException, IllegalArgumentException;

    /**
     * Returns a list of all Books in the system.
     *
     * @return the list of all Books
     * @throws DataAcessException in case of any failure on the persistence layers
     */
    List<Book> getAllBooks() throws DataAccessException;

    /**
     * Finds all loans of a specific Book
     * 
     * @return list of all books that need revision (have condition BAD or UNKOWN)
     * @throws DataAcessException in case of any failure on the persistence layers
     */
    List<Book> getAllBooksThatNeedRevision() throws DataAccessException, IllegalArgumentException;
}
