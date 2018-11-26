package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao.BookDAO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Book;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.BookCondition;

/**
 *
 * @author Matúš Čongrády
 */
public class BookServiceTest extends AbstractServiceTest {

    @Mock
    private BookDAO bookDao;

    @InjectMocks
    @Autowired
    private BookServiceImpl bookService;

    private Book book1;
    private Book book2;
    private Book book3;
    private Book book4;

    private List<Book> listOfBooks = new ArrayList<Book>();
    private List<Book> listOfBooksThatNeedRevision = new ArrayList<Book>();
    private List<Book> emptyList = Collections.emptyList();

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        book1 = new Book();
        book1.setAuthor("Author 1");
        book1.setName("BookName 1");
        book1.setCondition(BookCondition.NEW);
        book1.setAvailable(true);
        book1.setISBN("ISBN-1");
    
        book2 = new Book();
        book2.setAuthor("Author 2");
        book2.setName("BookName 2");
        book2.setCondition(BookCondition.UNKNOWN);
        book2.setAvailable(false);
        book2.setISBN("ISBN-2");

        book3 = new Book();
        book3.setAuthor("Author 3");
        book3.setName("BookName 3");
        book3.setCondition(BookCondition.GOOD);
        book3.setAvailable(true);
        book3.setISBN("ISBN-3");

        book4 = new Book();
        book4.setAuthor("Author 4");
        book4.setName("BookName 4");
        book4.setCondition(BookCondition.BAD);
        book4.setAvailable(true);
        book4.setISBN("ISBN-4");

        listOfBooks.add(book1);
        listOfBooks.add(book2);
        listOfBooks.add(book3);
        listOfBooks.add(book4);

        listOfBooksThatNeedRevision.add(book2);
        listOfBooksThatNeedRevision.add(book4);
    }

    @Test
    public void findAllWithEmptyListReturnsEmptyListTest() {
        when(bookDao.findAll()).thenReturn(emptyList);

        assertThat(bookService.getAllBooks()).isEqualTo(emptyList);
    }

    @Test
    public void findByIdWithWrongIdReturnsNullTest() {
        Long id = 0l;
        when(bookDao.findById(id)).thenReturn(null);

        assertThat(bookService.findBookById(id)).isNull();
    }

    @Test
    public void findByIdWithCorrectIdReturnsBookTest() {
        Long id = 0l;
        when(bookDao.findById(id)).thenReturn(book1);

        assertThat(bookService.findBookById(id)).isEqualTo(listOfBooksThatNeedRevision);
    }

    @Test
    public void findBooksThatNeedRevisionTest() {
        when(bookDao.findAll()).thenReturn(listOfBooks);

        assertThat(bookService.getAllBooksThatNeedRevision()).isEqualTo(listOfBooksThatNeedRevision);
    }

    @Test
    public void findByNameReturnsNullTest() {
        String bookName = "Non-existing-book";
        when(bookDao.findByName(bookName)).thenReturn(null);

        assertThat(bookService.findBookByName(bookName)).isNull();
    }

    @Test
    public void findByNameReturnsBookTest() {
        String bookName = "BookName 1";
        when(bookDao.findByName(bookName)).thenReturn(book1);

        assertThat(book1).isEqualTo(bookService.findBookByName(bookName));
    }

    @Test
    public void createBookTest() {
        bookService.createBook(book1);
        verify(bookDao).create(book1);
    }

    @Test
    public void updateBookTest() {
        bookService.createBook(book1);
        verify(bookDao).create(book1);

        book1.setAuthor("New author");

        bookService.updateBook(book1);
        verify(bookDao).update(book1);
    }

    @Test
    public void deleteBookTest() {
        bookService.createBook(book1);
        verify(bookDao).create(book1);

        bookService.removeBook(book1);
        verify(bookDao).remove(book1);
    }

}
