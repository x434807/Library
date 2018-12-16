/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author Andrej Sokolík 
 */
public class CustomerDTO {
    private long id;
    private String name;
    private String surname;
    private String login;
    private String password;
    @JsonIgnoreProperties("customer")
    private List<BookDTO> books;
    @JsonIgnoreProperties("customer")
    private List<LoanDTO> loans;
    
    // Getters and Setters

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

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    public List<LoanDTO> getLoans() {
        return loans;
    }

    public void setLoans(List<LoanDTO> loans) {
        this.loans = loans;
    }
    
    //HashCode and Equals

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.login);
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
        final CustomerDTO other = (CustomerDTO) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        return true;
    }

    // to string
    
    @Override
    public String toString() {
        return "CustomerDTO{" 
                + "id=" + id 
                + ", name=" 
                + name + ", surname=" 
                + surname + ", login=" 
                + login + ", password=" 
                + password + ", books=" 
                + books + ", loans=" 
                + loans + '}';
    }
    
    
}
