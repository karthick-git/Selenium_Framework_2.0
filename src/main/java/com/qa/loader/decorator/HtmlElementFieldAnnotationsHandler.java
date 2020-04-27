package com.qa.loader.decorator;

import static com.qa.utils.HtmlElementUtils.getGenericParameterClass;
import static com.qa.utils.HtmlElementUtils.isHtmlElement;
import static com.qa.utils.HtmlElementUtils.isHtmlElementList;
import static com.qa.utils.HtmlElementUtils.isTypifiedElement;
import static com.qa.utils.HtmlElementUtils.isTypifiedElementList;

import java.lang.reflect.Field;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.Annotations;

import com.qa.exceptions.HtmlElementsException;

public class HtmlElementFieldAnnotationsHandler extends Annotations {
    public HtmlElementFieldAnnotationsHandler(Field field) {
        super(field);
    }

    @Override
    public By buildBy() {
        if (isHtmlElement(getField()) || isTypifiedElement(getField())) {
            return buildByFromHtmlElementAnnotations();
        }
        if (isHtmlElementList(getField()) || isTypifiedElementList(getField())) {
            return buildByFromHtmlElementListAnnotations();
        }
        return super.buildBy();
    }

    private By buildByFromFindAnnotations() {
        if (getField().isAnnotationPresent(FindBys.class)) {
            FindBys findBys = getField().getAnnotation(FindBys.class);
            return buildByFromFindBys(findBys);
        }

        if (getField().isAnnotationPresent(FindAll.class)) {
            FindAll findBys = getField().getAnnotation(FindAll.class);
            return buildBysFromFindByOneOf(findBys);
        }

        if (getField().isAnnotationPresent(FindBy.class)) {
            FindBy findBy = getField().getAnnotation(FindBy.class);
            return buildByFromFindBy(findBy);
        }
        return null;
    }

    private By buildByFromHtmlElementAnnotations() {
        assertValidAnnotations();

        By result = buildByFromFindAnnotations();
        if (result != null) {
            return result;
        }

        Class<?> fieldClass = getField().getType();
        while (fieldClass != Object.class) {
            if (fieldClass.isAnnotationPresent(FindBy.class)) {
                return buildByFromFindBy(fieldClass.getAnnotation(FindBy.class));
            }
            fieldClass = fieldClass.getSuperclass();
        }

        return buildByFromDefault();
    }

    private By buildByFromHtmlElementListAnnotations() {
        assertValidAnnotations();

        By result = buildByFromFindAnnotations();
        if (result != null) {
            return result;
        }

        Class<?> listParameterClass = getGenericParameterClass(getField());
        while (listParameterClass != Object.class) {
            if (listParameterClass.isAnnotationPresent(FindBy.class)) {
                return buildByFromFindBy(listParameterClass.getAnnotation(FindBy.class));
            }
            listParameterClass = listParameterClass.getSuperclass();
        }

        throw new HtmlElementsException(String.format("Cannot determine how to locate element %s", getField()));
    }
}
