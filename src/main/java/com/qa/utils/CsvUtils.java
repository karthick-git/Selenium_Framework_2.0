package com.qa.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import com.qa.BaseClass.BaseClass;
import com.qa.TestPackage.AmazonLoginPage;

public class CsvUtils extends BaseClass{
	public int row;
	public int col;
	public static String username;
	public static String password;
	static AmazonLoginPage amz;

	public CsvUtils () throws IOException {
		super();
		
	}
	public static void csv(String sTestCaseName) throws Exception{

			  File file = new File(prop.getProperty("csvFile"));
		      FileInputStream fis = new FileInputStream(file);
		      byte[] byteArray = new byte[(int)file.length()];
		      fis.read(byteArray);
		      String data = new String(byteArray);
		      String[] stringArray = data.split("\r\n");
		      int rowcnt = stringArray.length;
		      System.out.println("Number of lines in the file are :"+rowcnt);
		      
		      String sCurrentLine;
		      BufferedReader br = new BufferedReader(new FileReader(file));
		      for(int row=1;row<=rowcnt;row++){
		    	  sCurrentLine = br.readLine();
			      String[] val = sCurrentLine.split(",");
			      if(sTestCaseName.equalsIgnoreCase(val[0])){
			    	  System.out.println(sTestCaseName +" is present in row : "+row);
			    	  username = val[1];
			    	  password = val[2];
			    	  System.out.println("Username : "+ username);
			    	  System.out.println("Password : "+ password);
			    	  System.out.println(sTestCaseName);
			    	  amz = new AmazonLoginPage();
			    	  amz.doAmazonLogin(username, password);
			      }
		      }	  
		      br.close();
		      fis.close();
	}
}
