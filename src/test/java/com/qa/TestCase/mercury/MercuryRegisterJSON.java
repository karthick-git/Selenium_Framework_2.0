package com.qa.TestCase.mercury;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.BaseClass.BaseClass;
import com.qa.utils.JsonReader;

public class MercuryRegisterJSON extends BaseClass {

	/*
	 * @FindBy(xpath = "//input[@name='firstName']") static WebElement FName;
	 * 
	 * @FindBy(xpath = "//input[@name='lastName']") static WebElement LName;
	 * 
	 * @FindBy(xpath = "//input[@name='phone']") static WebElement Phone;
	 * 
	 * @FindBy(xpath = "//input[@name='userName']") static WebElement Email;
	 * 
	 * @FindBy(xpath = "//input[@name='address1']") static WebElement Address;
	 * 
	 * @FindBy(xpath = "//input[@name='city']") static WebElement City;
	 * 
	 * @FindBy(xpath = "//input[@name='state']") static WebElement State;
	 * 
	 * @FindBy(xpath = "//input[@name='postalCode']") static WebElement PostalCode;
	 * 
	 * @FindBy(xpath = "//input[@name='email']") static WebElement UserID;
	 * 
	 * @FindBy(xpath = "//input[@name='password']") static WebElement Password;
	 * 
	 * @FindBy(xpath = "//input[@name='confirmPassword']") static WebElement
	 * ConfirmPassword;
	 * 
	 * @FindBy(xpath = "//input[@name='register']") static WebElement Submit;
	 */
	public MercuryRegisterJSON() {
		super();
		//PageFactory.initElements(driver, this);
	}

	@BeforeSuite
	public void setup() throws MalformedURLException {
		initialization();
		logger = LogManager.getLogger(MercuryRegisterJSON.class);
		logger.info(this.getClass().getSimpleName());
	}

	@DataProvider(name = "registration data")
	public Object[][] passData() throws IOException, ParseException {
		// return JsonReader.getJSONdata(AppConfig.getJsonPath()+"Registration.json",
		// "Registration Data",3);
		return JsonReader.getdata(prop.getProperty("jsonFile"), "Registration Data", 2, 12);
	}

	@Test(dataProvider = "registration data")
	public static void userRegistration(String FirstName, String LastName, String phone, String email, String address,
			String city, String state, String postalCode, String country, String userId, String pwd, String confirmPwd)
			throws Exception {
		logger1 = extent.createTest("User Registration Test in Mercury Tours Page");
		logger.debug("Validation of User Registration Test in Mercury Tours Page");
		
		//Filling the registration form
		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(FirstName);
		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(LastName);
		driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(phone);
		driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='address1']")).sendKeys(address);
		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
		driver.findElement(By.xpath("//input[@name='postalCode']")).sendKeys(postalCode);
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(userId);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pwd);
		driver.findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys(confirmPwd);
		driver.findElement(By.xpath("//input[@name='register']")).click();
		Thread.sleep(2000);
		
		//validating the registration
		String salutation = driver.findElement(By.xpath("//td/p[1]/font/b")).getText();
		Assert.assertEquals(salutation, "Dear "+FirstName+" "+LastName+",");
		String username = driver.findElement(By.xpath("//td/p[3]/a/font/b")).getText();
		Assert.assertEquals(username, "Note: Your user name is "+userId+".");
		
		//navigating back to registration page
		driver.findElement(By.xpath("//a[text()='REGISTER']")).click();
		
	}

}
