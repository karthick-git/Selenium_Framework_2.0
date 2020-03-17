package com.qa.TestCase;

import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.qa.BaseClass.BaseClass;
import com.qa.TestPackage.AmazonLoginPage;
import com.qa.lib.TakeScreenshot;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class AmazonLoginPageTest extends BaseClass {

	AmazonLoginPage Amz;

	@BeforeSuite
	public void setup() throws MalformedURLException {
		initialization();
		Amz = new AmazonLoginPage();
		logger = LogManager.getLogger(AmazonLoginPageTest.class);
		logger.info(this.getClass().getSimpleName());
	}

	public AmazonLoginPageTest() {
		super();
	}

	@Test(priority = 1, description = "Validation of Logo On Amazon Home Page")
	@Description("Validation of Logo On Amazon Home Page-Allure Description")
	@Epic("Amazon Application Validation")
	@Feature("Amazon Home Page Validation")
	@Story("Home page Logo Validation")
	@Step("Logo Validation") 
	@Severity(SeverityLevel.TRIVIAL)
	public void AmazonLogoTest() throws Exception {
		logger1 = extent.createTest("Validation of Logo On Amazon Home Page");
		logger.debug("Validation of Logo On Amazon Home Page");
		boolean LogTest = Amz.LogoPresent();
		Assert.assertTrue(LogTest, "Logo is not present in Amazon.com Application");
		TakeScreenshot.captureScreenshot(driver, "AmazonLogin");
	}

	@Test(priority = 2, description = "Validation of Title On Amazon Home Page")
	public void ConsumerPortalTittle() {
		logger1 = extent.createTest("Validation of Title On Amazon Home Page");
		logger.debug("Validation of Title On Amazon Home Page");
		String Tittle = Amz.ConsumerPortalGetTittle();
		Assert.assertEquals(Tittle,
				"Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in");
		logger.info("Title Validated Succesfully on Amazon Home Page");

	}

	/*
	 * @Test(priority = 3, description = "Amazon Customer Login Page") public void
	 * ConsumerLogoTest() throws Exception { logger1 =
	 * extent.createTest("Validation of Amazon Customer Login");
	 * logger.debug("Validation of Amazon Customer Login"); Amz.AmazonLogin();
	 * logger.info("Amazon Customer Login Validation is Succesfull");
	 * Amz.DashboardPage(); TakeScreenshot.captureScreenshot(driver,
	 * "DashboardPage");
	 * 
	 * }
	 */
}
