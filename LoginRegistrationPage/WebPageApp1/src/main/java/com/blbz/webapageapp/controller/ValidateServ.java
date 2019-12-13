package com.blbz.webapageapp.controller;
/*
 * Created by	: Tamilselvan S
 * Created on	: 7/12/2019
 * purpose		: to valid all the things which involved in this webapp
 */

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.blbz.webapageapp.model.WebPageAppModel.*;

@WebServlet("/validate")
public class ValidateServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.err.println("valid - DoPost - Entered");
		String email = request.getParameter("email");
		if (email != null) {
			System.err.println("valid - DoGet - emailvalidater");
			response.getWriter().print(dbAction().checkStatement(email));
		}
		System.err.println("valid - DoPost - Exit");
	}

}
