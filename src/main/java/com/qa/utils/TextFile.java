package com.qa.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.qa.utils.property.PropertyFile;

public class TextFile {	
	private final String fileName; 
	BufferedWriter writer;
		
	public TextFile() 
	{		
		String folderPath = new PropertyFile().get("logsFolderPath");
		validateFolderExists(folderPath);
		
		fileName = folderPath + "errors.txt";				
		
		try {
			writer = new BufferedWriter(new FileWriter(fileName,true));
		}
		catch (IOException ex) {
			throw new RuntimeException("couldnt open the text file");
		}
	}
	
	private void validateFolderExists(String folderPath) {
		File folder = new File(folderPath);
		if (!folder.exists())
			throw new RuntimeException("log folder does not exist");
	}
	
	public void add(String text) {		
		try {	         
	         writer.write(text);
	         writer.close();
	    }	      
	    catch (IOException e) {
	         throw new RuntimeException("couldnt write text to file");
	    }	        	    
	}	

}
