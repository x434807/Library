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
     * @throws DataAcessException       when there is any problem in accessing Book
     * @throws IllegalArgumentException if the book is not of Book class or is null
     */
    void createBook(Book book) throws DataAccessException, IllegalArgumentException;

    /**
     * Removes the given Book by his ID.
     *
     * @param book is a Book which is going to be removed
     * @throws DataAcessException       when there is any problem in accessing Book
     * @throws IllegalArgumentException if Book is not of Book class or is null
     */
    void removeBook(Book book) throws DataAccessException, IllegalArgumentException;

    /**
     * Updates the given Book.
     *
     * @param book the Book to be updated
     * @throws DataAcessException       when there is any problem with accessing Book
     * @throws IllegalArgumentException if Book is not of Book class or is null
     */
    void updateBook(Book book) throws DataAccessException, IllegalArgumentException;

    /**
     * Finds a book by its ID.
     *
     * @param id the Book's ID
     * @return book with the given id
     * @throws DataAcessException when there is any problem with accessing Book
     * @throws IllegalArgumentException if Book is not Book class or is null
     */
    Book findBookById(Long id) throws DataAccessException, IllegalArgumentException;

    /**
     * Finds a Book by her/his ID.
     *
     * @param id the Book's ID
     * @return book with the given id
     * @throws DataAcessException when there is any problem with accessing Book
     * @throws IllegalArgumentException if Book is not Book class or is null
     */
    Book findBookByName(Long id) throws DataAccessException, IllegalArgumentException;

    /**
     * Returns a list of all Books in the system.
     *
     * @return the list of all Books
     * @throws DataAcessException when there is any problem with accessing Books
     */
    List<Book> getAllBooks() throws DataAccessException;

    /**
     * Finds all loans of a specific Book
     * 
     * @return list of all books that need revision (have condition BAD or UNKOWN)
     * @throws DataAcessException       when there is any problem with accessing Books
     */
    List<Book> getAllBooksThatNeedRevision() throws DataAccessException, IllegalArgumentException;
}
