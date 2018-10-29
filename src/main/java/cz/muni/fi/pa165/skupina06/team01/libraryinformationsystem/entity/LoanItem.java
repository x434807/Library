package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Martin Piatka
 */

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.BookCondition;

@Entity
@Table
public class LoanItem {

    @Id
    @Column(name = "ID")
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

    public LoanItem(Book book, BookCondition borrowCondition, BookCondition returnCondition) {
        this.book = book;
        this.borrowCondition = borrowCondition;
        this.returnCondition = returnCondition;
    }

    protected LoanItem() {

    }

    @Override
    public boolean equals(Object other) {
        if ((other == null) || !(other instanceof LoanItem)) {
            return false;
        }
        final LoanItem item = (LoanItem) other;
        return new EqualsBuilder().append(getBook().getId(), item.getBook().getId())
                .append(getLoan().getId(), item.getLoan().getId())
                .append(getBorrowCondition(), item.getBorrowCondition())
                .append(getReturnCondition(), item.getReturnCondition()).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getLoan().getId()).append(getBook().getId()).append(getBorrowCondition())
                .append(getReturnCondition()).toHashCode();
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
