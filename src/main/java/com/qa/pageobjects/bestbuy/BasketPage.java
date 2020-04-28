package com.qa.pageobjects.bestbuy;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qa.annotations.FindByJS;
import com.qa.annotations.Name;
import com.qa.element.HtmlElement;
import com.qa.exceptions.HtmlElementsException;
import com.qa.loader.HtmlElementLoader;
import com.qa.page.*;
import com.qa.pageobjects.bestbuy.Cart;
import com.qa.pageobjects.bestbuy.SecureCheckout;

public class BasketPage extends Page {	
	
	private Cart cart;
	  
	@Name("PRODUCTS_IN_BASKET")
	@FindBy(className="prod-title-links")
	private List<HtmlElement> productList;	
	
	@Name("CHECKOUT_BUTTON")
	@FindByJS("document.getElementById('ctl00_CP_btnSubmitOrder')")
	private WebElement checkoutButton;
	
	private final String basketPageError = "basket page is not displayed";
	private final String checkoutButtonError = "cannot click checkout button after 5 attempts";
	
	private final String expectedUrl = "https://www-ssl.bestbuy.ca/Order/Basket.aspx";
	
	public BasketPage(WebDriver driver) {
		 super(driver); 
				 
		 HtmlElementLoader.populatePageObject(this, driver);
		 
		 if (urlContains(expectedUrl) == false) 			 
			 throw new HtmlElementsException(basketPageError);		 
	}
	  		
	public Cart cart() {
		return cart;
	}

	public int productCount() {		
		return productList.size();
	}
	
	public SecureCheckout checkOut() {	
		int i = 0;
		do {
			try {
				checkoutButton.click();
			}
			catch (Exception ex) {
				reload();
			}			
			i++;			 
		} while (new SecureCheckout(getDriver()).isAt() == false && i < 5);
		
		if (i == 5) 
			throw new HtmlElementsException(checkoutButtonError);		
		
		return new SecureCheckout(getDriver());
	}

}
