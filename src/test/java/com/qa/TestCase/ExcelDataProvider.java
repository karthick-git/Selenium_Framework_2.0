package com.qa.TestCase;

import org.apache.logging.log4j.LogManager;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.BaseClass.BaseClass;
import com.qa.utils.ExcelUtils;

public class ExcelDataProvider extends BaseClass {
	ExcelUtils excelUtils;
	public int iTestCaseRow;
	private String sTestCaseName;
	
	@BeforeTest
	public void setUpTest() throws Exception {
		initialization();
		logger = LogManager.getLogger(ExcelDataProvider.class);
		logger.info(this.getClass().getSimpleName());
		excelUtils = new ExcelUtils();
		sTestCaseName = ExcelUtils.getTestCaseName(this.toString());
		iTestCaseRow = ExcelUtils.getRowContains(sTestCaseName);
		System.out.println("Row containing test Scenario in the sheet is " + iTestCaseRow);
	}
	
	@Test(dataProvider="test1data")
	public void test1(String username, String password) throws Exception {
		logger1 = extent.createTest("Validation of OrangeHRM Login");
		System.out.println(username+" | "+password);
		driver.findElement(By.id("txtUsername")).sendKeys(username);
		driver.findElement(By.id("txtPassword")).sendKeys(password);
		Thread.sleep(2000);
		logger1.info("Login Successful - OrangeHRM");
	}

	@DataProvider(name = "test1data")
	public Object[][] getData() {
		Object data[][] = testData(ExcelUtils.workbook, ExcelUtils.sheet);
		return data;
	}
	
	
	public Object[][] testData(XSSFWorkbook workbook, XSSFSheet sheet) {
		
		int rowCount = ExcelUtils.getRowCount();
		System.out.println(rowCount);
		int colCount = ExcelUtils.getColCount();
		System.out.println(colCount);
		
		Object data[][] = new Object[rowCount-1][colCount];
		
		for(int i=1; i<rowCount; i++) {
			for(int j=0; j<colCount; j++) {
				
				String cellData = ExcelUtils.getCellDataString(i, j);
				System.out.print(cellData+" | ");
				data[i-1][j] = cellData;
				
			}
			System.out.println();
		}
		return data;
		
	}

}
