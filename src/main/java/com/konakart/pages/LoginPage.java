package com.konakart.pages;


import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;





@Component
@PropertySources({ @PropertySource(value = "application.properties", ignoreResourceNotFound = true) })
public class LoginPage extends BasePage {

	//private static final Logger logger = Logger.getLogger(LoginPage.class);


	/*
	 * @Autowired private WebDriver driver;
	 */

	//@Autowired
	private Environment env;
	
	//@Autowired
	private WebDriver driver;

	//@PostConstruct
	public void init() {

			environmt = env.getProperty("selenium.driver.browser");
			if(environmt.equals("Chrome"))
				driver.get(env.getProperty("url"));
			
			driver.manage().window().maximize();
			int waitTime  = Integer.parseInt(env.getProperty("shortWait"));
			waiting(waitTime);

	}

	public String getEnvironmt() {
		return environmt;
	}

	public String environmt;

	
	

	
	


}
