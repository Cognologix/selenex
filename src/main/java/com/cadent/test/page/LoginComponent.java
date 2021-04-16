package com.cadent.test.page;

import com.cadent.test.annotation.Page;
import com.cadent.test.controller.WebBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 * Provides base component that logs in to the system using credentials provided in spring profiles.
 */
@Page
public class LoginComponent extends WebBase {

    @Value("${default.timout:30}")
    private int timeout;

    @Value("${username}")
    private String uName;

    @Value("${password}")
    private String pwd;

    @FindBy(name = "userName")
    private WebElement userName;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(id = "login")
    private List<WebElement> signUp;



    public void loginDetails(){

        this.isAt(this.userName);
        this.userName.sendKeys(uName);
        this.password.sendKeys(pwd);
        this.signUp
                .stream()
                .filter(e -> e.isEnabled() && e.isDisplayed())
                .findFirst()
                .ifPresent(WebElement::submit);

    }



}

