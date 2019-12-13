package com.blbz.webapageapp.controller;

import static com.blbz.webapageapp.model.WebPageAppModel.dbAction;

/*
 * Created by	: Tamilselvan S
 * Created on	: 5/12/2019
 * purpose		: to register the user
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("/regestration")
public class RegisterSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(RegisterSer.class.getName());
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Enter");
		boolean state = dbAction().registerUSer(request) > 0;
		log.info("regestration " + state);
		if (state) {
			log.info("true");
			request.setAttribute("page", "login");
			request.setAttribute("regsucs", "success");
			log.info("request passed to login");
			request.getRequestDispatcher("switchpage").forward(request, response);
			log.info("request passed to login completed");
		} else {
			response.getWriter().write("Some thing went wrong");
			log.info("elseblock");
		}
		log.info("Exit");
	}

}
