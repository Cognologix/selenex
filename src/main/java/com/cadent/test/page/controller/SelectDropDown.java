package com.cadent.test.page.controller;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SelectDropDown implements IDropDown{

    private  Select select;
    @Override
    public void selectDropDownValue(List<WebElement> elements,String dropDownValue) {

        elements
                .stream()
                .filter(e -> e.getAttribute("value").matches(dropDownValue))
                .forEach(e -> e.click());
    }

    public void selectDropDown(WebElement element,String dropDownValue){

         select = new Select(element);
         select
                .getOptions()
                .stream()
                .anyMatch(item -> item.getText().equals(dropDownValue));

    }
}
