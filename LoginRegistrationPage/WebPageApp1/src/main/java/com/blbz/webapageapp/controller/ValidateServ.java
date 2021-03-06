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

import org.apache.log4j.Logger;

import static com.blbz.webapageapp.model.WebPageAppModel.*;

@WebServlet("/validate")
public class ValidateServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ValidateServ.class.getName());
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Entered");
		String email = request.getParameter("email");
		if (email != null) {
			log.info("emailvalidater");
			response.getWriter().print(dbAction().checkStatement(email));
		}
		log.info("Exit");
	}

}
