package com.cadent.test.page.controller;

import com.cadent.test.annotation.PageFragment;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

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
