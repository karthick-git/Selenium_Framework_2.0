package com.qa.lib;

public class CommonFunctions {

	public static String getCurrentMethodName() 
    { 
        // current method name at 0th index 
        String nameofCurrMethod = new Throwable() 
                                      .getStackTrace()[0] 
                                      .getMethodName(); 
  
		return nameofCurrMethod; 
    } 
	
}
