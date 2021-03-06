package com.blbz.webapageapp.controller;
/*
 * Created by	: Tamilselvan S
 * Created on	: 5/12/2019
 * purpose		: to navigate between pages
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("/switchpage")
public class PageNave extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(PageNave.class.getName());
	
	 @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("nav - DoPost");
		String page = "";
		if (req.getParameter("page") != null) {
			page = req.getParameter("page");
		} else if (req.getAttribute("page") != null) {
			page = (String) req.getAttribute("page");
		}
		log.info("going to - "+page);
		if (page.equals("login")) {
			req.getRequestDispatcher("login.jsp").forward(req, resp);
		} else if (page.equals("register")) {
			req.getRequestDispatcher("register.jsp").forward(req, resp);
		}else if(page.equals("home")) {
			req.getRequestDispatcher("home.jsp").forward(req, resp);
		}else if(page.equals("userprof")) {
			req.getRequestDispatcher("profilepage.jsp").forward(req, resp);
		}else if(page.equals("logout")) {
			req.getRequestDispatcher("logout.jsp").forward(req, resp);
		}
	}

	
}
