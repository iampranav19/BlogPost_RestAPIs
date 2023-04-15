package com.pranav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pranav.payload.LoginDto;
import com.pranav.payload.RegisterDto;
import com.pranav.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
	@Autowired
	private AuthService authService;
	
	// Build Login Rest API
	@PostMapping(value = {"/login","/signin"})
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto)
	{
		return new ResponseEntity<String>(authService.login(loginDto), HttpStatus.OK);
	}
	
	// Build Registered API
	@PostMapping(value = {"/register","/signup"})
	public ResponseEntity<String> registered(@RequestBody RegisterDto registerDto)
	{
		return new ResponseEntity<String>(authService.registered(registerDto), HttpStatus.CREATED);
	}
}
