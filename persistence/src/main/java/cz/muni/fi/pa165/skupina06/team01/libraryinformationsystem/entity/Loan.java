package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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
    private String timestamp;

    @OneToMany(mappedBy = "loan")
    private List<LoanItem> items;

    public Loan(Customer customer) {
        this(customer, Instant.now().toString());
    }

    public Loan(Customer customer, String timestamp) {
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

    public void addLoanItem(LoanItem item){
        items.add(item);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
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
