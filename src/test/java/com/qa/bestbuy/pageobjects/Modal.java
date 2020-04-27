package com.qa.bestbuy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.exceptions.HtmlElementsException;
import com.qa.page.Page;
import com.qa.utils.driver.BrowserDriver;

public class Modal extends Page {
	
	private WebDriver driver;
	
	private final By modalXpath = By.xpath("//div[@class='ui-modal-dialog ui-modal-small-old']");
	private final By dismissButtonXpath = By.xpath("//a[@data-dismiss='modal']");
	
	private final String modalError = "the survey modal is still displayed";
	
	public Modal(WebDriver driver) {
		super(driver);
	}
		
	private Boolean isVisible() {
		boolean isVisible = false;
		try {
			WebElement modalElement = driver.findElement(modalXpath);
			isVisible = modalElement.isDisplayed();
		}
		catch (Exception exception) {
			isVisible = false;
		}
		
		return isVisible;
	}
	
	public void close() {
		if (isVisible() == true) {
			WebElement dismissButton = ((BrowserDriver) driver).findClickableElement(dismissButtonXpath);
			dismissButton.click();
		}
		
		if (isVisible() == true) 
			throw new HtmlElementsException(modalError);		
	}
}
