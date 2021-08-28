package com.vtiger.comcast.genericutility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * This class is used read the data from commonData.properties file.
 * @author Swathi
 *
 */

public class FileUtility {

	/**
	 * This method helps us to read the data from the commonData.properties file.
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String getPropertyValue(String key) throws Exception {
		FileInputStream fis = new FileInputStream("./testdata/commonData.properties");
		
		//Create an object of the properties
		Properties propertyObj = new Properties();

		//Load the file
		propertyObj.load(fis);

		//Get the values from the property file
		String value = propertyObj.getProperty(key);
		return value ;
	}
}
