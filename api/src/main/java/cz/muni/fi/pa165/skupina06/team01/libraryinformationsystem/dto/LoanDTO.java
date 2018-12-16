/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Martin Piatka
 */

public class LoanDTO {

    private long id;
    @JsonIgnoreProperties({"loans", "books"})
    private CustomerDTO customer;
    private String timestamp;
    @JsonIgnoreProperties("loan")
    private List<LoanItemDTO> items;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public List<LoanItemDTO> getItems() {
        return items;
    }

    public void setItems(List<LoanItemDTO> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object other){
        if ((other == null) || !(other instanceof LoanDTO)) {
            return false;
        }
        final LoanDTO loan = (LoanDTO) other;
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

    @Override
    public String toString() {
        return "LoanDTO{" +
                "id=" + id +
                ", customer=" + customer.getId() +
                ", timestamp=" + timestamp +
                "}";
    }
}
