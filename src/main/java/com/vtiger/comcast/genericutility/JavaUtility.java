package com.vtiger.comcast.genericutility;

import java.util.Date;
import java.util.Random;

public class JavaUtility {

	/**
	 * This Class contains all the java specific generic libraries
	 * Author Swathi
	 */
	
	/**
	 * This method is used to generate integer random number within the range of 0 to 10000
	 * @return
	 */
	public int getRandomNumber() {
		Random random = new Random();
		int randomInt = random.nextInt(10000);
		return randomInt ;
	}
	
	
	/**
	 * This method is used to get the current system date and time.
	 * @return
	 */
	public String getSysDateAndTime() {
		Date date = new Date();
		String systemDateAndTime = date.toString();
		return systemDateAndTime;
	}
	
	/**
	 * This method is used to get the current system date in YYYY_MM_DD format.
	 * @return
	 */
	
	public String getSysDateAndTime_YYYY_MM_DD() {
		Date date = new Date();
		String systemDateAndTime = date.toString();
		String[] arr = systemDateAndTime.split(" ");
		String DD = arr[2];
		String YYYY = arr[5];
		int MM = date.getMonth() + 1;
		
		String dateformat = YYYY + MM + DD ;
		
		return dateformat;
	}		
}
