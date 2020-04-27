package com.qa.loader.decorator;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

import com.qa.element.HtmlElement;
import com.qa.element.TypifiedElement;
import com.qa.loader.decorator.proxyhandlers.HtmlElementListNamedProxyHandler;
import com.qa.loader.decorator.proxyhandlers.TypifiedElementListNamedProxyHandler;
import com.qa.loader.decorator.proxyhandlers.WebElementListNamedProxyHandler;
import com.qa.loader.decorator.proxyhandlers.WebElementNamedProxyHandler;
import com.qa.pagefactory.CustomElementLocatorFactory;
import static com.qa.utils.HtmlElementUtils.*;
import static com.qa.loader.decorator.ProxyFactory.*;
import static com.qa.loader.HtmlElementLoader.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.util.List;

public class HtmlElementDecorator implements FieldDecorator {
    protected ElementLocatorFactory factory;
    
    public HtmlElementDecorator(CustomElementLocatorFactory factory) {
        this.factory = factory;
    }

    public Object decorate(ClassLoader loader, Field field) {
        try {
            if (isTypifiedElement(field)) {
                return decorateTypifiedElement(loader, field);
            }
            if (isHtmlElement(field)) {
                return decorateHtmlElement(loader, field);
            }
            if (isWebElement(field) && !field.getName().equals("wrappedElement")) {
                return decorateWebElement(loader, field);
            }
            if (isTypifiedElementList(field)) {
                return decorateTypifiedElementList(loader, field);
            }
            if (isHtmlElementList(field)) {
                return decorateHtmlElementList(loader, field);
            }
            if (isWebElementList(field)) {
                return decorateWebElementList(loader, field);
            }
            return null;
        } catch (ClassCastException ignore) {
            return null; // See bug #94 and NonElementFieldsTest
        }
    }

    protected <T extends TypifiedElement> T decorateTypifiedElement(ClassLoader loader, Field field) {
        WebElement elementToWrap = decorateWebElement(loader, field);

        //noinspection unchecked
        return createTypifiedElement((Class<T>) field.getType(), elementToWrap, getElementName(field));
    }

    protected <T extends HtmlElement> T decorateHtmlElement(ClassLoader loader, Field field) {
        WebElement elementToWrap = decorateWebElement(loader, field);

        //noinspection unchecked
        return createHtmlElement((Class<T>) field.getType(), elementToWrap, getElementName(field));
    }

    protected WebElement decorateWebElement(ClassLoader loader, Field field) {
        ElementLocator locator = factory.createLocator(field);
        InvocationHandler handler = new WebElementNamedProxyHandler(locator, getElementName(field));

        return createWebElementProxy(loader, handler);
    }

    protected <T extends TypifiedElement> List<T> decorateTypifiedElementList(ClassLoader loader, Field field) {
        @SuppressWarnings("unchecked")
        Class<T> elementClass = (Class<T>) getGenericParameterClass(field);
        ElementLocator locator = factory.createLocator(field);
        String name = getElementName(field);

        InvocationHandler handler = new TypifiedElementListNamedProxyHandler<>(elementClass, locator, name);

        return createTypifiedElementListProxy(loader, handler);
    }

    protected <T extends HtmlElement> List<T> decorateHtmlElementList(ClassLoader loader, Field field) {
        @SuppressWarnings("unchecked")
        Class<T> elementClass = (Class<T>) getGenericParameterClass(field);
        ElementLocator locator = factory.createLocator(field);
        String name = getElementName(field);

        InvocationHandler handler = new HtmlElementListNamedProxyHandler<>(elementClass, locator, name);

        return createHtmlElementListProxy(loader, handler);
    }

    protected List<WebElement> decorateWebElementList(ClassLoader loader, Field field) {
        ElementLocator locator = factory.createLocator(field);
        InvocationHandler handler = new WebElementListNamedProxyHandler(locator, getElementName(field));

        return createWebElementListProxy(loader, handler);
    }
}
