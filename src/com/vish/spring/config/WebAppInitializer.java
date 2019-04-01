package com.vish.spring.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() { // Root WebApplicationContext (containing middle layer services, datasources, etc)

		return new Class[]{AppConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() { //Servlet WebApplicationContext (containing controllers, view resolvers and other web-related beans)
		
		return new Class[]{WebConfig.class};
	}

	@Override
	protected String[] getServletMappings() {

		return new String[]{"/"};
	}

}
