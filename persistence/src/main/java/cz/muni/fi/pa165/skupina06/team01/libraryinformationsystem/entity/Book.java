package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.BookCondition;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author Matúš Čongrády, Andrej Sokolík
 */

@Entity
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String ISBN;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private BookCondition condition;

    @Column
    private boolean isAvailable = true;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    // Default Constructor
    public Book() {
    }

    // Getters and setters
    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
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
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookCondition getCondition() {
        return this.condition;
    }

    public void setCondition(BookCondition condition) {
        this.condition = condition;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", ISBN='" + ISBN + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", condition=" + condition +
                ", isAvailable=" + isAvailable +
                ", customer=" + customer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return new EqualsBuilder()
                .append(id, book.id)
                .append(isAvailable, book.isAvailable)
                .append(ISBN, book.ISBN)
                .append(name, book.name)
                .append(author, book.author)
                .append(condition, book.condition)
                .append(customer, book.customer)
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
}