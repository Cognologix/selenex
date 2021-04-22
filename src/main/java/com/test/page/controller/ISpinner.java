package com.test.page.controller;

import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public interface ISpinner {

    void waitTillCompletion (WebElement element);
}
