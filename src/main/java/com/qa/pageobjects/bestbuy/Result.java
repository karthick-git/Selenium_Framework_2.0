package com.qa.pageobjects.bestbuy;

import com.qa.element.Link;

public interface Result  {
			
	public Link image();

	public String title();
	
	public void click();
	
	public String availability();
		
}
