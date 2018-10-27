package src;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej Sokolík
 */
class Book {
    String ISBN;
    BookCondition condition;
    boolean status; // true if book is borrowed

    public Book(String ISBN, BookCondition condition) {
        this.ISBN = ISBN;
        this.condition = condition;
        status = false; // now registred book cannot be borrowed
    }

    public void setCondition(BookCondition condition) {
        this.condition = condition;
    }

    public BookCondition getCondition() {
        return condition;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
}
