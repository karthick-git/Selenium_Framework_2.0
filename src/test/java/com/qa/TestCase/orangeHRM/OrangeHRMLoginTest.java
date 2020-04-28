package com.qa.TestCase.orangeHRM;

import org.apache.logging.log4j.LogManager;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.qa.BaseClass.BaseClass;
import com.qa.utils.ExcelUtils;

//@Listeners(ReportPortalTestNGListener.class)
public class OrangeHRMLoginTest extends BaseClass {
	ExcelUtils excelUtils;
	public String sTestCaseName;
	
	@BeforeTest
	public void setUpTest() throws Exception {
		initialization();
		logger = LogManager.getLogger(OrangeHRMLoginTest.class);
		logger.info(this.getClass().getSimpleName());
		excelUtils = new ExcelUtils();
		sTestCaseName = ExcelUtils.getTestCaseName(this.toString());
	}
	
	@Test
	public void test1() throws Exception {
		logger1 = extent.createTest("Validation of OrangeHRM Login");
		ExcelUtils.excelLogin(sTestCaseName);
		logger1.info("Login Successful - OrangeHRM");
	}

}
