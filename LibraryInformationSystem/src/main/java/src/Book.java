package src;

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
class Book {
    @Id
    @Column(name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    
    @Column(name="ISBN", unique = true, nullable = false)
    private String ISBN;
    
    @Column(name="name", nullable = false)
    private String name;
    
    @Column (name = "Authors")
    private String authors;
    
    @Column(name = "Condition", nullable = false)
    private BookCondition condition;
    
    @Column(name = "Status", nullable = false)
    private boolean isAvailable;

    public Book(String ISBN, String name, String authors, BookCondition condition) {
        this.ISBN = ISBN;
        this.authors = authors;
        this.condition = condition;
        isAvailable = true; // now registred book should be available
    }

    public Book() {
    }

    
    //Getters and Setters
    
    public long getID() {
        return ID;
    }

    public void setID(long bookID) {
        this.ID = bookID;
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
    
    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setCondition(BookCondition condition) {
        this.condition = condition;
    }

    public BookCondition getCondition() {
        return condition;
    }

    public void setIsAvailable(boolean status) {
        this.isAvailable = status;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.ISBN);
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
        final Book other = (Book) obj;
        if (!Objects.equals(this.ISBN, other.ISBN)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Book{" + "ID=" + ID + ", ISBN=" + ISBN + ", name=" + name + ", authors=" + authors + ", condition=" + condition + ", status=" + isAvailable + '}';
    }
    
    
    
}
