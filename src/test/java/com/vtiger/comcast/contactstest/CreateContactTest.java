package com.vtiger.comcast.contactstest;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.vtiger.comcast.pomrepositorylib.ContactsPage;
import com.vtiger.comcast.pomrepositorylib.CreateOrganizationPage;
import com.vtiger.comcast.genericutility.BaseClass;
import com.vtiger.comcast.genericutility.ExcelUtility;
import com.vtiger.comcast.genericutility.FileUtility;
import com.vtiger.comcast.genericutility.JavaUtility;
import com.vtiger.comcast.genericutility.WebDriverUtility;
import com.vtiger.comcast.pomrepositorylib.ContactInfoPage;
import com.vtiger.comcast.pomrepositorylib.HomePage;
import com.vtiger.comcast.pomrepositorylib.LoginPage;
import com.vtiger.comcast.pomrepositorylib.OrganizationInfoPage;
import com.vtiger.comcast.pomrepositorylib.OrganizationsPage;

@Listeners(com.vtiger.comcast.genericutility.ListenerImplementationClass.class)
public class CreateContactTest extends BaseClass {

	@Test (groups = {"SmokeTest"})
	public void createContactTest() throws Throwable {
		
		//Read the data from the excel workbook
		String firstname = elib.getDataFromExcel("TestData", 4, 2);
		
		//Create an object of HomePage to navigate to the contacts page.
		homePageObj = new HomePage(driver);
		homePageObj.clickOnContactsLink();
		
		//Create an object of contactsPage to click on create new contact button using a business method.
		ContactsPage contactsPageObj = new ContactsPage(driver);
		contactsPageObj.createNewContact();
		
		//Create an object of CreateNewContactPage to create the contact without entering mandatory fields.
		ContactInfoPage createNewContactObj = new ContactInfoPage(driver);
		createNewContactObj.getSaveButton().click();
				
		//Verification
		String expectedText = elib.getDataFromExcel("TestData", 4, 5);
		//String expectedText = "last name";
		String actualText = driver.switchTo().alert().getText();
		
		Assert.assertEquals(actualText, expectedText);
		
		//Handle the Alert popup by accepting it.
		wlib.acceptAlertPopUp(driver);
	}
	
	@Test (groups = {"RegressionTest"})
	public void createContactWithOrganizationNameTest() throws Throwable {
		
		// Create an object to RandomClass to generate a random organization in every execution
		Random random = new Random();
		
		//Randomly generate the org names.
		int randomInt = random.nextInt(10000);
				
		//Read the data from the excel workbook to create organization.
		String orgName = elib.getDataFromExcel("TestData", 1, 2) + ""+randomInt;
			
		//Read the data from the excel workbook
		String firstname = elib.getDataFromExcel("TestData", 5, 2);
		String lastname = elib.getDataFromExcel("TestData", 5, 3);
				
		//Create an object of the HomePage to navigate to the contacts page
		HomePage homePageObj = new HomePage(driver);
		homePageObj.getOrganizationsLink().click();
		
		//Create an object for OrganizationsPage to navigate to the create new Organization page.
		OrganizationsPage organizationPageObj = new OrganizationsPage(driver);
		organizationPageObj.getCreateNewOrganizationLink().click();
		
		//Create an object for createNewOrganizationPage to create an organization.
		CreateOrganizationPage createNewOrgPageObj = new CreateOrganizationPage(driver);
		createNewOrgPageObj.createOrgName(orgName);	
		
		//Create an object for OrganizationInfoPage to verify the organization creation.
		OrganizationInfoPage orgInfoPageObj = new OrganizationInfoPage(driver);
		String actualOrgText = orgInfoPageObj.getOrgFullHeaderText().getText();
		boolean orgnameStatus = actualOrgText.contains(orgName);
		Assert.assertTrue(orgnameStatus);
				
		homePageObj.clickOnContactsLink();
		
		//Create an object for ContactsPage to navigate to the create new contact page.
		ContactsPage contactsPageObj = new ContactsPage(driver);
		contactsPageObj.createNewContact();
		
		//Create an object for createNewContactPage to create a contact.
		ContactInfoPage createNewContactObj = new ContactInfoPage(driver);
		createNewContactObj.createContact(firstname, lastname);
		createNewContactObj.getOrganizationNameLookUp().click();
		wlib.switchToWindow(driver, "module=Accounts&action=Popup");
		createNewContactObj.selectOrgName(orgName);
		wlib.switchToWindow(driver, "module=Contacts&action=EditView");
		createNewContactObj.getSaveButton().click();
		
		//Verification
		String expectedContactText = elib.getDataFromExcel("TestData", 5, 5);
		String actualContactText = contactsPageObj.getHeaderText().getText();
		//String actOrgNameSelectedText = contactsPageObj.getOrgNameTextField().getText();
		
		boolean contactStatus = actualContactText.contains(expectedContactText);
		//boolean orgStatus = actOrgNameSelectedText.equals(orgName);
		
		Assert.assertTrue(contactStatus);
		//Assert.assertTrue(orgStatus);

		
	}
	
	@Test(groups = {"RegressionTest"})
	public void createContactWithImageTest() throws Throwable {
	
		//Read the data from the excel workbook
		String firstname = elib.getDataFromExcel("TestData", 6, 2);		
		String lastname = elib.getDataFromExcel("TestData", 6, 3);
		String filePath = elib.getDataFromExcel("TestData", 6, 4);

		//Create an object of the HomePage to navigate to the contacts page
		HomePage homePageObj = new HomePage(driver);
		homePageObj.clickOnContactsLink();
		
		//Create an object for ContactsPage to navigate to the create new contact page.
		ContactsPage contactsPageObj = new ContactsPage(driver);
		contactsPageObj.createNewContact();
		
		//Create an object for CreateNewContactPage to create a contact
		ContactInfoPage createNewContactObj = new ContactInfoPage(driver);
		createNewContactObj.createContact(firstname, lastname);
		createNewContactObj.getChooseFileButton().sendKeys(filePath);
		createNewContactObj.getSaveButton().click();
		
		//Verification
		String expectedText = elib.getDataFromExcel("TestData", 6, 5);
		String actualText = contactsPageObj.getFullHeaderText().getText();
		boolean status = actualText.contains(expectedText);
		Assert.assertTrue(status);
	}	
}
