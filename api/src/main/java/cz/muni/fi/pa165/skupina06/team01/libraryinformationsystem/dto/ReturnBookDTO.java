package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.BookCondition;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ReturnBookDTO {
    private Long bookId;

    private BookCondition returnCondition;

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public BookCondition getReturnCondition() {
        return returnCondition;
    }

    public void setReturnCondition(BookCondition returnCondition) {
        this.returnCondition = returnCondition;
    }

    @Override
    public boolean equals(Object other){
        if ((other == null) || !(other instanceof ReturnBookDTO)) {
            return false;
        }
        final ReturnBookDTO returnBookDTO = (ReturnBookDTO) other;
        return new EqualsBuilder().append(getBookId(), returnBookDTO.getBookId())
                .append(getReturnCondition(), returnBookDTO.getReturnCondition())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder() .append(getBookId())
                .append(getReturnCondition())
                .toHashCode();
    }

    @Override
    public String toString() {
        return "ReturnBookDTO{" +
                "bookId=" + bookId +
                ", returnCondition=" + returnCondition +
                "}";
    }

}
