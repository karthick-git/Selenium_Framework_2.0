package com.qa.BaseClass;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//The below 2 import statements are used in case of log4j2
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;

//The below 2 import statements are used in case of log4j
//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.lib.TakeScreenshot;
import com.qa.lib.WebElementListener;
import com.qa.utils.driver.ImprovedDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass{

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebElementListener eventListener;
	public static Logger logger;
	public static ExtentTest logger1;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	static String nodeURL;
	
	public static XWPFDocument docx;
	public static FileOutputStream out;
	public static InputStream pic;
	public static XWPFRun run;	

	@BeforeSuite

	public void ReportSetup() throws IOException {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") +"/test-output/STMExtentReport1.html");
		extent = new ExtentReports ();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Application Name", "Amazon");
		extent.setSystemInfo("User Name", "Shishimaru");
		extent.setSystemInfo("Envirnoment", "Production");

		htmlReporter.config().setDocumentTitle("Sample Automation Testing Report");
		htmlReporter.config().setReportName("Sample Automation Testing");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);	
	}


	public BaseClass(){
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/src/main/resources/"
					+ "Conf.properties");
			prop.load(ip);
			
			//Use the below 2 lines in case of using log4j
			//logger=Logger.getLogger("BaseClass");
			//PropertyConfigurator.configure(".\\src\\main\\resources\\log4j2.properties");
			
			//The below line is used in case of log4j2
			logger=LogManager.getLogger(BaseClass.class);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	public static void initialization() throws MalformedURLException
	{
		String browserName = prop.getProperty("Browser");

		if(browserName.equalsIgnoreCase("Chrome")){
			WebDriverManager.chromedriver().setup();
			
			//To disable the chrome notification saying "Chrome is being controlled by Automated test software"
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.setExperimentalOption("useAutomationExtension", false);
			chromeOptions.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
			driver = new ChromeDriver(chromeOptions);
			}
		else if(browserName.equalsIgnoreCase("FireFox")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(); 
		}
		else if (browserName.equalsIgnoreCase("Grid")) {
			nodeURL = "http://10.50.88.59:4444/wd/hub";
			DesiredCapabilities capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(Platform.WINDOWS);
			driver = new RemoteWebDriver(new URL(nodeURL), capability);
		}
		else if (browserName.equalsIgnoreCase("Headless")) {
			WebDriverManager.phantomjs().setup();
			driver = new PhantomJSDriver();
		}

		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebElementListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}

	@AfterMethod
	public void getResult(ITestResult result) throws Exception
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			//MarkupHelper is used to display the output in different colors
			logger1.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			logger1.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));

			//To capture screenshot path and store the path of the screenshot in the string "screenshotPath"
			//We do pass the path captured by this mehtod in to the extent reports using "logger.addScreenCapture" method. 

			//	String Scrnshot=TakeScreenshot.captuerScreenshot(driver,"TestCaseFailed");
			String screenshotPath = TakeScreenshot.captureScreenshot(driver, result.getName());
			//To add it in the extent report 

			logger1.fail("Test Case Failed Snapshot is below " + logger1.addScreenCaptureFromPath(screenshotPath));


		}
		else if(result.getStatus() == ITestResult.SKIP){
			//logger.log(Status.SKIP, "Test Case Skipped is "+result.getName());
			logger1.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE)); 
		} 
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			logger1.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		}

	}
	@AfterTest

	public void Exit() {

		driver.quit();
	}


	@AfterSuite
	public void endReport()
	{
		extent.flush();
	}
	
	
}








