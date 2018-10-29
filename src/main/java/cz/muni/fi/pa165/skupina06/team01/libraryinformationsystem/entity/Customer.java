package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
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
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Surname", nullable = false)
    private String surname;
    
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToMany(mappedBy = "customer")
    private List<Book> books;

    @OneToMany(mappedBy = "customer")
    private List<Loan> loans;

    public Customer( String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.login = login;
        books = new LinkedList<>();
    }

    public Customer() {
        books = new LinkedList<>();
    }

    //getters and setters
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.login);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", login=" + login + ", password=" + password + ", books=" + books + '}';
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
