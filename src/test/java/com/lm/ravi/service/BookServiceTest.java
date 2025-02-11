package com.lm.ravi.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lm.ravi.entity.Book;
import com.lm.ravi.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book1, book2;
    private Long bookId1;
    @BeforeEach
    void setUp() {
        bookId1 = 1L;
        book1 = new Book();
        book2 = new Book();
    }

    // Test getAllBooks method
    @Test
    void testGetAllBooks() {
        // Given: BookRepository returns a list of books
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        // When: Calling getAllBooks()
        List<Book> books = bookService.getAllBooks();

        // Then: Verify the result contains both books
        assertNotNull(books);
        assertEquals(2, books.size());
//        assertEquals(title1, books.get(0).getTitle());
//        assertEquals(title2, books.get(1).getTitle());
    }

    // Test getAvailableBooks method
    @Test
    void testGetAvailableBooks() {
        // Given: BookRepository returns available books
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        // When: Calling getAvailableBooks()
        List<Book> books = bookService.getAvailableBooks();

        // Then: Ensure correct books are returned
        assertNotNull(books);
        assertEquals(2, books.size());
    }

    // Test saveBook method
    @Test
    void testSaveBook() {
        // Given: Saving a book
        when(bookRepository.save(book1)).thenReturn(book1);

        // When: Calling saveBook()
        Book savedBook = bookService.saveBook(book1);

        // Then: Ensure book is saved correctly
        assertNotNull(savedBook);
//        assertEquals(bookId1, savedBook.getId());
//        assertEquals(title1, savedBook.getTitle());
    }

    // Test getTotalBooksCount method
    @Test
    void testGetTotalBooksCount() {
        // Given: Mock the repository's count method
        when(bookRepository.getTotalBooksCount()).thenReturn(10);

        // When: Calling getTotalBooksCount()
        int totalBooks = bookService.getTotalBooksCount();

        // Then: Ensure correct total count
        assertEquals(10, totalBooks);
    }

    @Test
    void testGetTotalBooksCount_WhenNull() {
        // Given: Repository returns null
        when(bookRepository.getTotalBooksCount()).thenReturn(null);

        // When: Calling getTotalBooksCount()
        int totalBooks = bookService.getTotalBooksCount();

        // Then: Should return 0
        assertEquals(0, totalBooks);
    }

    // Test getTotalCopiesAvailable method
    @Test
    void testGetTotalCopiesAvailable() {
        // Given: Mock available copies count
        when(bookRepository.getTotalCopiesAvailable()).thenReturn(5);

        // When: Calling getTotalCopiesAvailable()
        int availableCopies = bookService.getTotalCopiesAvailable();

        // Then: Ensure correct available count
        assertEquals(5, availableCopies);
    }

    @Test
    void testGetTotalCopiesAvailable_WhenNull() {
        // Given: Repository returns null
        when(bookRepository.getTotalCopiesAvailable()).thenReturn(null);

        // When: Calling getTotalCopiesAvailable()
        int availableCopies = bookService.getTotalCopiesAvailable();

        // Then: Should return 0
        assertEquals(0, availableCopies);
    }

    // Test updateAvailableCopies method
    @Test
    void testUpdateAvailableCopies_WhenBookExists() {
        // Given: Book exists
        when(bookRepository.findById(bookId1)).thenReturn(Optional.of(book1));

        // When: Update available copies
        bookService.updateAvailableCopies(bookId1, 3);

        // Then: Ensure available copies are updated
        verify(bookRepository, times(1)).findById(bookId1);
    }

    @Test
    void testUpdateAvailableCopies_WhenBookNotFound() {
        // Given: Book does not exist
        when(bookRepository.findById(bookId1)).thenReturn(Optional.empty());

        // When: Trying to update copies
        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookService.updateAvailableCopies(bookId1, 3);
        });

        // Then: Ensure exception is thrown
        assertEquals("Book not found", exception.getMessage());
    }
}