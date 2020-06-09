package com.lmiguel.sospet.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.lmiguel.sospet.security.UserSS;


public class UserService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}