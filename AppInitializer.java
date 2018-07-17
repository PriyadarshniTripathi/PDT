package com.sha.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {

		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfig.class);
		rootContext.setServletContext(servletContext);
		rootContext.refresh();
		servletContext.addListener(new ContextLoaderListener(rootContext));

		dispatcherInitializer(servletContext, rootContext);

	}

	private void dispatcherInitializer(ServletContext servletContext,
			AnnotationConfigWebApplicationContext rootContext) {

		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher",
				new DispatcherServlet(rootContext));

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
		

	}
}