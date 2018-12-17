package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * @author Martin Piatka
 */

public class CreateLoanDTO {
  private Long customerId;

  private List<Long> bookIds;

  public Long getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Long customerId) {
    this.customerId = customerId;
  }

  public List<Long> getBookIds() {
    return bookIds;
  }

  public void setBookIds(List<Long> bookIds) {
    this.bookIds = bookIds;
  }

  @Override
  public boolean equals(Object other){
    if ((other == null) || !(other instanceof CreateLoanDTO)) {
      return false;
    }
    final CreateLoanDTO loan = (CreateLoanDTO) other;
    return new EqualsBuilder().append(getCustomerId(), loan.getCustomerId())
            .append(getBookIds(), loan.getBookIds())
            .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder() .append(getBookIds())
            .append(getBookIds())
            .toHashCode();
  }

  @Override
  public String toString() {
    return "CreateLoanDTO{" +
            "customerId=" + customerId +
            ", bookIds=" + bookIds +
            "}";
  }
}
