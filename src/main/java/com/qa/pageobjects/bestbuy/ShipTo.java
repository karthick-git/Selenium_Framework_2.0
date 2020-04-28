package com.qa.pageobjects.bestbuy;

import org.openqa.selenium.support.FindBy;

import com.qa.annotations.Name;
import com.qa.element.HtmlElement;
import com.qa.element.Link;
import com.qa.element.Select;
import com.qa.element.TextInput;
import com.qa.utils.enumerations.UserInfo;

@Name("SHIP_TO")
@FindBy(className = "ship-to-address-form")
public class ShipTo extends HtmlElement {
	
	@Name("FIRST_NAME")
	@FindBy(id="ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_FirstNameContainer_TxtFirstName")
	private TextInput firstNameTxtBox;
	
	@Name("LAST_NAME")
	@FindBy(id="ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_LastNameContainer_txtLastName")
	private TextInput lastNameTxtBox;
		
	@Name("ADDRESS")
	@FindBy(id="ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_AddressLine1Container_TxtAddressLine1")
	private TextInput addressTxtBox;

	@Name("CITY")
	@FindBy(id="ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_CityContainer_TxtCity")
	private TextInput cityTxtBox;	
	
	@Name("PROVINCE")
	@FindBy(id="ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_StateContainer_DdlState")
	private Select provinceList;
	
	@Name("POSTAL_CODE")
	@FindBy(id="ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_PostalCodeContainer_TxtZipCode")
	private TextInput postalCodeTxtBox;
	
	@Name("PHONE1")
	@FindBy(id="ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_PhoneContainer_TxtPhone")
	private TextInput phone1TxtBox;
	
	@Name("PHONE2")
	@FindBy(id="ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_Phone1Container_TxtPhone1")
	private TextInput phone2TxtBox;
	
	@Name("PHONE3")
	@FindBy(id="ctl00_CP_checkoutSections_ctl01_DeliveryOptionTabs1_manageShippingAddress_oeaUseNew_addressUC_Phone2Container_TxtPhone2")
	private TextInput phone3TxtBox;
	
	@Name("CONTINUE")
	@FindBy(xpath="//div[@class='btn btn-primary btnContinueFromShipping']/a")
	private Link continueBtn;	
		
	public void fillInfo() {
		firstNameTxtBox.sendKeys(UserInfo.FIRST_NAME.toString());
		lastNameTxtBox.sendKeys(UserInfo.LAST_NAME.toString());

		addressTxtBox.sendKeys(UserInfo.ADDRESS.toString());
		cityTxtBox.sendKeys(UserInfo.CITY.toString());	
		provinceList.selectByValue(UserInfo.PROVINCE.toString());
		
		postalCodeTxtBox.clear();
		postalCodeTxtBox.sendKeys(UserInfo.POSTAL_CODE.toString());
		
		phone1TxtBox.sendKeys(UserInfo.PHONE1.toString());
		phone2TxtBox.sendKeys(UserInfo.PHONE2.toString());
		phone3TxtBox.sendKeys(UserInfo.PHONE3.toString());				
	}	

}
