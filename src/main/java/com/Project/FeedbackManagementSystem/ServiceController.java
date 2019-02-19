package com.Project.FeedbackManagementSystem;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

	@RequestMapping("/")
	String welcomeMsg()
	{
		return "Welcome to Feedback Management System";
	}
}
