package Interfaces;

import java.time.ZonedDateTime;

/**
 * @author Martin Piatka
 */

public interface LoanDAO  {

    int getID();

    ZonedDateTime getTimestamp();

    String getCustomer();

    String getItems();
}
