package java.cz.fi.muni.pa165.dao;

import java.cz.fi.muni.pa165.entity.Book;

public interface BookDAO extends DAO<Book> {
    Book findByName(String name);
}
