package com.konakart.automation.games;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.konakart.conf.TestConfig;
import  com.konakart.pages.HomePage;
import  com.konakart.pages.LoginPage;





public class BuyGamesTest {
	
	private AnnotationConfigApplicationContext context;
	
	private HomePage homePage;
	private LoginPage loginPage;
	
	
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite(ITestContext context) {
	     for (ITestNGMethod method : context.getAllTestMethods()) {
	       
	     }}
	
	@BeforeMethod
	public void init() {
		context = new AnnotationConfigApplicationContext(TestConfig.class);
		homePage = (HomePage) context.getBean(HomePage.class);
		loginPage = (LoginPage) context.getBean(LoginPage.class);
		
		loginPage.init();
	}
	
	
	@AfterMethod
	public void end() {
		loginPage.end();
		context.close();
	}
	
	
  @Test
  public void BuyOnlineGame() {
	  
	  homePage.ClickOnGamesBtn();
	  
  }
}
