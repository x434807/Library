package Interfaces;

import src.Book;

public interface BookDAO extends DAO<Book> {
    Book findByName(String name);
}