package com.test.controller;

import org.openqa.selenium.WebElement;

/**
 *  This class is restricted to selenux project. It is responsible to make all the basic operations on selenance commands.
 *  Get - to launch the browser
 *  Close - to close the browser
 *  Quite  - to close the browser
 *  maximizeWindow - to Maximize the window
 *  isDisplayed - to check weather the page gets loaded.
 * */
public interface IDriverMethodCtrl {

    void get(String url);

    void close();

    void quit();

    void maximizeWindow();

    Object runScript(String script, Object...args);

    boolean isAt(WebElement element);

    boolean isDisplayed(WebElement element);

    void switchTab();

    void closeTab();
}
