package com.qa.TestCase.amazon;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.BaseClass.BaseClass;
import com.qa.lib.TakeScreenshot;
import com.qa.pageobjects.amazon.CustomerHelpPage;
import com.qa.pageobjects.amazon.DashBoardPage;

public class CustomerHelpPageTest extends BaseClass
{

	CustomerHelpPage Cus;
	
	public CustomerHelpPageTest()
	{

		super();
	}
	
	@BeforeTest 
	public void DashBoardIntialize()
	{
		Cus=new CustomerHelpPage();
	}
	
	@Test(priority=7,description="MouseHover to Department and Select Baby Products")
	public void ConsumerLogoTest() throws Exception
	{
		logger1 = extent.createTest("Validation of Amazon Customer Login");
		Cus.MouseHoverDept();
		Thread.sleep(5000);
		TakeScreenshot.captureScreenshot(driver, "MouseHovertoDept");
		
	}
	
	@Test(priority=8,description="MouseHover and Logout Page From Amazon")
	public void AmazonLogout() throws InterruptedException
	{
		logger1 = extent.createTest("Validation of Amazon Customer Logout Functionality");
		Cus.AmazonLogout();
		//logger.info("Sucesfully logout from Amazon");
		
		
	}
	
}
