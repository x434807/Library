package src;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Piatka
 */

@Entity
@Table
public class Loan {

    @Id
    @Column(name = "LoanID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "CustomerID")
    private Customer customer;

    @Column(name = "Timestamp")
    private ZonedDateTime timestamp;

    @OneToMany(mappedBy = "loan")
    private List<LoanItem> items;

    public Loan(Customer customer) {
        this(customer, ZonedDateTime.now());
    }

    public Loan(Customer customer, ZonedDateTime timestamp) {
        this.customer = customer;
        this.timestamp = timestamp;
        items = new ArrayList<LoanItem>();
    }

    protected Loan(){
        items = new ArrayList<>();
    }

    public boolean addLoanedBook(Book book){
        if(book == null)
            return false;

        BookCondition borrowCondition = book.getCondition();
        BookCondition returnCondition = BookCondition.UNKNOWN; // Not yet returned
        
        LoanItem item = new LoanItem(book, borrowCondition, returnCondition);
        items.add(item);
        
        customer.addBorrowedBook(book);
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

    public long getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public List<LoanItem> getItems() {
        return items;
    }
}
