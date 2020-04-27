package com.qa.bestbuy.pageobjects;

import org.openqa.selenium.support.FindBy;

import com.qa.annotations.Name;
import com.qa.element.HtmlElement;
import com.qa.element.Link;

@Name("SELECT_PAYMENT_METHOD")
@FindBy(id = "ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_pnlSelectPaymentMethod")
public class PaymentMethod extends HtmlElement {
	
	@Name("SELECT_CREDIT_CARD")
	@FindBy(id="ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_lnkCreditCard")
	private Link selectCreditCard;	
		
	public void selectCreditCard() {				
		selectCreditCard.click();		
	}	
	
}
