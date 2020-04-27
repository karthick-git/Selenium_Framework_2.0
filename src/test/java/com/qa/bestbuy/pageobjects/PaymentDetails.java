package com.qa.bestbuy.pageobjects;

import org.openqa.selenium.support.FindBy;

import com.qa.annotations.Name;
import com.qa.element.HtmlElement;
import com.qa.element.Select;
import com.qa.element.TextInput;
import com.qa.utils.enumerations.PaymentInfo;

@Name("PAYMENT_DETAILS")
@FindBy(id="ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_PnlCreditCardEntry")
public class PaymentDetails extends HtmlElement {
	
	@Name("CARD_NUMBER")
	@FindBy(id="ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_AddCreditCardUC_CreditCardNumberContainer_TxtCardNumber")
	private TextInput cardNumber;
	
	@Name("EXPIRY_MONTH")
	@FindBy(id="ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_AddCreditCardUC_MonthContainer_DdlMonth")
	private Select expiryMonth;
		
	@Name("EXPIRY_YEAR")
	@FindBy(id="ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_AddCreditCardUC_YearContainer_DdlYear")
	private Select expiryYear;

	@Name("CVV")
	@FindBy(id="ctl00_CP_checkoutSections_ctl03_ucPaymentEdit_UCEditCreditCard_AddCreditCardUC_CIDNumberContainer_TxtCID")
	private TextInput cvv;	
		
	public void fillInfo() {
		cardNumber.sendKeys(PaymentInfo.CARD_NUMBER.toString());
		expiryMonth.selectByValue(PaymentInfo.MONTH.toString());
		expiryYear.selectByValue(PaymentInfo.YEAR.toString());
		cvv.sendKeys(PaymentInfo.CVV.toString());
	}	

}
