package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.BookDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Book;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service.BookService;

/**
 *
 * @author Matúš Čongrády
 */
public class BookFacadeTest extends AbstractFacadeTest {

    @Mock
    private BookService bookService;

    @Autowired
    @InjectMocks
    private BookFacadeImpl bookFacade;

    private Book book;
    private BookDTO bookDTO;

    private String bookName = "My House";
    private Long bookId = 2L;

    @BeforeMethod
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        bookFacade = (BookFacadeImpl) unwrapProxy(bookFacade);
        ReflectionTestUtils.setField(bookFacade, "bookService", bookService);
        ReflectionTestUtils.setField(bookFacade, "beanMappingService", beanMappingService);

        book = new Book();
        book.setName(bookName);
        book.setId(bookId);

        bookDTO = new BookDTO();
        bookDTO.setName(bookName);
        bookDTO.setId(bookId);
    }

    @Test
    public void testCreateBook() {
        when(beanMappingService.mapTo(bookDTO, Book.class)).thenReturn(book);
        bookFacade.createBook(bookDTO);

        verify(bookService).createBook(book);
        verify(beanMappingService).mapTo(bookDTO, Book.class);
    }

    @Test
    public void testUpdateBook() {
        when(beanMappingService.mapTo(bookDTO, Book.class)).thenReturn(book);
        bookFacade.createBook(bookDTO);

        verify(bookService).createBook(book);
        verify(beanMappingService).mapTo(bookDTO, Book.class);

        bookDTO.setName("Motyl 2");

        bookFacade.updateBook(bookDTO);
        verify(bookService).updateBook(book);
        verify(beanMappingService, times(2)).mapTo(bookDTO, Book.class);
    }

    @Test
    public void testDeleteBook() {
        when(beanMappingService.mapTo(bookDTO, Book.class)).thenReturn(book);
        when(bookService.findBookById(bookId)).thenReturn(book);

        bookDTO.setId(bookId);

        bookFacade.removeBook(bookDTO);
        verify(bookService).removeBook(book);
        verify(beanMappingService).mapTo(bookDTO, Book.class);
    }

    @Test
    public void testGetAll() {
        when(bookService.getAllBooks()).thenReturn(Collections.singletonList(book));
        bookFacade.createBook(bookDTO);

        List<BookDTO> books = bookFacade.getAllBooks();

        assertThat(book.getName()).isEqualTo(books.get(0).getName());
        verify(bookService).getAllBooks();
        verify(beanMappingService).mapTo(Collections.singletonList(book), BookDTO.class);
    }

    @Test
    public void testGetAllWithNull() {
        when(bookService.getAllBooks()).thenReturn(null);

        List<BookDTO> books = bookFacade.getAllBooks();

        assertThat(books).isNull();
        verify(bookService).getAllBooks();
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testGetAllThatNeedRevision() {
        when(bookService.getAllBooksThatNeedRevision()).thenReturn(Collections.singletonList(book));

        List<BookDTO> books = bookFacade.getAllBooksThatNeedRevision();

        assertThat(bookName).isEqualTo(books.get(0).getName());
        verify(bookService).getAllBooksThatNeedRevision();
        verify(beanMappingService).mapTo(Collections.singletonList(book), BookDTO.class);
    }

    @Test
    public void testAllThatNeedRevisionWithNull() {
        when(bookService.getAllBooksThatNeedRevision()).thenReturn(null);
        bookFacade.createBook(bookDTO);

        List<BookDTO> books = bookFacade.getAllBooksThatNeedRevision();

        assertThat(books).isNull();
        verify(bookService).getAllBooksThatNeedRevision();
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindById() {
        when(beanMappingService.mapTo(bookDTO, Book.class)).thenReturn(book);
        when(bookService.findBookById(bookId)).thenReturn(book);
        bookFacade.createBook(bookDTO);

        BookDTO result = bookFacade.findBookById(bookId);

        assertThat(result).isNotNull();
        assertThat(bookName).isEqualTo(result.getName());
        verify(bookService).findBookById(bookId);
        verify(beanMappingService).mapTo(book, BookDTO.class);
    }

    @Test
    public void testFindByIdWithNull() {
        when(bookService.findBookById(bookId)).thenReturn(null);

        BookDTO bookDTO = bookFacade.findBookById(bookId);

        assertThat(bookDTO).isNull();
        verify(bookService).findBookById(bookId);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

    @Test
    public void testFindByName() {
        when(bookService.findBookByName(bookName)).thenReturn(book);
        bookFacade.createBook(bookDTO);

        BookDTO bookDTO = bookFacade.findBookByName(bookName);

        assertThat(bookDTO).isNotNull();
        assertThat(bookName).isEqualTo(bookDTO.getName());
        verify(bookService).findBookByName(bookName);
        verify(beanMappingService).mapTo(book, BookDTO.class);
    }

    @Test
    public void testFindByNameWithNull() {
        when(bookService.findBookByName(bookName)).thenReturn(null);

        BookDTO bookDTO = bookFacade.findBookByName(bookName);

        assertThat(bookDTO).isNull();
        verify(bookService).findBookByName(bookName);
        verify(beanMappingService, never()).mapTo(any(), any());
    }

}
