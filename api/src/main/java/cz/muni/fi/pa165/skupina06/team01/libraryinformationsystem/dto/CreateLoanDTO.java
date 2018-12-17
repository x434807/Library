package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

/**
 * @author Martin Piatka
 */

public class CreateLoanDTO {
  private String customerLogin;

  private List<Long> bookIds;

  public String getCustomerLogin() {
    return customerLogin;
  }

  public void setCustomerLogin(String customerLogin) {
    this.customerLogin = customerLogin;
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
    return new EqualsBuilder().append(getCustomerLogin(), loan.getCustomerLogin())
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
            "customerLogin=" + customerLogin +
            ", bookIds=" + bookIds +
            "}";
  }
}
