package com.lm.ravi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lm.ravi.repository.BookRepository;
import com.lm.ravi.service.BookService;
import com.lm.ravi.service.IssueBookService;

@Controller
public class DashboardController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private IssueBookService issueBookService;
	
	@Autowired
	private BookRepository bookRepository;
	
	
	@GetMapping("/dashboard")
	public String showDashboard(Model model) {
		int totalBooks=bookService.getTotalBooksCount();
		//int totalCopiesAvailable=bookService.getTotalCopiesAvailable();
		
		int issuedBooks= issueBookService.getIssuedBooksCount();
		int availableBooks=(totalBooks-issuedBooks);
		System.out.println("From DashboardController:"+availableBooks);
		model.addAttribute("totalBooks", totalBooks);
		model.addAttribute("availableBooks", availableBooks);
		model.addAttribute("issuedBooks", issuedBooks);
		return "dashboard";
	}

}
