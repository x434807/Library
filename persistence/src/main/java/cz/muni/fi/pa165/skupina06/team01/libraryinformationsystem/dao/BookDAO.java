package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Book;

/**
 * @author Matúš Čongrády
 */
public interface BookDAO extends DAO<Book> {
    Book findByName(String name);
}