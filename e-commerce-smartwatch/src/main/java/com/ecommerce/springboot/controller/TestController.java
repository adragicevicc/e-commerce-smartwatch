package com.ecommerce.springboot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	
	@GetMapping("/kupac")
	@PreAuthorize("hasRole('KUPAC') or hasRole('ZAPOSLENI') or hasRole('ADMIN')")
	public String kupacAccess() {
		return "Kupac";
	}
	@GetMapping("/zaposleni")
	@PreAuthorize("hasRole('ZAPOSLENI')")
	public String zaposleniAccess() {
		return "Zaposleni";
	}
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin";
	}
}
