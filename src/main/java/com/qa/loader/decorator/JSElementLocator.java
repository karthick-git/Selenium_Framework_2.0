package com.qa.loader.decorator;

import java.lang.reflect.Field;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import com.qa.annotations.FindByJQuery;
import com.qa.annotations.FindByJS;

public class JSElementLocator implements ElementLocator {
	  private final JavascriptExecutor jsExecutor;
	  private final FindByJS annotation;
	  private String jsLocator;
	  
	  public JSElementLocator(final WebDriver driver, final Field field) {
	  
		this.jsExecutor = (JavascriptExecutor)driver;
	    this.annotation = field.getAnnotation(FindByJS.class);
	    this.jsLocator = annotation.value();
	  }
	  
	  @Override
	  public WebElement findElement() {
		  String jsFunction = String.format("function getElement() {var element = %s; return element; }; "
					 + "return getElement()", jsLocator);
		  WebElement element = (WebElement) jsExecutor.executeScript(jsFunction);
		  return element;
	  }
	  
	  @SuppressWarnings("unchecked")
	  public List<WebElement> findElements() {
		  return (List<WebElement>) jsExecutor.executeScript(jsLocator);
	  }
	  
	}
