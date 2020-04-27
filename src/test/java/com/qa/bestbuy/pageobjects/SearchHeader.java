package com.qa.bestbuy.pageobjects;

import org.openqa.selenium.support.FindBy;

import com.qa.annotations.Name;
import com.qa.element.Button;
import com.qa.element.HtmlElement;
import com.qa.element.TextInput;

@Name("SEARCH_HEADER")
@FindBy(className = "main-navigation-container") 
public class SearchHeader extends HtmlElement{
		
	@Name("SEARCH_FIELD")
	@FindBy(id = "ctl00_MasterHeader_ctl00_uchead_GlobalSearchUC_TxtSearchKeyword")	
	private TextInput searchKeywordTxt;
	
	@Name("SEARCH_BUTTON")
	@FindBy(id = "ctl00_MasterHeader_ctl00_uchead_GlobalSearchUC_BtnSubmitSearch")
	private Button searchBtn;	
	  		
	public void search(String keyword) {
		searchKeywordTxt.click();  
		searchKeywordTxt.clear();
		searchKeywordTxt.sendKeys(keyword);
		searchBtn.click();		
	}
	
}
