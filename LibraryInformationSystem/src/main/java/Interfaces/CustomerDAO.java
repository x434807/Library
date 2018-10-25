/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

/**
 *
 * @author Anry
 */
public interface CustomerDAO {
    
    /*
        Return ID of specifik Customer
    */
    int getID();
    
    /*
        Return full name of Customer
    */
    String getName();
    
    /*
        Return String wich contains all borrowed books
    */
    String borrowedBooks();
    
    
    
}
