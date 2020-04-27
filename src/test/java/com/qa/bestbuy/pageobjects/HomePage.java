package com.qa.bestbuy.pageobjects;

import org.openqa.selenium.WebDriver;

import com.qa.exceptions.HtmlElementsException;
import com.qa.loader.HtmlElementLoader;
import com.qa.page.Page;

public class HomePage extends Page {
	
	private final String siteUrl = "http://www.bestbuy.ca";
	private final String expectedUrl = "www.bestbuy.ca";
	private final String expectedTitle = "Best Buy Canada";
	
	private final String homePageError = "home page is not displayed";
	
	private SearchHeader searchHeader;

	public HomePage(WebDriver driver) {
		super(driver);
		HtmlElementLoader.populatePageObject(this, driver);
	}

	public HomePage open() throws InterruptedException {		
		getDriver().get(siteUrl);		 
		Thread.sleep(6000);
		
		if (!urlContains(expectedUrl) || !titleContains(expectedTitle)) 
			throw new HtmlElementsException(homePageError);
				
		return this;
	}		
	
	public ResultsPage search(String keyword) {				
		searchHeader.search(keyword);
	
		ResultsPage resultsPage = new ResultsPage(getDriver());
		if (resultsPage.totalCount() == 0)
			throw new RuntimeException("there are no results for the search");
		
		return resultsPage;
	}
		
}
