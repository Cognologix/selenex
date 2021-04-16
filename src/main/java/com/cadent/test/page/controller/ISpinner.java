package com.cadent.test.page.controller;

import com.cadent.test.annotation.Page;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public interface ISpinner {

    void waitTillCompletion (WebElement element);
}
