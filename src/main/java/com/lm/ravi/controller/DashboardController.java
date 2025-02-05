package com.lm.ravi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.lm.ravi.repository.BookRepository;
import com.lm.ravi.service.BookService;

@Controller
public class DashboardController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookRepository bookRepository;
	
	
	@GetMapping("/dashboard")
	public String showDashboard(Model model) {
		int totalBooks=bookService.getTotalBooksCount();
		int totalCopiesAvailable=bookService.getTotalCopiesAvailable();
		
		model.addAttribute("totalBooks", totalBooks);
		model.addAttribute("totalCopiesAvailable", totalCopiesAvailable);
		return "dashboard";
	}

}
