package src;

/**
 * @author Martin Piatka
 */

import javax.persistence.*;

@Entity
@Table
public class LoanItem {

    @Id
    @Column(name="LoanItemID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "LoanID")
    private Loan loan;

    @ManyToOne
    @JoinColumn(name = "BookID")
    private Book book;

    @Column(name = "BorrowCondition")
    private BookCondition borrowCondition;

    @Column(name = "ReturnCondition")
    private BookCondition returnCondition;

    public LoanItem(Book book, BookCondition borrowCondition, BookCondition returnCondition){
        this.book = book;
        this.borrowCondition = borrowCondition;
        this.returnCondition = returnCondition;
    }

    protected LoanItem(){

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

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

    public Loan getLoan() {
        return loan;
    }

    private void setLoan(Loan loan) {
        this.loan = loan;
    }
}
