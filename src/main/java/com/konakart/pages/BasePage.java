package com.konakart.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Function;
import com.konakart.conf.*;
import com.konakart.utils.DriverFactory;


@Component
public class BasePage {
	
	@Autowired
	protected DriverFactory driverFactory;
	
	@Autowired
	protected WebDriver driver;

	public void createDriver() {
		driver = driverFactory.createDriver();
	}
	
	public void end() {
		if(driver!=null) {
			driver.quit();
		}
	}
	
	public void switchToDefault() {
		driver.switchTo().defaultContent();
	}
	
	public boolean isElementPresent(By locatorKey) {
		try {
			waiting(3);
			driver.findElement(locatorKey).isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private WebDriverWait getWait() {
		return getWait(3);
	}

	private WebDriverWait getWait(int seconds) {
		return new WebDriverWait(driver, seconds);
	}

	public void waitForWebElement(By element) {
		waitForWebElement(element, 30);
	}

	public void waitForWebElement(By element, int seconds) {
		WebDriverWait wait = getWait(seconds);
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}

	public void waitForWebElements(By element, int seconds) {
		WebDriverWait wait = getWait(seconds);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
	}

	public void waitForWebElementToBeVisible(By element) {
		WebDriverWait wait = getWait(120);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public void waitForWebElementToBeClickable(By element) {
		WebDriverWait wait = getWait(58);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitAndswitchToFrame(String frameName) {
		WebDriverWait wait = getWait(180);
		waiting(6);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
	}

	public void waiting(int time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {

		}
	}

	public void scrollDown() {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, 250);");
	}

	public void scrollUp() {

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("scroll(0, -250);");
	}

	public WebElement scrollToElement(By by) {

		WebDriverWait wait = getWait();

		JavascriptExecutor jse = (JavascriptExecutor) driver;
		// presence in DOM
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
		WebElement element = driver.findElement(by);
		// scrolling
		jse.executeScript("arguments[0].scrollIntoView(true);", element);

		return element;
	}

	public boolean retryingFindClick(By by) {
	
		boolean result = false;
		int attempts = 0;
		while (attempts < 3) {
			try {
				driver.findElement(by);
				result = true;
				break;
			} catch (StaleElementReferenceException e) {

			}
			attempts++;
		}
		return result;
	}

	public WebElement fluentWait(final WebElement ele) {

		@SuppressWarnings("deprecation")
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class)
				.ignoring(ElementNotVisibleException.class).ignoring(StaleElementReferenceException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return ele;
			}
		});

		return foo;
	}

	public void implicitWait(long seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	public void switchToActivePopupOrFrame() {
		driver.switchTo().activeElement();
	}

	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
	}

	public boolean isAlertPresent() {
		waiting(2);
		boolean presentFlag = false;

		int i = 0;
		while (i++ < 5) {

			try {

				// Check the presence of alert
				Alert alert = driver.switchTo().alert();
				// Alert present; set the flag
				presentFlag = true;
				// if present consume the alert
				alert.accept();

			} catch (NoAlertPresentException ex) {
				// Alert not present
				// ex.printStackTrace();
			}
		}
		return presentFlag;
	}

	public String getAlertMessage(){
		String message ="";
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		message= alert.getText();
		alert.accept();
		return message;
	}

	public void switchToAnotherWindowHandle(String winHandleBefore) {
		// Switch to new window opened
		
		
		for (String winHandle : driver.getWindowHandles()) {
			if (!winHandle.equals(winHandleBefore))
				driver.switchTo().window(winHandle);
				
		}
		// driver.manage().window().maximize();
	}

	public void switchToMainWindowHandleAndCloseCurrentOne(String winHandleBefore) {
		// Close the new window, if that window no more required
		driver.close();
		// Switch back to original browser (first window)
		driver.switchTo().window(winHandleBefore);

	}

	public void minimizeMaximizeBrowser() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			Dimension dd = new Dimension(600, 600);
			driver.manage().window().setSize(dd);
			waiting(4);
			driver.manage().window().maximize();
		}
	}

}
