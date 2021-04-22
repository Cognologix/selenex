package com.test.page.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * Provide ability to update @FindBy annotation's locator value at runtime.
 */
public  class DynamicPageFactory {

    private static final String DECLARED_ANNOTATIONS = "declaredAnnotations";

    private static Method method;

    static {
        try {
            method = Field.class.getDeclaredMethod(DECLARED_ANNOTATIONS);
            method.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    private final DefaultFieldDecorator decorator;
    private final HashMap<String, String> fieldToFormatMap;

    public DynamicPageFactory(WebDriver driver) {
        fieldToFormatMap = new HashMap<>();
        this.decorator = new DefaultFieldDecorator(new DefaultElementLocatorFactory(driver));
    }


    public void updateLocator(Object page, String fieldName, Object... args) {
        try {

            Field field = page.getClass().getDeclaredField(fieldName);
            FindBy findBy = field.getAnnotation(FindBy.class);
            CustomFindBy customFindBy = new CustomFindBy(findBy);

            Field[] bys = CustomFindBy.class.getDeclaredFields();
            for (Field by : bys) {
                if (!by.get(customFindBy).equals("") && !by.getName().equals("how")) {
                    String value = (String) by.get(customFindBy);
                    if (fieldToFormatMap.containsKey(fieldName)) {
                        value = fieldToFormatMap.get(fieldName);
                    } else {
                        fieldToFormatMap.put(fieldName, value);
                    }
                    by.set(customFindBy, String.format(value, args));
                    break;
                }
            }

            Map<Class<? extends Annotation>, Annotation> declaredAnnotations =
                    (Map<Class<? extends Annotation>, Annotation>) method.invoke(field);
            declaredAnnotations.put(FindBy.class, customFindBy);

            Object value = decorator.decorate(page.getClass().getClassLoader(), field);
            if (value != null) {
                field.setAccessible(true);
                field.set(page, value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

