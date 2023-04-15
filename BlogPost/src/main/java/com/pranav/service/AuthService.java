package com.pranav.service;

import com.pranav.payload.LoginDto;
import com.pranav.payload.RegisterDto;

public interface AuthService {

	
	String login(LoginDto loginDto);
	
	String registered(RegisterDto registerDto);
}
