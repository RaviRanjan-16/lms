package com.lm.ravi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lm.ravi.entity.IssueBook;
import com.lm.ravi.service.BookService;
import com.lm.ravi.service.IssueBookService;
import com.lm.ravi.service.UserService;

@Controller
@RequestMapping("/issuebooks")
public class IssueBookController {

    @Autowired
    private IssueBookService issueBookService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

  
    @GetMapping("/issue")
    public String showIssueForm(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("books", bookService.getAvailableBooks());
        List<IssueBook> issued =  issueBookService.getAllIssuedBooks();
        model.addAttribute("issued",issued);
        System.out.println("********"+issued);
       
        return "issuebooks"; 
    }

    
    @PostMapping("/issue")
    public String issueBook(@RequestParam String userEmail, @RequestParam Long bookId, Model model) {
        try {
            issueBookService.issueBook(userEmail, bookId);
            List<IssueBook> issued =  issueBookService.getAllIssuedBooks();
            System.out.println("********"+issued);
//            model.addAttribute("issued",issued);
            model.addAttribute("success", "Book issued successfully.");
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/issuebooks/issue";
    }

    
    @PostMapping("/return")
    public String returnBook(@RequestParam Long issueId) {
    	issueBookService.returnBook(issueId);
    	return "redirect:/issuebooks/issued";
    }
    
    
    @GetMapping("/issued")
    public String showIssuedBooks(Model model) {
        List<IssueBook> issuedBooks = issueBookService.getAllIssuedBooks();
        model.addAttribute("issuedBooks", issuedBooks);
        return "issued_books"; 
    }
}