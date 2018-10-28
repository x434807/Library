package java.cz.fi.muni.pa165.dao;

import java.cz.fi.muni.pa165.entity.Customer;
import java.util.List;
/**
 *
 * @author Andrej Sokolík
 */
public interface CustomerDAO {
    /**
        Return list of all known customers
    */
    List<Customer> getAllCustomers();
    /*
        return specific customer by selected ID
    */
    Customer findByID(int id);
    /*
        return true if Customer was successfully added otherwise false
    */
    boolean insertCustomer(Customer customer);
    /*
        return true if selected customer was successfully removed otherwise false
    */
    boolean deleteCustomer(Customer customer);
    
}
