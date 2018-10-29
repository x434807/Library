package srcTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import src.Book;
import src.BookCondition;
import src.Customer;

/**
 * @author Matúš Čongrády
 */

public class CustomerDAOTestImpl extends AbstractDaoTest {

    private Customer customer;

    @BeforeMethod
    public void setUp() {
        Book book1 = new Book();
        book1.setAuthor("Dan Brown");
        book1.setISBN("0-7432-7506-3");
        book1.setName("Angels & Demons");
        book1.setCondition(BookCondition.GOOD);
        book1.setAvailable(true);

        Book book2 = new Book();
        book1.setAuthor("Dan Brown");
        book1.setISBN("0-5632-7324-8");
        book1.setName("Da Vinci Code");
        book1.setCondition(BookCondition.BAD);
        book1.setAvailable(false);

        customer = new Customer("Matus", "Congrady", "matus.congrady", "heslo123");
        customer.addBorrowedBook(book1);
        customer.addBorrowedBook(book2);
    }

    @Test
    public void testCreate() {
        customerDAO.create(customer);
        Long customerId = customer.getId();
        assertThat(customerId).isEqualTo(customerDAO.findById(customerId).getId());

        customerDAO.remove(customer);
    }

    @Test
    public void testUpdate() {
        customer.setName("Igor");
        customerDAO.update(customer);

        assertThat(customer.getName()).isEqualTo("Igor");
    }

    @Test
    public void testRemove() {
        customerDAO.remove(customer);

        assertThat(customerDAO.findAll().size()).isEqualTo(0);

        customerDAO.create(customer);
    }

    @Test
    public void testFindByID() {
        customerDAO.create(customer);

        Customer customerInDB = customerDAO.findById(customer.getId());

        assertThat(customerInDB.getName()).isEqualTo("Matus");
        assertThat(customerInDB.getBooks().size()).isEqualTo(2);
    }

    @Test
    public void testFindByName() {
        assertThat(customerDAO.findByLogin("matus.congrady")).isEqualTo(customer);
        assertThat(customerDAO.findByLogin("igor.pekar")).isNull();
    }

    @Test
    public void testFindAll() {
        assertThat(customerDAO.findAll()).hasSize(1).contains(customer);
    }
}
