//package com.qa.TestCase;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//
//import org.apache.logging.log4j.LogManager;
//import org.openqa.selenium.By;
//import org.testng.Assert;
//import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import com.helger.commons.io.resource.FileSystemResource;
//import com.paypal.selion.platform.dataprovider.DataProviderFactory;
//import com.paypal.selion.platform.dataprovider.SeLionDataProvider;
//import com.qa.BaseClass.BaseClass;
//
//public class MercuryRegistrationYML extends BaseClass {
//	public MercuryRegistrationYML() {
//		super();
//	}
//
//	@BeforeSuite
//	public void setup() throws MalformedURLException {
//		initialization();
//		logger = LogManager.getLogger(MercuryRegistrationYML.class);
//		logger.info(this.getClass().getSimpleName());
//	}
//
//	@DataProvider(name = "yamlDataProvider")
//	public Object[][] getYamlDataProvider() throws IOException {
//	  FileSystemResource resource = new FileSystemResource(prop.getProperty("ymlFile"));
//	  SeLionDataProvider dataProvider = DataProviderFactory.getDataProvider(resource);
//	  return dataProvider.getAllData();
//	}
//
//	@Test(dataProvider = "yamlDataProvider")
//	public static void userRegistration(String FirstName, String LastName, String phone, String email, String address,
//			String city, String state, String postalCode, String country, String userId, String pwd, String confirmPwd)
//			throws Exception {
//		logger1 = extent.createTest("User Registration Test in Mercury Tours Page");
//		logger.debug("Validation of User Registration Test in Mercury Tours Page");
//		
//		//Filling the registration form
//		driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(FirstName);
//		driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(LastName);
//		driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(phone);
//		driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(email);
//		driver.findElement(By.xpath("//input[@name='address1']")).sendKeys(address);
//		driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
//		driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
//		driver.findElement(By.xpath("//input[@name='postalCode']")).sendKeys(postalCode);
//		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(userId);
//		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(pwd);
//		driver.findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys(confirmPwd);
//		driver.findElement(By.xpath("//input[@name='register']")).click();
//		Thread.sleep(2000);
//		
//		//validating the registration
//		String salutation = driver.findElement(By.xpath("//td/p[1]/font/b")).getText();
//		Assert.assertEquals(salutation, "Dear "+FirstName+" "+LastName+",");
//		String username = driver.findElement(By.xpath("//td/p[3]/a/font/b")).getText();
//		Assert.assertEquals(username, "Note: Your user name is "+userId+".");
//		
//		//navigating back to registration page
//		driver.findElement(By.xpath("//a[text()='REGISTER']")).click();
//		
//	}
//
//}
