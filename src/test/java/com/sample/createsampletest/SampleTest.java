package com.sample.createsampletest;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.vtiger.comcast.genericutility.BaseClass;

public class SampleTest {
	
	WebDriver driver = null;
	ExtentHtmlReporter reporter ;
	ExtentReports reports;
	ExtentTest test;
	
	@BeforeSuite
	public void configBS() {
		String timestamp = LocalDateTime.now().toString().replace(":", "-");
		reporter = new ExtentHtmlReporter("./Extent Reports/TestReports"+timestamp+".html");
		reports = new ExtentReports();
		reports.attachReporter(reporter);
		System.out.println("Execute before suite");
	}

	@BeforeClass
	public void configBC() {
		//Launch the browser and navigate to the main URL of the application.
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://localhost:8888");		
	}
	
	@BeforeMethod
	public void configBM() {
		//Login to the application 
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).click();
	}
	
	@Test
	public void getHomeTitle() {		
		
		test = reports.createTest("getHomeTitle");
		//Get the title of the home page and save it in a local variable.
		String homePageTitle = driver.getTitle();
		System.out.println("homePageTitle = "+homePageTitle);
		Assert.assertEquals(homePageTitle, "Administrator - Home - vtiger CRM 5 - Commercial Open Source CRM");
		
	}
	
	@Test 
	public void getOrgTitle() {
		test = reports.createTest("getOrgTitle");
		//navigate to the organizations page and get the title and save it in a local variable
		driver.findElement(By.linkText("Organizations")).click();
		String orgPageTitle = driver.getTitle();
		System.out.println("orgPageTitle = "+orgPageTitle);
		Assert.assertEquals(orgPageTitle, "Administrator - Organizations - vtiger CRM 5 - Commercial Open Source CRM");
	}
	
	@AfterMethod
	public void configAM() {	
		//Create an object to actions class to signout from the application
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//img[@src = 'themes/softed/images/user.PNG']"))).perform();
		driver.findElement(By.linkText("Sign Out")).click();
	}
	
	@AfterClass
	public void configAC() {
		//Close the browser
		driver.quit();
	}

	@AfterSuite
	public void configAS() {
		reports.flush();
		System.out.println("Execute After suite");
	}
}
