package com.qa.loader;

import static com.qa.loader.decorator.ProxyFactory.*;
import static com.qa.utils.HtmlElementUtils.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import com.qa.element.HtmlElement;
import com.qa.element.TypifiedElement;
import com.qa.loader.decorator.HtmlElementDecorator;
import com.qa.loader.decorator.HtmlElementLocatorFactory;
import com.qa.loader.decorator.proxyhandlers.WebElementNamedProxyHandler;
import com.qa.pagefactory.CustomElementLocatorFactory;
import com.qa.exceptions.HtmlElementsException;

public class HtmlElementLoader {
   
    @SuppressWarnings("unchecked")
    public static <T> T create(Class<T> clazz, WebDriver driver) {
        if (isHtmlElement(clazz)) {
            return (T) createHtmlElement((Class<HtmlElement>) clazz, driver);
        }
        if (isTypifiedElement(clazz)) {
            return (T) createTypifiedElement((Class<TypifiedElement>) clazz, driver);
        }
        return createPageObject(clazz, driver);
    }

   
    public static <T> void populate(T instance, WebDriver driver) {
        if (isHtmlElement(instance)) {
            populateHtmlElement((HtmlElement) instance, driver);
        } else {
            // Otherwise consider instance as a page object
            populatePageObject(instance, driver);
        }
    }

   
    public static <T extends HtmlElement> T createHtmlElement(Class<T> clazz, SearchContext searchContext) {
        ElementLocator locator = new HtmlElementLocatorFactory(searchContext).createLocator(clazz);
        String elementName = getElementName(clazz);

        InvocationHandler handler = new WebElementNamedProxyHandler(locator, elementName);
        WebElement elementToWrap = createWebElementProxy(clazz.getClassLoader(), handler);
        return createHtmlElement(clazz, elementToWrap, elementName);
    }

    public static <T extends HtmlElement> T createHtmlElement(Class<T> elementClass, WebElement elementToWrap,
                                                              String name) {
        try {
            T instance = newInstance(elementClass);
            instance.setWrappedElement(elementToWrap);
            instance.setName(name);
            // Recursively initialize elements of the block
            populatePageObject(instance, elementToWrap);
            return instance;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            throw new HtmlElementsException(e);
        }
    }

    public static <T extends TypifiedElement> T createTypifiedElement(Class<T> clazz, SearchContext searchContext) {
        ElementLocator locator = new HtmlElementLocatorFactory(searchContext).createLocator(clazz);
        String elementName = getElementName(clazz);

        InvocationHandler handler = new WebElementNamedProxyHandler(locator, elementName);
        WebElement elementToWrap = createWebElementProxy(clazz.getClassLoader(), handler);

        return createTypifiedElement(clazz, elementToWrap, elementName);
    }

    public static <T extends TypifiedElement> T createTypifiedElement(Class<T> elementClass, WebElement elementToWrap,
                                                                      String name) {
        try {
            T instance = newInstance(elementClass, elementToWrap);
            instance.setName(name);
            return instance;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            throw new HtmlElementsException(e);
        }
    }

    
    public static <T> T createPageObject(Class<T> clazz, WebDriver driver) {
        try {
            T instance = newInstance(clazz, driver);
            populatePageObject(instance, driver);
            return instance;
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException
                | InvocationTargetException e) {
            throw new HtmlElementsException(e);
        }
    }

   
    public static void populateHtmlElement(HtmlElement htmlElement, SearchContext searchContext) {
        populateHtmlElement(htmlElement, new HtmlElementLocatorFactory(searchContext));
    }

    
    public static void populateHtmlElement(HtmlElement htmlElement, CustomElementLocatorFactory locatorFactory) {
        @SuppressWarnings("unchecked")
        Class<HtmlElement> elementClass = (Class<HtmlElement>) htmlElement.getClass();
        // Create locator that will handle Block annotation
        ElementLocator locator = locatorFactory.createLocator(elementClass);
        ClassLoader elementClassLoader = htmlElement.getClass().getClassLoader();
        // Initialize block with WebElement proxy and set its name
        String elementName = getElementName(elementClass);
        InvocationHandler handler = new WebElementNamedProxyHandler(locator, elementName);
        WebElement elementToWrap = createWebElementProxy(elementClassLoader, handler);
        htmlElement.setWrappedElement(elementToWrap);
        htmlElement.setName(elementName);
        // Initialize elements of the block
        populatePageObject(htmlElement, elementToWrap);
    }   
    
    public static void populatePageObject(Object page, SearchContext searchContext) {
        populatePageObject(page, new HtmlElementLocatorFactory(searchContext));
    }
    
    public static void populatePageObject(Object page, CustomElementLocatorFactory locatorFactory) {
        PageFactory.initElements(new HtmlElementDecorator(locatorFactory), page);
    }
}
