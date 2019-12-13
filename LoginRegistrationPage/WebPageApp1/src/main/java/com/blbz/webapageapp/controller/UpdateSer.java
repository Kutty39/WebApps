package com.blbz.webapageapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import static com.blbz.webapageapp.model.WebPageAppModel.*;

@WebServlet("/update")
public class UpdateSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(UpdateSer.class.getName());
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.info("Enter");
		if (dbAction().updateUSer(request) > 0) {
			log.info("Data updated");
			response.getWriter().print("Data updated");
			response.getWriter().close();
		} else {
			log.info("Data not updated");
			response.getWriter().print("Data not updated. Somthing went wrong");
			response.getWriter().close();
		}
		log.info("Exit");
	}

}
