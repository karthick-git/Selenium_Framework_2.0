package com.qa.pageobjects.amazon;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.BaseClass.BaseClass;


public class AmazonLoginPage extends BaseClass
{

	@FindBy(xpath="//span[contains(text(),'Hello. Sign in')]")
	WebElement HelloSignin;
	
	@FindBy(xpath="//span[@class='nav-sprite nav-logo-base']")
	WebElement Logo;
	
	@FindBy(xpath="//input[@id='ap_email']")
	WebElement Email;
	
	@FindBy(xpath="//span[@id='continue']")
	WebElement Continue;
	
	@FindBy(xpath="//input[@id='ap_password']")
	WebElement Password;
	
	@FindBy(xpath="//input[@id='signInSubmit']")
	WebElement SigninButton;
	
	public AmazonLoginPage() 
	{
	PageFactory.initElements(driver, this);	
	}
	
	public boolean isLogoPresent()
	{
		return Logo.isDisplayed();
		
	}
	public String getTitleConsumerPortal()
	{
		return driver.getTitle();
		
	}
	
	public void goToAmazonLoginPage() {
		HelloSignin.click();
	}
	
	public void doAmazonLogin(String email, String password) throws InterruptedException
	{
		Thread.sleep(1000);
		Email.sendKeys(email);
		Continue.click();
		Thread.sleep(2000);
		Password.sendKeys(password);
		SigninButton.click();
		
	}
	
	public DashBoardPage returnDashboardPage()
	{
		return new DashBoardPage();
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
