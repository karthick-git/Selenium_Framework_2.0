package com.qa.pageobjects.bestbuy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.annotations.FindByJS;
import com.qa.annotations.Name;
import com.qa.exceptions.HtmlElementsException;
import com.qa.loader.HtmlElementLoader;
import com.qa.page.Page;

public class DetailsPage extends Page {
	       
  @Name("ADD_TO_CART")
  @FindByJS("document.getElementById('btn-cart')")
  private WebElement addToCartButton;
  
  private final String detailsPageError = "details page is not displayed";
  
  private final String expectedUrl = "www.bestbuy.ca/en-ca/product";
    
  public DetailsPage(WebDriver driver) {
	 super(driver); 	 	 
	 HtmlElementLoader.populatePageObject(this, driver);
	 
	 if (urlContains(expectedUrl) == false) 		
	 	 throw new HtmlElementsException(detailsPageError);
	 
	 Modal modal = new Modal(driver);
	 modal.close();
  }
  
  public BasketPage addToCart() {	  
	  addToCartButton.click();
	  BasketPage basketPage = new BasketPage(getDriver());
	  
	  if (basketPage.cart().count() != 1 || basketPage.productCount() != 1)
		  throw new RuntimeException("product is not added to cart");
	  
	  return basketPage;
  }
  
}
