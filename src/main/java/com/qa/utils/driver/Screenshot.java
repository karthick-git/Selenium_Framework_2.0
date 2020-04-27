package com.qa.utils.driver;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import com.qa.utils.property.PropertyFile;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	
	private WebDriver driver;	
	private final String folderPath;
	public static Logger logger=LogManager.getLogger(Screenshot.class);
	
	private final String folderNotExistError = "screenshot folder does not exist";
	private final String cannotCleanFolderError = "cannot clean screenshot folder";
	private final String cannotCaptureScreenshotError = "cannot capture the screenshot";
	
	
	public Screenshot(WebDriver driver) throws Exception {		
		this.driver = driver;
		folderPath = new PropertyFile().get("screenshotsFolderPath");
		validateFolderExists();				
	}

	private void validateFolderExists() {
		File screenShotFolder = new File(folderPath);
		if (!screenShotFolder.exists()) {
			logger.fatal(folderNotExistError);
			throw new RuntimeException(folderNotExistError);
		}
	}
	
	public void cleanFolder()
	{		
		try{					
			File screenShotFolder = new File(folderPath);
			for(File file: screenShotFolder.listFiles()) 
				file.delete();
		}
		catch(Exception ex) {
			logger.fatal(cannotCleanFolderError);
			throw new RuntimeException(cannotCleanFolderError, ex);
		}
	}	
	
	public void capture(String methodName) 
	{		
		File scrFile;
		 
		 try {
			 scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			 FileUtils.copyFile(scrFile, new File(getScreenshotName(methodName)));	
			 return;
		 } 
		 catch (IOException e) {
			 e.printStackTrace();
		 }
		 
		 logger.fatal(cannotCaptureScreenshotError);
		 throw new RuntimeException(cannotCaptureScreenshotError);
        
    }
	
	private String getScreenshotName(String methodName) {
		 String localDateTime = LocalDateTime.now().toString().replaceAll("[^0-9a-zA-Z]", "");
		 StringBuilder name = new StringBuilder().append(folderPath) 
				       							 .append(methodName)
				       							 .append("_")
				       							 .append(localDateTime)
				       							 .append(".png");
		 return name.toString();
	 }

}
