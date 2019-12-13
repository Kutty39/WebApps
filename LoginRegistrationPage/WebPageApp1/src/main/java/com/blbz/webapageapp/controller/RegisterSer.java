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

@WebServlet("/regestration")
public class RegisterSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.err.println("Regser - DoPost - Enter");
		boolean state = dbAction().registerUSer(request) > 0;
		System.err.println("Regser - DoGet - regestration " + state);
		if (state) {
			System.err.println("Regser - DoGet - true");
			request.setAttribute("page", "login");
			request.setAttribute("regsucs", "success");
			System.err.println("Regser - DoGet - request passed to login");
			request.getRequestDispatcher("switchpage").forward(request, response);
			System.err.println("Regser - DoGet - request passed to login completed");
		} else {
			response.getWriter().write("Some thing went wrong");
			System.err.println("Regser - DoGet - elseblock");
		}
		System.err.println("Regser - DoPost - Exit");
	}

}
