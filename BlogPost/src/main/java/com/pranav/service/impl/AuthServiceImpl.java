package com.pranav.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pranav.entity.Role;
import com.pranav.entity.User;
import com.pranav.exception.BlogAPIException;
import com.pranav.exception.ResourceNotFoundException;
import com.pranav.payload.LoginDto;
import com.pranav.payload.RegisterDto;
import com.pranav.repository.RoleRepository;
import com.pranav.repository.UserRepository;
import com.pranav.service.AuthService;
@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public String login(LoginDto loginDto) {
		// TODO Auto-generated method stub
		
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authenticate);
		return "User Login successfully!";
	}

	@Override
	public String registered(RegisterDto registerDto) {
		
		// add check for username exists in database
        if(userRepository.existsByUsername(registerDto.getUsername())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");
        }

        // add check for email exists in database
        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully!.";
	}

}
