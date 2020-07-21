package com.ECM.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Google {
	
	WebDriver driver;
	
	public Google(WebDriver driver) {
		
		this.driver=driver;
	}
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Images')]")
	WebElement images;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Gmail')]")
	WebElement gmail;
	
	public void getImages() {
		JavascriptExecutor Je = (JavascriptExecutor) driver;
		Je.executeScript("arguments[0].click();", images);

	}
	
	public void getGmail() {
		JavascriptExecutor Je = (JavascriptExecutor) driver;
		Je.executeScript("arguments[0].click();", gmail);

	}

}
