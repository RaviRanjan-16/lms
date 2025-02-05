package com.lm.ravi.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lm.ravi.entity.Book;
import com.lm.ravi.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	
	@Mock
	private BookRepository bookRepository;
	
	@InjectMocks
	private BookService bookService;
	
//	
//	@BeforeEach
//	public void init() {
//		System.out.println("i");
//	}
//	
//	
	@Test
	public void testgetAllBooks() {
//		System.out.println(this.bookRepository);
//		System.out.println(this.bookService);
//		assertEquals("hi", "hi");
		
		Book book = new Book();
		book.setAuthor("abc");
		when(this.bookRepository.save(book)).thenReturn(book);
		
		Book b=this.bookService.saveBook(book);
		
		assertNotNull(b);
		
	}

}
