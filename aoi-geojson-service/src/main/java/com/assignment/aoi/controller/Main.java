package com.assignment.aoi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Main {

	public String getHomepage() {
		return "index";
	}
}
