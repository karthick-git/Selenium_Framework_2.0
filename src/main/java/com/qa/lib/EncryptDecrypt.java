package com.qa.lib;

import org.jasypt.util.text.BasicTextEncryptor;

//Uses Jasypt API (Java Simplified Encryption)
public class EncryptDecrypt {
	 private final String secretKey = "";
	 BasicTextEncryptor basicTextEncryptor;
	 public EncryptDecrypt() {
			 basicTextEncryptor = new BasicTextEncryptor(); 
	 basicTextEncryptor.setPassword(secretKey); 
	 }
	 public String encryptPassword(String password) {
	    return basicTextEncryptor.encrypt(password);
	 }
	    public String decryptPassword(String encryptedPassword) {
	      return basicTextEncryptor.decrypt(encryptedPassword);
	    }}
	    
	    //Implementation in Main Class
//	      public class TestEncryptDecrypt {
//	       public static void main(String[] args) {
//	    	   EncryptDecrypt encryptDecrypt = new EncryptDecrypt();
//	    	   String password = "TestingPasswordEncryptionAndDecryption"; 
//	    	   String encryptedPassword = encryptDecrypt.encryptPassword(password); 
//	    	   System.out.println(encryptedPassword); 
//	    	   System.out.println(encryptDecrypt.decryptPassword(encryptedPassword));
//		}
//	      }
	       