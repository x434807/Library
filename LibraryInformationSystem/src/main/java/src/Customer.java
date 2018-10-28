package src;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Andrej Sokolík
 */
@Entity
@Table
public class Customer {

    @Id
    @Column(name = "CustomerID")
    int cust_id;

    @Column(name = "Name")
    String name;

    @Column(name = "Surname")
    String surname;

    @Column(name = "password")
    String password;

    List<Book> books;

    public Customer(int id, String name, String surname, String password) {
        this.cust_id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        books = new LinkedList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
        @return String wich contains id;name;surname;book ids
        null values will be reprezented by '-'
     */
    public String getComplexCustomerInfo() {
        String result = "";
        result += String.valueOf(cust_id) + ";";

        if (name.isEmpty()) {
            result += "-;";
        } else {
            result += (name + ";");
        }

        if (surname.isEmpty()) {
            result += "-;";
        } else {
            result += (surname + ";");
        }

        for (Book book : books) {
            // book class has to be done first
        }
        return result;
    }

    /*
        @param newBorrowedBook is new Book wich should be added to list od borrowed book
        @return if succesfully added return true otherwise return false
     */
    public boolean addBorrowedBook(Book newBorrowedBook) {
        if (newBorrowedBook == null) {
            return false;
        }
        books.add(newBorrowedBook);
        return true;
    }
}
