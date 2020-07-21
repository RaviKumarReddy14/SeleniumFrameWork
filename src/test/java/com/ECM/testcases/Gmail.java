package com.ECM.testcases;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ECM.pages.Google;
import com.ECM.utility.BrowserFactory;
import com.ECM.utility.ConfigDataProvider;
import com.ECM.utility.ScreenShot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Gmail {
	
	WebDriver driver;
	ExtentHtmlReporter reporter;
	ExtentReports extent;
	ExtentTest logger;
	public ConfigDataProvider config;
	
	@BeforeSuite
	public void setUpSuite() throws IOException {
		
		config=new ConfigDataProvider();
	}
	
	@BeforeClass
	public void setup() {
		
		driver=BrowserFactory.startApplication(driver, config.getBrowser(), config.getAppURL());
	}
	
	
	

	@BeforeTest
	public void extentReport() {
		
		reporter=new ExtentHtmlReporter("./Reports/Gmail.html"); 
		extent = new ExtentReports();
		extent.attachReporter(reporter);
				
	}
	
	
	@Test(priority=1)
	public void getLogIn() throws Exception {
		
		logger=extent.createTest("Google Page");
		
		System.out.println(driver.getTitle());
		Assert.assertEquals("Google",driver.getTitle());
		Reporter.log("Title is verified successfully",true);
		
		logger.log(Status.INFO, "*********** Google Page Verified Successfully**********");
		
		ScreenShot.captureScreenshot(driver,driver.getTitle());
		
		Google page=PageFactory.initElements(driver, Google.class);
		page.getImages();
		Assert.assertEquals("Google Images",driver.getTitle());
		Reporter.log("Title is verified successfully",true);
		ScreenShot.captureScreenshot(driver,driver.getTitle());
		
		logger.log(Status.PASS, "*********** Google Images Page Verified Successfully**********");
		
		System.out.println(driver.getTitle());
		
		extent.flush();
		
	}
		
		
		
		@AfterClass
		public void tearDown() {
		
		BrowserFactory.quitBrowser(driver);
	}
		
		@AfterMethod
		public void tearDownMetod(ITestResult result) throws IOException {
			
			if(result.getStatus()==ITestResult.FAILURE) {
				String screenShotPath = ScreenShot.captureScreenshot(driver,"Failed");
				logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
				logger.fail(result.getThrowable());
				logger.fail("Snapshot below: ",MediaEntityBuilder.createScreenCaptureFromPath(screenShotPath).build());
				
			}
			else if(result.getStatus() == ITestResult.SUCCESS)
	        {
	        	
	        	logger.pass("Test Passed",MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot.captureScreenshot(driver,"Success")).build());
	        	 }
	        else if(result.getStatus() == ITestResult.SKIP)
	        {
	            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
	            logger.skip(result.getThrowable());
	        }
			
	        
	        extent.flush();
		}

		
}
