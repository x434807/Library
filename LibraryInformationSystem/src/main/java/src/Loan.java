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

    public Loan(int id, Customer customer) {
        this(id, customer, ZonedDateTime.now());
    }

    public Loan(int id, Customer customer, ZonedDateTime timestamp) {
        this.id = id;
        this.customer = customer;
        this.timestamp = timestamp;
        items = new ArrayList<LoanItem>();
    }

    public boolean addLoanedBook(Book book){
        if(book == null)
            return false;

        BookCondition borrowCondition = book.getCondition();
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

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<LoanItem> getItems() {
        return items;
    }
}
