package com.vtiger.comcast.contactstest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;

public class CreateOrganization {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		//Step 1 : Create an object of FileInputStream to get the java object representation of Physical file.
		FileInputStream fis = new FileInputStream("./data/commonData.properties");
		
		//Step 2 : Create an object of Properties class and load the properties.
		Properties pobj = new Properties();
		pobj.load(fis);
		
		//Step 3 : Get the properties using getProperty method.
		String browser = pobj.getProperty("browser");
		String url = pobj.getProperty("url");
		String username = pobj.getProperty("username");
		String password = pobj.getProperty("password");
		String accountName = pobj.getProperty("accountName");
		
		//Step 4 : declare the Webdriver reference and create an object based on the value present in the browser variable.
		WebDriver driver = null;
		
		if(browser.equals("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browser.equals("firefox"))
		{
			driver = new FirefoxDriver();
		}
		else if(browser.equals("opera"))
		{
			driver = new OperaDriver();
		}
		else if(browser.equals("internetExplorer"))
		{
			driver = new InternetExplorerDriver();
		}
		
		//Step 5 : Navigate to the main url of the application.
		driver.get(url);
		
		//Step 6 :  Login to the apllication.
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 7 : Click on Organizations module
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 8 : Click on Create Organization button
		driver.findElement(By.cssSelector("img[title = 'Create Organization...']")).click();
		
		//Step 9 : Enter all the details to create new organization
		driver.findElement(By.name("accountname")).sendKeys(accountName);
		driver.findElement(By.name("assigntype")).click();
		driver.findElement(By.name("button")).click();
		
		Thread.sleep(5000);
		
		//Step 10 : Verify the organization name in the header.
		String headerText = driver.findElement(By.xpath("//span[@class = 'dvHeaderText']")).getText();
		if(headerText.contains(accountName))
		{
			System.out.println("Pass : Organization is created successfully");
		}
		else
		{
			System.out.println("Fail : Organization is not created succesfully");
		}
		
		Thread.sleep(5000);
		
		//Logout from the application.
		WebElement userImage = driver.findElement(By.cssSelector("img[src = 'themes/softed/images/user.PNG']"));
		Actions actions = new Actions(driver);
		actions.moveToElement(userImage).perform();
		
		driver.findElement(By.linkText("Sign Out")).click();
		
		driver.quit();
		
	}

}
