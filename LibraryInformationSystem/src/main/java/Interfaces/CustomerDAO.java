package Interfaces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import src.Customer;

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
