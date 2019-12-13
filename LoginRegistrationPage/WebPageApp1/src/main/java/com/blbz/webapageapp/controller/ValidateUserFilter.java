package com.blbz.webapageapp.controller;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

@WebFilter(value = { "/profilepage.jsp" }, dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD })
public class ValidateUserFilter implements Filter {
	private static final Logger log = Logger.getLogger(ValidateUserFilter.class.getName());
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		log.info(req.getRequestURI());
		if (req.getSession(false) != null) {
			if (req.getSession(false).getAttribute("detail") != null
					&& req.getSession(false).getAttribute("detail") != "") {
				request.setAttribute("detail", req.getSession(false).getAttribute("detail"));
				log.info("navigating to the page");
				chain.doFilter(request, response);
			}else {
				log.info("Unauthorized Access");
				response.getWriter().print("<h1>Unauthorized Access.I will file a complaint on you</h1>");
			}
		} else {
			log.info("Seeson closed-Unauthorized Access");
			response.getWriter().print("<h1>Unauthorized Access.I will file a complaint on you</h1>");
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void destroy() {

	}

}
