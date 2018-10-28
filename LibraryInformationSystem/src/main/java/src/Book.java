package src;

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
    @Column(name="BookID")
    private int bookID;
    
    @Column(name="ISBN")
    private String ISBN;
    
    @Column (name = "Authors")
    private String authors;
    
    @Column(name = "Condition")
    private BookCondition condition;
    
    @Column(name = "Status")
    private BookStatus status; // true if book is borrowed

    public Book(String ISBN, String authors, BookCondition condition) {
        this.ISBN = ISBN;
        this.authors = authors;
        this.condition = condition;
        status = BookStatus.FREE; // now registred book cannot be borrowed
    }

    public void setCondition(BookCondition condition) {
        this.condition = condition;
    }

    public BookCondition getCondition() {
        return condition;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
    
    
    
}
