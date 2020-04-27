package com.qa.TestCase;

import static org.testng.AssertJUnit.assertTrue;

import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.BaseClass.BaseClass;
import com.qa.bestbuy.pageobjects.BasketPage;
import com.qa.bestbuy.pageobjects.CheckOut;
import com.qa.bestbuy.pageobjects.DetailsPage;
import com.qa.bestbuy.pageobjects.HomePage;
import com.qa.bestbuy.pageobjects.ResultsPage;
import com.qa.bestbuy.pageobjects.SecureCheckout;
import com.qa.lib.ListnerImpl;
import com.qa.utils.TextFile;
import com.qa.utils.enumerations.MemberType;

@Listeners(ListnerImpl.class)
public class TestClass extends BaseClass{
	
	@BeforeSuite
	public void setup() throws MalformedURLException {
		initialization();
		logger = LogManager.getLogger(TestClass.class);
		logger.info(this.getClass().getSimpleName());
	}

	public TestClass() {
		super();
	}

	private void addToLog(String text) {
		(new TextFile()).add("\n " + text);
	}
	
	@DataProvider(name = "keywordValues")
	public static Object[][] primeNumbers() {
	      return new Object[][] {
	    	  						{"iphone"}, 
//	    	  						{"ipad mini"}, 
//	    	  						{"iphone 6"},
//	    	  						{"screen protector"},
//	    	  						{"screens"},
//	    	  						{"charger samsung"},
//	    	  						{"charger iphone"},
//	    	  						{"watch"},
//	    	  						{"watches for women"},
	    	  					};
	   }

	@Test(dataProvider = "keywordValues")
	public void transactionFailsForIncorrectPaymentInfo(String keyword) throws InterruptedException { 	
		
		HomePage homePage = new HomePage(driver);					
		
		ResultsPage resultsPage = homePage.open()
				                          .search(keyword);
					
		DetailsPage detailsPage = resultsPage.selectOffer("Online Only")
				                             .selectResult(5);
		
		BasketPage basketPage = detailsPage.addToCart();
		
		SecureCheckout secureCheckout = basketPage.checkOut();
		
		CheckOut checkout = secureCheckout.select(MemberType.GUEST)
										  .continueCheckout();
		
		checkout.shipTo().fillInfo();
		checkout.continueToPayment();
		checkout.paymentMethod().selectCreditCard();
		checkout.paymentDetails().fillInfo();
		checkout.checkSameAsShipping();
		checkout.submitPayment();
		
		assertTrue(checkout.errors().messages().size() > 0);
	
		addToLog(checkout.errors().toString());		
			
	}

}
