package com.test.page.controller;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface IDropDown {

    void selectDropDownValue(List<WebElement> elements,String dropDownValue);
}
