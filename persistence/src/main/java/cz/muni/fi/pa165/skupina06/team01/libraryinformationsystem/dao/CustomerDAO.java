package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;

/**
 *
 * @author Andrej Sokolík
 */
public interface CustomerDAO extends DAO<Customer> {
    /*
     * Return customer with specific login
     */
    Customer findByLogin(String login);
}
