package com.blbz.webapageapp.controller;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebFilter(value = {"/*"},dispatcherTypes = {DispatcherType.FORWARD,DispatcherType.REQUEST,DispatcherType.INCLUDE})
public class ValidateLogin implements Filter {
    private static final Logger log = Logger.getLogger(ValidateLogin.class.getName());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("login filter");
        HttpServletRequest req=(HttpServletRequest) request;
        HttpSession sn= req.getSession(false);
        if(sn!=null){
            request.setAttribute("user",sn.getAttribute("user"));
        }
    chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
