package com.blbz.webapageapp.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.PropertyConfigurator;

@WebListener
public final class Log4jListener implements ServletContextListener {

	public Log4jListener() {
	}

	public void contextDestroyed(ServletContextEvent paramServletContextEvent) {
	}

	public void contextInitialized(ServletContextEvent servletContext) {
		ServletContext context = servletContext.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
        PropertyConfigurator.configure(fullPath);
	}

}
