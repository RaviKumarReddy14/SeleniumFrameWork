package com.ECM.utility;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;




/**
 * @author RaviKuamrReddy
 *
 */
public class ScreenShot {

	public static String captureScreenshot(WebDriver driver, String screenshotname) throws IOException

	{
		
		TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = System.getProperty("user.dir") +"\\Screenshots\\"+screenshotname+"_"+getCurrentDataTime()+".png";
        File destination = new File(dest);
        FileUtils.copyFile(source, destination);        
                     
        return dest;
		
		

	}
	
	public static String getCurrentDataTime() {
		DateFormat customFormat=new SimpleDateFormat("yyyy_MMMMM_d_HH_mm_ss");
		//DateFormat customFormat=new SimpleDateFormat("dMMMMMyyyy");
		Date currentDate=new Date();
		return customFormat.format(currentDate);
	}

}
