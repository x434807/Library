package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.BookCondition;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 *
 * @author Martin Piatka
 */

public class LoanItemDTO {
    private long id;

    private LoanDTO loan;

    private BookDTO book;

    private BookCondition borrowCondition;

    private BookCondition returnCondition;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LoanDTO getLoan() {
        return loan;
    }

    public void setLoan(LoanDTO loan) {
        this.loan = loan;
    }

    public BookDTO getBook() {
        return book;
    }

    public void setBook(BookDTO book) {
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


    @Override
    public boolean equals(Object other) {
        if ((other == null) || !(other instanceof LoanItemDTO)) {
            return false;
        }
        final LoanItemDTO item = (LoanItemDTO) other;
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

    @Override
    public String toString() {
        return "LoanItemDTO {" +
                "id=" + id +
                ", loan=" + loan +
                ", book=" + book +
                ", borrowCondition=" + borrowCondition +
                ", returnCondition=" + returnCondition +
                "}";
    }
}
