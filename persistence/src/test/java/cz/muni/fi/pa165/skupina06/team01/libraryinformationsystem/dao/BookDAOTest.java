package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao;

import static org.assertj.core.api.Assertions.assertThat;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Book;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.BookCondition;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Martin Piatka
 */

public class BookDAOTest extends AbstractDaoTest {

   private Book book;

   @BeforeMethod
   public void setUp(){
      book = new Book();
      book.setAuthor("Tester Testovics");
      book.setName("Testing 101");
      book.setCondition(BookCondition.NEW);
      book.setAvailable(true);
      book.setISBN("ISBN-56468486");
   }

    @Test
    public void testCreate() {
        bookDAO.create(book);
        Long bookId = book.getId();
        assertThat(bookId).isEqualTo(bookDAO.findById(bookId).getId());

        bookDAO.remove(book);
    }

    @Test
    public void testUpdate() {
        book.setName("Testing 201");
        bookDAO.update(book);

        assertThat(book.getName()).isEqualTo("Testing 201");
    }

    @Test
    public void testRemove() {
        bookDAO.remove(book);

        assertThat(bookDAO.findAll().size()).isEqualTo(0);

        bookDAO.create(book);
    }

    @Test
    public void testFindByID() {
        bookDAO.create(book);

        Book bookInDB = bookDAO.findById(book.getId());

        assertThat(bookInDB.getName()).isEqualTo(book.getName());
    }

    @Test
    public void testFindByName() {
        bookDAO.create(book);
        assertThat(bookDAO.findByName("Testing 101")).isEqualTo(book);
        assertThat(bookDAO.findByName("dfgdfgfd")).isNull();
    }

    @Test
    public void testFindAll() {
        bookDAO.create(book);
        assertThat(bookDAO.findAll()).hasSize(1).contains(book);
    }
}
