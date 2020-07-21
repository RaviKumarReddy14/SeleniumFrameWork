package com.ECM.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {
	
	public static WebDriver startApplication(WebDriver driver,String browserName,String appURL) {
		
		if(browserName.equals("chrome")) {
			
			driver=new ChromeDriver();
			
		}
		else if(browserName.equals("fireFox")){
			
			driver=new FirefoxDriver();
			
		}
		else if(browserName.equals("ie")){
			
			driver=new InternetExplorerDriver();
			
		}
		else{
			
			System.out.println("We do not support this browser:");
	
		}
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		return driver;
		
	}
	
	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}

}
