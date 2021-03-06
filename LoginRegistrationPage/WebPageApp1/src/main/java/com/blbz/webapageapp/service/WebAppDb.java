package com.blbz.webapageapp.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface WebAppDb {
	// for all the select statements
	boolean checkStatement(String email);

	// for user registration
	int registerUSer(HttpServletRequest request);

//validate and get all the detail
	HttpSession userValidation(String username, String pas, HttpSession session);

	// to update the existing user detail
	int updateUSer(HttpServletRequest request);

	// validate the user
	boolean userValidation(String username, String pas);
}
