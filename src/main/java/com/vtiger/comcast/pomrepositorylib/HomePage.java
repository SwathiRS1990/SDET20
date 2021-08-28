package com.vtiger.comcast.pomrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.vtiger.comcast.genericutility.WebDriverUtility;

public class HomePage extends WebDriverUtility{

	WebDriver driver ;
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText = "Organizations")
	private WebElement organizationsLink;
	
	@FindBy(name = "accountname")
	private WebElement accountName;	

	@FindBy(linkText = "Contacts")
	private WebElement contactsLink;
	
	@FindBy(xpath = "//img[@src = 'themes/softed/images/user.PNG']")
	private WebElement menuList;
	
	@FindBy(linkText = "Sign Out")
	private WebElement signOutLink;
	
	public WebElement getOrganizationsLink() {
		return organizationsLink;
	}

	public WebElement getMenuList() {
		return menuList;
	}

	public WebElement getAccountName() {
		return accountName;
	}

	public WebElement getContactsLink() {
		return contactsLink;
	}

	public WebElement getSignOutLink() {
		return signOutLink;
	}
	
	public void clickOnContactsLink()
	{
		contactsLink.click();
	}
	
	public void logout() {
		WebDriverUtility wlib = new WebDriverUtility();
		wlib.mouseHover(driver, menuList);
		signOutLink.click();
	}
	
}
