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

@WebFilter(value= {"/logout.jsp"},dispatcherTypes = {DispatcherType.FORWARD})
public class LogoutFilter implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		req.getSession(false).setAttribute("username",null);
		req.getSession(false).invalidate();
		chain.doFilter(request, response);
	}
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
