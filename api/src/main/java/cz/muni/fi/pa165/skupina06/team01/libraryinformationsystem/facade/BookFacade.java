/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade;

import java.util.List;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.BookDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CreateBookDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.EditBookDTO;

/**
 *
 * @author Matúš Čongrády
 */
public interface BookFacade {
    /**
     * Creates a new book in the system.
     *
     * @param book to be created
     * @return the newly created book
     */
    void createBook(BookDTO book);

    void createBook(CreateBookDTO createBookDTO);

    /**
     * Removes the given book by its ID.
     *
     * @param id of the book to be removed
     */
    void removeBook(BookDTO book);

    /**
     * Updates the given book.
     *
     * @param book the book to be updated
     */
    void updateBook(BookDTO book);

    void updateBook(EditBookDTO editBookDTO);

    /**
     * Returns a list of all books in the system.
     *
     * @return the list of all books
     */
    List<BookDTO> getAllBooks();

    /**
     * Returns a list of books in the system, that need revision (have condition BAD or UNKNOWN)
     *
     * @return the list of books that need revision
     */
    List<BookDTO> getAllBooksThatNeedRevision();

    /**
     * Finds a book its ID.
     *
     * @param id the book's ID
     * @return book with given ID
     */
    BookDTO findBookById(Long id);

    /**
     * Finds a book by its name.
     *
     * @param name the book's name
     * @return book with given name
     */
    BookDTO findBookByName(String name);
}
