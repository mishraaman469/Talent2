package com.mindtree.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class UserController {
	
	@GetMapping("/index")
	public String getIndex() {
		return "index";
	}
	
	@GetMapping("/indexNew")
	public String GetIndex() {
		return "index";
	}
	
	
	
}