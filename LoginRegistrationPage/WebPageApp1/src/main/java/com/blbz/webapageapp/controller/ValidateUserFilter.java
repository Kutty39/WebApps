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

@WebFilter(value = { "/profilepage.jsp" }, dispatcherTypes = { DispatcherType.REQUEST, DispatcherType.FORWARD })
public class ValidateUserFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		if (req.getSession(false) != null) {
			req.setAttribute("user", req.getSession(false).getAttribute("user"));
			if (req.getSession(false).getAttribute("detail") != null
					&& req.getSession(false).getAttribute("detail") != "") {
				req.setAttribute("detail", req.getSession(false).getAttribute("detail"));
				chain.doFilter(request, response);
			}else {
				response.getWriter().print("<h1>Unauthorized Access.I will file a complaint on you</h1>");
			}
		} else {
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
