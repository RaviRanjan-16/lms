package com.lm.ravi.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.lm.ravi.entity.Admin;
import com.lm.ravi.repository.AdminRepository;
import com.lm.ravi.service.AdminService;
import com.lm.ravi.service.BookService;
import com.lm.ravi.service.IssueBookService;

import jakarta.servlet.http.HttpSession;

@Controller //handles web requests 
@RequestMapping("/admin") //This means all URLs in this class will start with /admin
@CrossOrigin(origins = "*") //allow frontend application to access this backend API
public class AdminController {
	
	@Autowired //injects spring-managed objects automatically
	private AdminService adminService; //calls business logic function
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private IssueBookService issueBookService;
	
	
	//initialize admin(first-time-setup)
	@GetMapping("/initialize")          
	public String initializeAdmin() {
		adminService.initializeAdmin();
		return "login";
	}
	
	//show login page
	@GetMapping("/loginAdmin")         
	public String loginPage(Model model) {
		return "login";               
	}
	
	
	//handle login
	@PostMapping("/login")    
	public String  loginAdmin(@RequestParam String email, @RequestParam String password, Model model,HttpSession session) {
							
		Optional<Admin> adminOpt=adminService.getAdminByEmail(email);
		if(adminOpt.isPresent() && adminOpt.get().getPassword().equals(password)) {
			session.setAttribute("adminEmail", adminOpt.get().getEmail());
			session.setAttribute("adminName", "Ravi");
			return "redirect:/admin/dashboard";
		}
		
		else {
			model.addAttribute("error", "Invalid Email or Password");
			return "login";
		}		

	}
		
	
	@GetMapping("/dashboard")        
	public String showDashboard(Model model,HttpSession session) {
		if(session.getAttribute("adminEmail")==null) {
			return "redirect:/admin/loginAdmin";
		}
		int totalBooks=bookService.getTotalBooksCount();
		int issuedBooks= issueBookService.getIssuedBooksCount();
	
		int availableBooks=(totalBooks-issuedBooks);
		
		
		model.addAttribute("totalBooks", totalBooks);
		model.addAttribute("issuedBooks", issuedBooks);
		model.addAttribute("availableBooks",availableBooks);
		session.setAttribute("avalableBooks", availableBooks);
		model.addAttribute("issuedBooks",issuedBooks);
		System.out.println("--From AdminController We have Available Books:"+availableBooks);
		return "dashboard";               
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session, RedirectAttributes ra) {
		ra.addFlashAttribute("logoutmsg","Logged out");
		session.invalidate();
		return "redirect:/admin/loginAdmin";
	}
	

}
