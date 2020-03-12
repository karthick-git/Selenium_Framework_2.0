package com.qa.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static Properties prop;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	public ExcelUtils() {
		try {
			workbook = new XSSFWorkbook(prop.getProperty("excelFilePath"));
			sheet = workbook.getSheet(prop.getProperty("sheetName"));
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet, File Not found exception");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet, IO Exception");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static int getRowCount() {
		int rowCount = 0;
		try {
			
			rowCount = sheet.getPhysicalNumberOfRows();
			System.out.println("No of rows : " + rowCount);
			
		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return rowCount;

	}

	public static int getColCount() {
		int colCount = 0;
		try {

			colCount = sheet.getRow(0).getPhysicalNumberOfCells();
			System.out.println("No of columns : " + colCount);

		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return colCount;

	}

	public static String getCellDataString(int rowNum, int colNum) {
		String cellData = null;
		try {

			cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
			// System.out.println(cellData);

		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		return cellData;
	}

	public static void getCellDataNumber(int rowNum, int colNum) {
		try {

			double cellData = sheet.getRow(rowNum).getCell(colNum).getNumericCellValue();
			System.out.println(cellData);

		} catch (Exception exp) {
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
	}
	
	public static String getTestCaseName(String sTestCase)throws Exception
	{
		String value = sTestCase;
		try
		{
			System.out.println(sTestCase);
			int posi = value.indexOf("@");
			value = value.substring(0, posi);
			posi = value.lastIndexOf(".");	
			value = value.substring(posi + 1);
			return value;			
		}catch (Exception e)
		{
			throw (e);
		}
	}

	public static int getRowContains(String sTestCaseName, String InstanceName) throws Exception
	{
		int i;
		try 
		{
			int rowCount = ExcelUtils.getRowUsed();
			for ( i=0 ; i<=rowCount; i++)
			{
				if(ExcelUtils.getCellDataString(i,0).equalsIgnoreCase(sTestCaseName))
				{				
					if(ExcelUtils.getCellDataString(i,1).equalsIgnoreCase(InstanceName))
					break;
				}
			}
			return i;
				
		}catch (Exception e)
		{
			throw(e);		
		}
	}

	public static int getRowUsed() throws Exception 
	{
		try
		{
			int RowCount = sheet.getLastRowNum();
			return RowCount;
		}catch (Exception e)
		{
			System.out.println(e.getMessage());
			throw (e);
		}

	}
}
