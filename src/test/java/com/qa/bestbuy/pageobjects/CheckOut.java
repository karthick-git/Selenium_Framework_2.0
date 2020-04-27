package com.qa.bestbuy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.annotations.FindByJS;
import com.qa.annotations.Name;
import com.qa.exceptions.HtmlElementsException;
import com.qa.loader.HtmlElementLoader;
import com.qa.page.Page;
import com.qa.utils.driver.BrowserDriver;

public class CheckOut extends Page {

	private ShipTo shipTo;
	private PaymentMethod paymentMethod;	
	private PaymentDetails paymentDetails;
	private Errors errors;
	
	private final String expectedUrl = "https://www-ssl.bestbuy.ca/Order/Checkout.aspx";
	
	private final String checkoutPageError = "user info checkout is not displayed";
	
	@Name("CONTINUE")
	@FindByJS("document.getElementById('ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_btnContinueFromShipping')")
	private WebElement continueButton;
	
	@Name("SUBMIT")
	@FindByJS("document.getElementById('ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_BtnContinueFromPayment')")
	private WebElement submitButton;
	
	@Name("CHECK_SAME_AS_SHIPPING")
	@FindByJS("document.getElementById('ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_ChkSameAsShipping')")
	private WebElement sameAsShippingButton;
	
	public CheckOut(WebDriver driver) {
		 super(driver); 
		 		 
		 HtmlElementLoader.populatePageObject(this, driver);
		 
		 if (urlContains(expectedUrl) == false) 
			 throw new HtmlElementsException(checkoutPageError);		 
	}	
		
	public ShipTo shipTo() {
		return shipTo;	
	}
		
	public PaymentMethod paymentMethod() {				
		return paymentMethod;
	}
		
	public PaymentDetails paymentDetails() {				
		return paymentDetails;
	}
		
	public Errors errors() {				
		return errors;
	}

	public void continueToPayment() {		
		continueButton.click();
		((BrowserDriver)getDriver()).waitUntilVisible(paymentMethod);		
	}
		
	public void checkSameAsShipping() {				
		sameAsShippingButton.click();
	}
		
	public void submitPayment() {		
		submitButton.click();
	}	
	
}
