package java.cz.fi.muni.pa165.entity;

import javax.persistence.*;
import java.cz.fi.muni.pa165.enums.BookCondition;

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

    @NotNull
    @Column(nullable = false, unique = true)
    private String ISBN;

    @NotNull
    @Column(nullable = false)
    private String author;
    
    @Column(nullable = false)
    private BookCondition condition;
    
    @Column
    private boolean isAvailable = true;

    // Default Constructor
    public Book() {}


    // Getters and setters
    public long getId() {
        return id;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
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
}
