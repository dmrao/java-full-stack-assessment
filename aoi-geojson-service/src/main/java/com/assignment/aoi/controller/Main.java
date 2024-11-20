package com.assignment.aoi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Main {
	
	@GetMapping("/")
	public String getHomepage(Model model) {
		String code = getAccessToken();
		System.out.println("CODE ******************"+code);
	    model.addAttribute("accessToken", code);
		return "index";
	}
	
	 private String getAccessToken() {
	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        if (authentication != null && authentication.getPrincipal() instanceof OidcUser) {
	            OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
	            return oidcUser.getIdToken().getTokenValue();
	        }
	        return null;
	    }
	 	 
	 
}
