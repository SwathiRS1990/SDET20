package com.vtiger.comcast.genericutility;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * This class has all the generic methods for WebDriver
 * @author Swathi
 *
 */

public class WebDriverUtility {

	
	/**
	 * This method is used to give implicit wait condition for the webPage loading.
	 * @param driver
	 */
	public void waitForPageLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	/**
	 * This method is used to provide explicit wait condition for the WebElements.
	 * @param driver
	 * @param ele
	 */
	public void waitForElementPresent (WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method is used to select an option for the dropdown list box using visible text.
	 * @param element
	 * @param text
	 */
	public void selectFromDropdownListBox(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}
	
	/**
	 * This is an overloaded method to select an option from the dropdown list box using index.
	 * @param element
	 * @param index
	 */
	public void selectFromDropdownListBox(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	
	/**
	 * This method is used to mouse hover on the WebElement. 
	 * @param driver
	 * @param target
	 */
	public void mouseHover(WebDriver driver, WebElement target) {
		Actions actions = new Actions(driver);
		actions.moveToElement(target).perform();		
	}
	
	
	/**
	 * This method is used to switch the driver control to the child windows.
	 * @param driver
	 * @param title
	 */
	public void switchToWindow(WebDriver driver, String windowURL) {
		Set<String> childWindowIds = driver.getWindowHandles();
		Iterator<String> iterator = childWindowIds.iterator();
		while(iterator.hasNext()) {
			String windowId = iterator.next();
			String url = driver.switchTo().window(windowId).getCurrentUrl();
			if(url.contains(windowURL)) {
				break;
			}			
		}
	}
	
	
	/**
	 * This method is used to accept the alert popup.
	 * @param driver
	 */
	public void acceptAlertPopUp(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	/**
	 * This method is used to dismiss the alert popup.
	 * @param driver
	 */
	public void dismissAlertPopUp(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
}
