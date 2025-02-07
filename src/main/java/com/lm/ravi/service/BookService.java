package com.lm.ravi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lm.ravi.entity.Book;
import com.lm.ravi.repository.BookRepository;

@Service
public class BookService {
	
	private static final Logger log=LoggerFactory.getLogger(BookService.class);

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public List<Book> getAvailableBooks(){
    	return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    
    public int getTotalBooksCount() {
    	Integer totalBooks=bookRepository.getTotalBooksCount();
    	int totalBook=(totalBooks!= null)? totalBooks.intValue():0;
    	System.out.printf("Total Book:%d",totalBook);
    	return totalBook;
    }
    
    public int getTotalCopiesAvailable() {
    	Integer availableCopies=bookRepository.getTotalCopiesAvailable();
    	
        int available=(availableCopies != null)? availableCopies.intValue() : 0;
        System.out.printf("--Available Copies:%d ---",available);
        return available;
    }
    
    public void updateAvailableCopies(Long bookId, int change) {
    	Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));
    	book.setAvailableCopies(book.getAvailableCopies() + change);
    

    
    }
}