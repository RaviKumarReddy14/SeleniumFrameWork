package com.ECM.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.gargoylesoftware.htmlunit.javascript.host.file.File;

public class ConfigDataProvider {
	
	Properties pro;
	public ConfigDataProvider() throws FileNotFoundException {
		
		java.io.File src=new java.io.File("./Config/Config.properties");
		
		
			FileInputStream fis=new FileInputStream(src);
			pro=new Properties();
			try {
				pro.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Not able to load config file: "+e.getMessage());
			}
		
	}
	
	public String getDateFormat(String keyToSearch) {
		
		return pro.getProperty(keyToSearch);
		
	}
	
	public String getBrowser() {
		
		return pro.getProperty("Browser");
	}
	
	public String getAppURL() {
		
		return pro.getProperty("url");
		
	}

}
