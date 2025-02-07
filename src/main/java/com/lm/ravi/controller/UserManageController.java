package com.lm.ravi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lm.ravi.service.UserService;

@Controller
@RequestMapping("/students")
public class UserManageController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String listStudents(Model model) {
    	System.out.println("hi");
        model.addAttribute("students", userService.getAllUsers());
        return "manageuser";
    }

    @PostMapping("/update")
    public String updateStudentEmail(@RequestParam Long id, @RequestParam String email) {
        userService.updateStudentEmail(id, email);
        return "redirect:/students";
    }

    @PostMapping("/delete")
    public String deleteStudent(@RequestParam Long id) {
        userService.deleteStudent(id);
        return "redirect:/students";
    }
}