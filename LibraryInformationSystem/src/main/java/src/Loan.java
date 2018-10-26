package src;

import java.time.ZonedDateTime;
import java.util.List;

/**
 *
 * @author Martin Piatka
 */

public class Loan {
    int id;
    Customer customer;
    ZonedDateTime timestamp;
    List<LoanItem> items;

    public Loan(int id, Customer customer, ZonedDateTime timestamp) {
        this.id = id;
        this.customer = customer;
        this.timestamp = timestamp;
    }

    public boolean addLoanedBook(Book book){
        if(book == null)
            return false;

        BookCondition borrowCondition = BookCondition.UNKNOWN; // Will be taken from book
        BookCondition returnCondition = BookCondition.UNKNOWN; // Not yet returned
        
        LoanItem item = new LoanItem(book, borrowCondition, returnCondition);
        items.add(item);

        return true;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
