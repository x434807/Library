package Interfaces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import src.Customer;
import java.util.List;
/**
 *
 * @author Andrej Sokolík
 */
public interface CustomerDAO extends DAO<Customer>{
    /*
        Return list of all known customers
    */
    List<Customer> getAllCustomers();
    /*
        return specific custemer by selected ID
    */
    Customer findByID(int id);
    /*
        return true if Customer was succesfully added otherwise false
    */
    boolean insertCustomer(Customer customer);
    /*
        return true if selected customer was succesfully removed otherwise false
    */
    boolean deleteCustomer(Customer customer);
    
    
    
}
