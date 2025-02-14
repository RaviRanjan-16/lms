package com.lm.ravi.controller;

import com.lm.ravi.entity.Book;
import com.lm.ravi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Display Add Book Page
    @GetMapping("/add")
    public String showAddBookPage(Model model) {
    	
        model.addAttribute("book", new Book());
        model.addAttribute("books", bookService.getAllBooks()); // Load all books
        return "addbooks"; // Thyme-leaf page
    }

    // Save book when form is submitted
    @PostMapping("/save")
    public String addBook(@ModelAttribute("book") Book book, RedirectAttributes ra) {
    	
    	book.setTotalCopies(book.getAvailableCopies());
    	System.out.println(book);
        bookService.saveBook(book);
        ra.addFlashAttribute("addbookmsg","Book Added Successfully!");
        return "redirect:/books/add"; // Redirect to the same page after saving
    }
}