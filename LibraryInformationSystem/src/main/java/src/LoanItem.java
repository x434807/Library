package src;

/**
 * @author Martin Piatka
 */

public class LoanItem {
    Book book;
    BookCondition borrowCondition;
    BookCondition returnCondition;

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
