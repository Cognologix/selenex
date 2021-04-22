package com.test.service;

import com.test.annotation.LazyConfiguration;
import com.test.exception.AutomationRunTimeError;
import org.openqa.selenium.By;

@LazyConfiguration
public class Locator implements ILocator{

    private LocatorType locatorType  = null;
    private String      locatorValue = null;
    private By			locatorPath = null;


    public void Locator(LocatorType locatorType, String locatorValue){

        this.locatorType = locatorType;
        this.locatorValue = locatorValue;
        setLocator();
    }

    public void setLocatorValue(String locatorValue) {
        this.locatorValue = locatorValue;
    }

    // convert this in lambda expression
    @Override
    public void setLocator() {

        switch (locatorType) {

            case id:
                locatorPath = By.id(locatorValue);
                break;

            case name:
                locatorPath = By.name(locatorValue);
                break;

            case link:
                locatorPath = By.linkText(locatorValue);
                break;

            case xpath:
                locatorPath = By.xpath(locatorValue);
                break;

            case css:
                locatorPath = By.cssSelector(locatorValue);
                break;

            case className:
                locatorPath = By.className(locatorValue);
                break;

            case tagName:
                locatorPath = By.tagName(locatorValue);
                break;

            case partialLink:
                locatorPath = By.partialLinkText(locatorValue);
                break;

            default:
                throw new AutomationRunTimeError("Unsupported LocatorType: " +
                        locatorType.toString());
        }
    }
}
