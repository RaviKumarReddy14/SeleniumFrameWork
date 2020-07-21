package com.ECM.testcases;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Date {
	
	public static String getCurrentDataTime() {
		DateFormat customFormat=new SimpleDateFormat("yyyy_MMMMM_d_HH_mm_ss");
		//DateFormat customFormat=new SimpleDateFormat("dMMMMMyyyy");
		Date currentDate=new Date();
		return customFormat.format(currentDate);
	}

}
