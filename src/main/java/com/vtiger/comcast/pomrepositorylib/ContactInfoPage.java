package com.vtiger.comcast.pomrepositorylib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
	WebDriver driver;
	
	String parentWindowId ;
	
	public ContactInfoPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "firstname")
	private WebElement firstname;
	
	@FindBy(name = "lastname")
	private WebElement lastname;
	
	@FindBy(xpath = "//input[@title = 'Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(xpath = "//input[@name = 'account_id']/following-sibling::img[@src='themes/softed/images/select.gif']")
	private WebElement organizationNameLookUp;

	@FindBy(xpath = "//a[contains(@onclick,'set_return_contact_address')]")
	private List<WebElement> selectOrganizationName;
	
	@FindBy(xpath = "//input[@type = 'file']")
	private WebElement chooseFileButton;
	
	@FindBy(xpath = "//input[@name= 'contact_id']/following-sibling::img[@src = 'themes/softed/images/select.gif']")
	private WebElement reportsToLookUp;
	
	@FindBy(linkText = "Swathi")
	private WebElement selectContactName;
	
	public WebElement getFirstname() {
		return firstname;
	}

	public WebElement getLastname() {
		return lastname;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	public WebElement getOrganizationNameLookUp() {
		return organizationNameLookUp;
	}

	public List<WebElement> getSelectOrganizationName() {
		return selectOrganizationName;
	}

	public WebElement getChooseFileButton() {
		return chooseFileButton;
	}

	public WebElement getSelectContactName() {
		return selectContactName;
	}

	public WebElement getReportsToLookUp() {
		return reportsToLookUp;
	}

	public void createContact(String firstName, String lastName) {
		firstname.sendKeys(firstName);
		lastname.sendKeys(lastName);
		//saveButton.click();
	}
	
	public void selectOrgName(String orgName) {
		for (WebElement ele: selectOrganizationName) {		
			if(ele.getText().equalsIgnoreCase(orgName)) {
				System.out.println(ele.getText());
				JavascriptExecutor jse = (JavascriptExecutor) driver;
				jse.executeScript("arguments[0].click;", ele);
				break;
			}			
		}
		
	}
		
}
