package com.konakart.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
@PropertySources({ @PropertySource(value = "application.properties", ignoreResourceNotFound = true) })
public  class DriverFactory {
	
	//@Autowired
	private Environment env;
	
	public WebDriver createDriver() {
		String browser = env.getProperty("selenium.driver.browser");
		WebDriver webDriver = null;
		
		if(browser == "Firefox")
		{
			webDriver = new FirefoxDriver();
			
		}
		else if(browser == "Chrome")
			
		{

			System.setProperty("webdriver.chrome.driver", "src/main/resources/seleniumwebdriver/chromedriver.exe");

			DesiredCapabilities chCapabilities = DesiredCapabilities.chrome();
			ChromeOptions opch = new ChromeOptions();
			opch.merge(chCapabilities);
			opch.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, true);
			opch.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);
			opch.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
			webDriver = new ChromeDriver(opch);
			
			DesiredCapabilities capch = new DesiredCapabilities();
			capch.setCapability("browser_version", "57.0");
			capch.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, true);
			capch.setCapability(CapabilityType.SUPPORTS_NETWORK_CONNECTION, true);
			capch.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
			webDriver = new ChromeDriver(opch);
			// ChromeOptions options=new ChromeOptions();
			// webDriver=new ChromeDriver(options)

		}
		else if(browser == "IE")
		{
			DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
			caps.setBrowserName("internet explorer");
			caps.setPlatform(Platform.WIN10);
			
			System.setProperty("webdriver.ie.driver", "src/main/resources/seleniumwebdriver/IEDriverServer.exe");
			caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
			caps.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
			caps.setPlatform(Platform.WIN10);
			
			InternetExplorerOptions options = new InternetExplorerOptions();
			options.waitForUploadDialogUpTo(120, TimeUnit.SECONDS);
			options.merge(caps);

			// reading from application.properties file
			options.withInitialBrowserUrl(env.getProperty("url"));
    		options.introduceFlakinessByIgnoringSecurityDomains();
			options.useCreateProcessApiToLaunchIe();
			options.addCommandSwitches("-private");
			options.destructivelyEnsureCleanSession();
			options.ignoreZoomSettings();
			options.requireWindowFocus();

		
			webDriver = new InternetExplorerDriver(options);
		}
		else
			webDriver = new InternetExplorerDriver();
		
		
		//TODO
		/*webDriver.get(env.getProperty("sqc.app.url"));
		LoginPage loginPage = new LoginPage();
		loginPage.logIntoSQC("t868180", "Password-997");
		loginPage.waiting(3);*/
		return webDriver;
	
	}
	
}
