package com.mall.ssm.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.mall.ssm.util.Configuration;

public class AppServletContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		String domain = servletContextEvent.getServletContext().getInitParameter("domain");
		Configuration.BaseUrl = domain;
		
		String path = servletContextEvent.getServletContext().getRealPath("/");
		Configuration.BaseImgDir = path;
		
	}

}