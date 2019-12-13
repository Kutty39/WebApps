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

@WebFilter(value = { "/logout.jsp" }, dispatcherTypes = { DispatcherType.FORWARD,DispatcherType.REQUEST })
public class LogoutFilter implements Filter {
	private static final Logger log = Logger.getLogger(LogoutFilter.class.getName());

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("log-out");
		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getSession(false).getAttribute("user") != null) {
			req.getSession(false).setAttribute("user", null);
			req.getSession(false).invalidate();
			chain.doFilter(request, response);
		} else {
			log.info("Unauthorized Access");
			response.getWriter().print("<h1>Unauthorized Access.I will file a complaint on you</h1>");
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
