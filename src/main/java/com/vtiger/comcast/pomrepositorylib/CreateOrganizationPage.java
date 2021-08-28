package com.vtiger.comcast.pomrepositorylib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganizationPage {
	
	WebDriver driver ;
	public CreateOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	@FindBy(name = "accountname")
	private WebElement orgName;
	
	@FindBy(xpath = "//input[@title = 'Save [Alt+S]']")
	private WebElement orgSaveButton;

	public WebElement getOrgName() {
		return orgName;
	}

	public WebElement getOrgSaveButton() {
		return orgSaveButton;
	}
	
	public void createOrgName(String name) {
		orgName.sendKeys(name);
		orgSaveButton.click();
	}

}
