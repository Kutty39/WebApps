package com.blbz.webapageapp.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.blbz.webapageapp.model.WebPageAppModel.*;

@WebServlet("/update")
public class UpdateSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.err.println("updateuer - Dopost - Enter");
		if (dbAction().updateUSer(request) > 0) {
			response.getWriter().print("Data updated");
			response.getWriter().close();
		} else {
			response.getWriter().print("Data not updated. Somthing went wrong");
			response.getWriter().close();
		}
		System.err.println("updateuer - Dopost - Exit");
	}

}
