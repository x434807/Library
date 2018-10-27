package src;

/**
 * @author Martin Piatka
 */

import javax.persistence.*;

@Entity
@Table
public class LoanItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "LOAN_ID")
    private Loan loan;

    private Book book;
    private BookCondition borrowCondition;
    private BookCondition returnCondition;

    LoanItem(Book book, BookCondition borrowCondition, BookCondition returnCondition){
        this.book = book;
        this.borrowCondition = borrowCondition;
        this.returnCondition = returnCondition;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookCondition getBorrowCondition() {
        return borrowCondition;
    }

    public void setBorrowCondition(BookCondition borrowCondition) {
        this.borrowCondition = borrowCondition;
    }

    public BookCondition getReturnCondition() {
        return returnCondition;
    }

    public void setReturnCondition(BookCondition returnCondition) {
        this.returnCondition = returnCondition;
    }
}
