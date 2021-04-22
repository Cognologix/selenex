package com.test.page.controller;

import com.test.annotation.PageFragment;
import org.openqa.selenium.WebElement;

/**
 * Provides CheckBox functionality for WebElement.
 */
@PageFragment
public class CheckBox {



    private  WebElement element;

    public CheckBox(WebElement element){

        this.element = element;
    }


    public boolean isChecked(){

        return element.isSelected();
    }

    public void check() {

        if ( ! isChecked() ) {
            element.click();
        }
    }


    public void uncheck() {

        if ( isChecked() ) {
            element.click();
        }
    }

}
