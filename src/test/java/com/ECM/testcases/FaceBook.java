package com.ECM.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
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

public class FaceBook {
	
	WebDriver driver;
	public ConfigDataProvider config;
	
	@BeforeSuite
	public void setUpSuite() throws IOException {
		
		config=new ConfigDataProvider();
	}
	
	@BeforeClass
	public void setup() {
		
		driver=BrowserFactory.startApplication(driver, config.getBrowser(), config.getAppURL());
	}
	
	ExtentHtmlReporter reporter;
	ExtentReports extent;
	ExtentTest logger;
	ExtentTest logger2;

	@BeforeTest
	public void extentReport() {
		
		reporter=new ExtentHtmlReporter("./Reports/FaceBook.html"); 
		extent = new ExtentReports();
		extent.attachReporter(reporter);
				
	}
	
	
	@Test(priority=2)
	public void getLogIn() throws Exception {
		
		logger=extent.createTest("FaceBook Page");
		
		
		System.out.println(driver.getTitle());
		Assert.assertEquals("Google",driver.getTitle());
		Reporter.log("Title is verified successfully",true);
		
		logger.log(Status.INFO, "*********** Google Page Verified Successfully**********");
		logger.log(Status.PASS, "*********** Google Page Verified Successfully**********");
		ScreenShot.captureScreenshot(driver,driver.getTitle());
		
		Google page=PageFactory.initElements(driver, Google.class);
		page.getGmail();
		Assert.assertEquals("Google Images",driver.getTitle());
		Reporter.log("Title is verified successfully",true);
		
		System.out.println(driver.getTitle());
		ScreenShot.captureScreenshot(driver,driver.getTitle());
		logger.log(Status.INFO, "*********** Gmail Page Verified Successfully**********");
		logger.log(Status.PASS, "*********** Gmail Page Verified Successfully**********");
		
		
		
		extent.flush();
		
	}
		
		
		
		@AfterClass
		public void tearDown() {
		
		BrowserFactory.quitBrowser(driver);
	}
		
		@AfterMethod
		public void tearDownMetod(ITestResult result) throws IOException {
			
			if(result.getStatus()==ITestResult.FAILURE) {
				logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" Test case FAILED due to below issues:", ExtentColor.RED));
				logger.fail(result.getThrowable());
				logger.fail("Snapshot below: ",MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot.captureScreenshot(driver,"Fail")).build());
				
			}
			else if(result.getStatus() == ITestResult.SUCCESS)
	        {
	        	
	        	
	        	logger.pass("Test Passed",MediaEntityBuilder.createScreenCaptureFromPath(ScreenShot.captureScreenshot(driver,"Pass")).build());
	        	 }
	        else
	        {
	            logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+" Test Case SKIPPED", ExtentColor.ORANGE));
	            logger.skip(result.getThrowable());
	        }
	        extent.flush();
		}

}
