package src;

import Interfaces.DAO;

public interface BookDAO extends DAO<Book> {
    Book findByName(String name);
}