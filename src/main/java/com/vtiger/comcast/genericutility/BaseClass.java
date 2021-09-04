package com.vtiger.comcast.genericutility;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.mysql.cj.jdbc.Driver;
import com.vtiger.comcast.pomrepositorylib.HomePage;
import com.vtiger.comcast.pomrepositorylib.LoginPage;

public class BaseClass {

	//Create an object to the JavaUtility generic library to use the generic methods.
	public JavaUtility jlib = new JavaUtility();

	//Create an object to the WebDriverUtility generic library to use the generic methods.
	public WebDriverUtility wlib = new WebDriverUtility();

	//Craete an object to the File generic library to read the data from property file.
	public FileUtility flib = new FileUtility();

	//Create an object to Excel Utlity library to read and write the data into the Excel file.
	public ExcelUtility elib = new ExcelUtility();

	public WebDriver driver = null;
	public static WebDriver sDriver;
	public HomePage homePageObj ;
	public Connection connection ; 
	public String organizationName ;
	
	@BeforeSuite(groups = {"SmokeTest", "RegressionTest"})
	public void configBS() throws Throwable {
		//Register the Driver
//		Driver dbDriver = new Driver();
//		DriverManager.registerDriver(dbDriver);
//		
//		//Establish the connection
//		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/commonData", "root", "root");
//		
//		//Issue the statement
//		Statement statement = connection.createStatement();
//		
//		//Execute the query.
//		int result = statement.executeUpdate("insert into commondata values(1, 'TATA')");
//		ResultSet resultSet = statement.executeQuery("select orgname from commondata");
//		while(resultSet.next())
//		{
//			organizationName = resultSet.getString(1);
//		}		
	}
	
	//@Parameters("browser")
	@BeforeClass(groups = {"SmokeTest", "RegressionTest"})
	//public void configBC(String browser) throws Throwable {
	public void configBC() throws Throwable {
		//Get the values from the FileUtility
		//String browser = flib.getPropertyValue("browser");
		//String url = flib.getPropertyValue("url");
		
		//Maven Parameters from command line.
		String browser = System.getProperty("browser");
		String url = System.getProperty("url");
		
		if(browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equals("Opera")) {
			driver = new OperaDriver();
		}
		sDriver = driver;
		
		driver.get(url);
		driver.manage().window().maximize();
		wlib.waitForPageLoad(driver);
		
	}
	
	@BeforeMethod(groups = {"SmokeTest", "RegressionTest"})
	public void configureBM() throws Throwable {
		//Get the values from the FileUtility			
		String username = flib.getPropertyValue("username");
		String password = flib.getPropertyValue("password");
		
		//Create an object of LoginPage to login to the application using business method.
		LoginPage loginPageObj = new LoginPage(driver);
		loginPageObj.loginToApplication(username,password );
	}
	
	@AfterMethod(groups = {"SmokeTest", "RegressionTest"})
	public void configAM() {		
		//Logout from the application.
		homePageObj = new HomePage(driver);
		homePageObj.logout();					
	}
	
	@AfterClass(groups = {"SmokeTest", "RegressionTest"})
	public void configAC() {
		//Close the browser
		driver.quit();
	}
	
	@AfterSuite(groups = {"SmokeTest", "RegressionTest"})
	public void configAS() throws Throwable {
	//	connection.close();
	}


}
