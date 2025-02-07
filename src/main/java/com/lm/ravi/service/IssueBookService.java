package com.lm.ravi.service;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lm.ravi.entity.Book;
import com.lm.ravi.entity.IssueBook;
import com.lm.ravi.entity.User;
import com.lm.ravi.repository.BookRepository;
import com.lm.ravi.repository.IssueBookRepository;
import com.lm.ravi.repository.UserRepository;


@Service
public class IssueBookService {
	
	private static final Logger log=LoggerFactory.getLogger(IssueBookService.class);

    @Autowired
    private IssueBookRepository issueBookRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public void issueBook(String userEmail, Long bookId) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepository.save(book);

            IssueBook issue = new IssueBook(user, book, LocalDate.now());
            issueBookRepository.save(issue);
           
        } else {
            throw new RuntimeException("Book not available");
        }
    }
    
    public void returnBook(Long issueId) {
    	IssueBook issue = issueBookRepository.findById(issueId).orElseThrow(() -> new RuntimeException("Issued Book not found"));
    	
    	issueBookRepository.delete(issue);
    	
    	Book book = issue.getBook();
    	book.setAvailableCopies(book.getAvailableCopies() + 1);
    	bookRepository.save(book);
    }

    public List<IssueBook> getAllIssuedBooks() {
        return issueBookRepository.findAll();
    }
    
    public int getIssuedBooksCount() {
    	Integer count=issueBookRepository.getIssuedBooksCount();
    	int issueCount= (count != null)? count.intValue():0;
    	System.out.printf("Issue Count is:%d",issueCount);
    	return issueCount;
    }
}