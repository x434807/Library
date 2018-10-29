package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.BookCondition;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.exceptions.BookNotAvailableException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    protected Loan() {
        items = new ArrayList<>();
    }

    @Override
    public boolean equals(Object other){
        if ((other == null) || !(other instanceof Loan)) {
            return false;
        }
        final Loan loan = (Loan) other;
        return new EqualsBuilder().append(getTimestamp(), loan.getTimestamp())
                .append(getCustomer().getId(), loan.getCustomer().getId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder() .append(getTimestamp())
                .append(getCustomer().getId())
                .toHashCode();
    }

    public boolean addLoanedBook(Book book) throws BookNotAvailableException {
        if (book == null)
            return false;

        if(!book.isAvailable()){
            throw new BookNotAvailableException("Inavailable book cannot be borrowed!");
        }
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

    public void setId(long id) {
        this.id = id;
    }

    public List<LoanItem> getItems() {
        return items;
    }

    private void setItems(List<LoanItem> items){
        this.items = items;
    }

}