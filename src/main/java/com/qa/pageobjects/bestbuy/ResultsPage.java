package com.qa.pageobjects.bestbuy;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.qa.annotations.Name;
import com.qa.element.HtmlElement;
import com.qa.exceptions.HtmlElementsException;
import com.qa.loader.HtmlElementLoader;
import com.qa.page.Page;
import com.qa.utils.driver.BrowserDriver;
import com.qa.utils.enumerations.Availability;

public class ResultsPage extends Page {
	
  private SearchResults results;
  
  private CurrentOffers currentOffers;
  
  @Name("TOTAL_RESULT_COUNT")
  @FindBy(className = "display-total")
  private HtmlElement totalCountElement;
  
  private final String resultsPageError = "results page is not displayed";
  private final String expectedUrl = "SearchResults.aspx";
  private final String expectedTitle = "Results for";
  
  BrowserDriver browserDriver;

  public ResultsPage(WebDriver driver) {
	 super(driver);		 
	 browserDriver = (BrowserDriver)getDriver();
	 
	 HtmlElementLoader.populatePageObject(this, driver);
	 
	 if (!urlContains(expectedUrl) || !titleContains(expectedTitle))
		 throw new HtmlElementsException(resultsPageError);	 
  }  
        
  public DetailsPage selectResult(int index) {	 	 
	 SearchResult result = getOnlineResults().get(index);
	 result.click();
	 
	 return new DetailsPage(getDriver());
  }
  
  private List<SearchResult> getOnlineResults() {
	  return results.filterBy(Availability.ONLINE);  
  }
    
  public int totalCount() {
	  return Integer.parseInt(totalCountElement.getText());
  }
    
  public ResultsPage selectOffer(String name) {	 		 	 
	 Offer offer =  (Offer) currentOffers.offer(name);
	 String offerCount = offer.count();
	 
	 ResultsPage resultPage = selectOfferByJS(name);	 	 	 	 	 
	 
	 browserDriver.waitUntilElementIncludesValue(totalCountElement, 
			                                     offerCount);
	 
	 return resultPage;
  }
  
  private ResultsPage selectOfferByJS(String name) {	  
	  browserDriver.executeJS(clickOfferJS(name));
	  
	  ResultsPage resultPage = new ResultsPage(getDriver());
	  
	  String encodedName = name.replaceAll("[\" \"]", "%2B");
	  if (!resultPage.urlContains(encodedName))
			 throw new HtmlElementsException(name + " is not in url"); 
	  
	  return resultPage;	
  }
      
  private String clickOfferJS(String offerName) {	  
	  String encodedName = offerName.replaceAll("[\" \"]", "+");
	  StringBuilder js = new StringBuilder();
	  
	  js.append("document.querySelector(")
        .append("\"input[data-path='currentoffers0enrchstring%3a")
        .append(encodedName)
        .append("']\").click()");
	  	 
	  return js.toString();
  }
    

}
