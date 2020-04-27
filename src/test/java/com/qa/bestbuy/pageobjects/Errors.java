package com.qa.bestbuy.pageobjects;

import java.util.List;

import org.openqa.selenium.support.FindBy;

import com.qa.annotations.Name;
import com.qa.element.HtmlElement;

@Name("ERRORS")
@FindBy(id="ctl00_CP_ErrorSummaryUC12_ValidationSummary1")            
public class Errors extends HtmlElement {
	
	@Name("ERROR_MESSAGES")
	@FindBy(xpath=".//ul/li")
	private List<HtmlElement> messages;

	public List<HtmlElement> messages() {
		return messages;
	}
		
	@Override
	public String toString() {
		StringBuilder value = new StringBuilder();
		
		for (HtmlElement error: messages)
			value.append("\n\n").append(error.getText());
		
		return value.toString();		
	}
	
}
