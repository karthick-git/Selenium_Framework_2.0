package com.qa.pageobjects.bestbuy;

import org.openqa.selenium.support.FindBy;

import com.qa.annotations.Name;
import com.qa.element.HtmlElement;

/* implements functionality for a current offer */
@Name("OFFER")
public class Offer extends HtmlElement{	
	
	@Name("NAME")
	@FindBy(xpath = ".//span[@class='item']")
	private HtmlElement name;
	
	@Name("COUNT")
	@FindBy(xpath = ".//span[@class='count']")
	private HtmlElement count;
	
	public String attribute(String name) {
		return getWrappedElement().getAttribute(name);
	}
		
	public String name() {
		return name.getText();			
	}
		
	public String count() {
		String countText = count.getText().replaceAll("[()]", "");
		return countText;
	}
	
}
