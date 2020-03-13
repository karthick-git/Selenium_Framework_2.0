package com.qa.TestCase;

import static com.google.common.base.Strings.isNullOrEmpty;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;

import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;

import io.github.bonigarcia.wdm.WebDriverManager;

@RunWith(JUnit4.class)
public class AppliToolsDemo {

	/**
	 * Runs Applitools test for the demo app https://demo.applitools.com
	 */
		private EyesRunner runner;
		private Eyes eyes;
		private static BatchInfo batch;
		private WebDriver driver;

		@BeforeClass
		public static void setBatch() {
			// Must be before ALL tests (at Class-level)
			batch = new BatchInfo("Demo batch");
		}

		@Before
		public void beforeEach() {
			// Initialize the Runner for your test.
			runner = new ClassicRunner();

			// Initialize the eyes SDK
			eyes = new Eyes(runner);

			// Raise an error if no API Key has been found.
			if(isNullOrEmpty(System.getenv("APPLITOOLS_API_KEY"))) {
			    throw new RuntimeException("No API Key found; Please set environment variable 'APPLITOOLS_API_KEY'.");
			}

			// Set your personal Applitols API Key from your environment variables.
			eyes.setApiKey(System.getenv("APPLITOOLS_API_KEY"));

			// set batch name
			eyes.setBatch(batch);

			// Use Chrome browser
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(); 
			
		}

		@Test
		public void basicTest() {
			// Set AUT's name, test name and viewport size (width X height)
			// We have set it to 800 x 600 to accommodate various screens. Feel free to
			// change it.
			eyes.open(driver, "Demo App", "Smoke Test");

			// Navigate the browser to the "ACME" demo app.
			driver.get("http://demo.applitools.com/index_v2.html#");
			
			//Set timeout for page to load
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			// To see visual bugs after the first run, use the commented line below instead.
			//driver.get("https://demo.applitools.com/index_v2.html");
					
			// Visual checkpoint #1 - Check the login page.
			eyes.checkWindow("Login Window");

			// This will create a test with two test steps.
			driver.findElement(By.id("log-in")).click();

			// Visual checkpoint #2 - Check the app page.
			eyes.checkWindow("App Window");

			// End the test.
			eyes.closeAsync();
		}

		@After
		public void afterEach() {
			// Close the browser.
			driver.quit();

			// If the test was aborted before eyes.close was called, ends the test as
			// aborted.
			eyes.abortIfNotClosed();

			// Wait and collect all test results
			TestResultsSummary allTestResults = runner.getAllTestResults();

			// Print results
			System.out.println(allTestResults);
		}
	}
