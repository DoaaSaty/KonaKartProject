package com.konakart.automation;




import org.apache.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.konakart.conf.ApplicationConfig;


/**
 * Hello world!
 *
 */
public class App {
	private static Logger logger = Logger.getLogger(App.class.getName());
	
	public static void main(String[] args) {
		System.out.println("Welcome To konakart Test Automation");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
	
		
		logger.info("Info");

		logger.warn("warning!");

		logger.error("error");
		
		context.close();
	}
}
