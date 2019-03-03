package com.konakart.pages;

import org.openqa.selenium.By;
import org.springframework.stereotype.Component;

@Component
public class HomePage extends BasePage
{
	
	private static By gameBtn = By.linkText("Games");
	private static By rightSliderController = By.xpath("//div[@id='price-range-slider']/span[2]");
	private static By leftSliderController = By.xpath("//div[@id='price-range-slider']/span[1]");
	
	
	// click on game btn
	public void ClickOnGamesBtn()
	{
		waitForWebElementToBeClickable(gameBtn);
		driver.findElement(gameBtn).click();
		
	}
	// drag the rightSliderController to

}
