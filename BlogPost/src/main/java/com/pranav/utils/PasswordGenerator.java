package com.pranav.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordGenerator {

	public static void main(String[] args) {
		PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode("pranav@123"));
		System.out.println(passwordEncoder.encode("rupal@123"));
	}
}
