package com.test.page.pagefactory;

import com.test.annotation.PageFragment;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.lang.annotation.Annotation;

/**
 * Stores @FindBy annotation values to be used for updating locators at runtime.
 */
@PageFragment
public class CustomFindBy implements FindBy {

     How how;
     String using;
     String id;
     String name;
     String className;
     String css;
     String tagName;
     String linkText;
     String partialLinkText;
     String xpath;

    CustomFindBy(FindBy findBy) {
        how = findBy.how();
        using = findBy.using();
        id = findBy.id();
        name = findBy.name();
        className = findBy.className();
        css = findBy.css();
        tagName = findBy.tagName();
        linkText = findBy.linkText();
        partialLinkText = findBy.partialLinkText();
        xpath = findBy.xpath();
    }


    public void setUp(FindBy findBy){

        how = findBy.how();
        using = findBy.using();
        id = findBy.id();
        name = findBy.name();
        className = findBy.className();
        css = findBy.css();
        tagName = findBy.tagName();
        linkText = findBy.linkText();
        partialLinkText = findBy.partialLinkText();
        xpath = findBy.xpath();
    }

    @Override
    public How how() {
        return how;
    }

    @Override
    public String using() {
        return using;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String className() {
        return className;
    }

    @Override
    public String css() {
        return css;
    }

    @Override
    public String tagName() {
        return tagName;
    }

    @Override
    public String linkText() {
        return linkText;
    }

    @Override
    public String partialLinkText() {
        return partialLinkText;
    }

    @Override
    public String xpath() {
        return xpath;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return FindBy.class;
    }
}
