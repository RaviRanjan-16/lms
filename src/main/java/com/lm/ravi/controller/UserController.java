package com.lm.ravi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lm.ravi.entity.User;
import com.lm.ravi.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//Show user registration page
	@GetMapping("/register")
	public String showRegisrationForm(Model model) {
		model.addAttribute("user", new User());
		return "userregister";
	}
	
	//Handle user registration
	@PostMapping("/register")
	public String registerUser(@ModelAttribute User user) {
		userService.addUser(user);
		return "redirect:/admin/users/list";
	}
	
	//Show all users
	@GetMapping("/list")
	public String showUsers(Model model) {
		List<User> users=userService.getAllUsers();
		model.addAttribute("users", users);
		return "userlist";
	}

}
