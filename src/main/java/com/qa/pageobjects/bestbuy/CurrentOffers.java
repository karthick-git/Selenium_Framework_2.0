package com.qa.pageobjects.bestbuy;

import java.util.List;

import org.openqa.selenium.support.FindBy;

import com.qa.annotations.Name;
import com.qa.element.HtmlElement;
import com.qa.exceptions.HtmlElementsException;

@Name("CURRENT_OFFERS")
@FindBy(xpath = "//nav[@class='menu-facets solr_facets']") 
public class CurrentOffers extends HtmlElement{	
	
	@Name("OFFER_ELEMENTS")
	@FindBy(xpath = "//li[contains(@class, 'filter')]")
	private List<Offer> offers;		
		
	public Offer offer(String offerName) {
		for(Offer offer: offers) 
			if (offer.name().contains(offerName))
				return offer;
			
		throw new HtmlElementsException(offerName + " offer not found");			
	}	
	
}
