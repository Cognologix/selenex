package com.test.controller;

import com.test.annotation.Page;
import com.test.exception.AutomationRunTimeError;
import com.test.page.pagefactory.DynamicPageFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * WebBase is responsible to instantiate all the configuration classes. WebDriver and RemoteWebdrive.
 *  This class needs to be an extended in all POM model.
 *
 *
 * */
@Page
public class WebBase implements IDriverMethodCtrl {

    @Autowired
    public WebDriver driver;

    @Autowired
    public WebDriverWait driverWait;

    private DynamicPageFactory dynamicPageFactory;

    @Value("${default.timeout:30}")
    private long timeout;

    @PostConstruct
    private void initialize() {
        dynamicPageFactory = new DynamicPageFactory(driver);
        PageFactory.initElements(this.driver, this);
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    @Override
    public void get(String url) {
        try {
            this.driver.get(url);
        } catch (WebDriverException error) {

            throw new AutomationRunTimeError("Timeout to load the URL " + error.getMessage());
        }
    }

    @Override
    public void close() {
        this.driver.close();
    }

    @Override
    public void quit() {
        this.driver.quit();
    }

    @Override
    public void maximizeWindow() {
        this.driver.manage().window().maximize();
    }

    @Override
    public Object runScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    @Override
    public boolean isAt(WebElement element) {
        return driverWait.until((d) -> element.isDisplayed() && element.isEnabled());
    }

    @Override
    public boolean isDisplayed(WebElement element) {
        try {

            return isAt(element);
        } catch (WebDriverException e) {
            return false;
        }
    }

    @Override
    public void switchTab() {
        Set<String> all = driver.getWindowHandles();
        String last = "";
        for (String h : all) {
            last = h;
        }
        driver.switchTo().window(last);
    }

    @Override
    public void closeTab() {
        driver.close();
    }

    public void waitForElement(WebElement element) {
        try {
            driverWait.until((d) -> element.isEnabled() && element.isDisplayed());
        } catch (Exception e) {
            throw new AutomationRunTimeError("Element not found Exception" + e.getMessage());
        }
    }


    public void updateLocator(String fieldName, Object... args) {
        dynamicPageFactory.updateLocator(this, fieldName, args);
    }
}
