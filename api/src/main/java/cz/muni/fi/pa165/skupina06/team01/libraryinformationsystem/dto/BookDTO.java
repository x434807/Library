/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.BookCondition;

/**
 *
 * @author Matúš Čongrády
 */
public class BookDTO {
    private long id;
    private String ISBN;
    private String name;
    private String author;
    private BookCondition condition;
    private boolean isAvailable = true;
    @JsonIgnoreProperties({"books", "loans"})
    private Customer customer;

    public BookDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookCondition getCondition() {
        return condition;
    }

    public void setCondition(BookCondition condition) {
        this.condition = condition;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        BookDTO bookDTO = (BookDTO) o;

        return new EqualsBuilder()
                .append(id, bookDTO.id)
                .append(isAvailable, bookDTO.isAvailable)
                .append(ISBN, bookDTO.ISBN)
                .append(name, bookDTO.name)
                .append(author, bookDTO.author)
                .append(condition, bookDTO.condition)
                .append(customer, bookDTO.customer)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(ISBN)
                .append(name)
                .append(author)
                .append(condition)
                .append(isAvailable)
                .append(customer)
                .toHashCode();
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                ", ISBN='" + ISBN + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", condition=" + condition +
                ", isAvailable=" + isAvailable +
                ", customer=" + customer +
                '}';
    }

}
