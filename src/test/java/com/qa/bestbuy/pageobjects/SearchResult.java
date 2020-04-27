package com.qa.bestbuy.pageobjects;

import org.openqa.selenium.support.FindBy;

import com.qa.annotations.Name;
import com.qa.element.HtmlElement;
import com.qa.element.Link;

/* implements a search result */
@Name("RESULT")
public class SearchResult extends HtmlElement implements Result {
			
	@Name("IMAGE")
	@FindBy(xpath=".//div[@class='prod-image']/a")
	Link image;
	
	@Name("TITLE")
	@FindBy(xpath=".//h4[@class='prod-title']/a")
	Link title;
	
	@Name("AVAILABILITY")
	@FindBy(xpath=".//ul[@class='prod-availability grid-layout-prod-availability']/li")
	HtmlElement availability;	
	
	@Override
	public Link image() {	
		return image;
	}

	@Override
	public String title() {	
		return title.getText();
	}
	
	@Override
	public void click() {
		image.click();
	}
	
	@Override
	public String availability() {
		String value = "";
		try {
			value = availability.getText();
		}
		catch (Exception ex) {
			value = "";
		}
		return value;
	}
		
}
