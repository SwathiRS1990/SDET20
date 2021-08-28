package com.vtiger.comcast.pomrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	public ContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//img[@src= 'themes/softed/images/btnL3Add.gif']")
	private WebElement createNewContactButton;
	
	@FindBy(xpath = "//span[@class = 'dvHeaderText']")
	private WebElement headerText;
	
	@FindBy(xpath = "//span[@class = 'dvHeaderText']/..")
	private WebElement fullHeaderText;

	@FindBy(name = "account_name")
	private WebElement orgNameTextField ;
	
	public WebElement getOrgNameTextField() {
		return orgNameTextField;
	}

	public WebElement getCreateNewContactButton() {
		return createNewContactButton;
	}
	
	public WebElement getHeaderText() {
		return headerText;
	}

	public void createNewContact()
	{
		createNewContactButton.click();
	}

	public WebElement getFullHeaderText() {
		return fullHeaderText;
	}

}
