package com.lm.ravi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LibController {

	@GetMapping("/")
	public String showHome() {
		return "index";
	}
}
