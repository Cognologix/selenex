package com.test.easy.page.inputforms;

import com.test.annotation.Page;
import com.test.controller.WebBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

/**
 * Page object for https://www.seleniumeasy.com/test/basic-first-form-demo.html
 */
@Page
public class SimpleFormDemoPage extends WebBase {
    @FindBy(id = "user-message")
    private WebElement messageBox;

    @FindBy(xpath = "//button[contains(text(),'Show Message')]")
    private WebElement showMessage;

    @FindBy(id = "display")
    private WebElement display;

    @FindBy(id = "sum%d")
    private WebElement sumInput;

    @FindBy(xpath = "//button[contains(text(),'Get Total')]")
    private WebElement getTotal;

    @FindBy(id = "displayvalue")
    private WebElement total;

    public void enterMessage(String message) {
        messageBox.clear();
        messageBox.sendKeys(message);
    }

    public String getMessage() {
        showMessage.click();
        return display.getText();
    }

    public void enterAandB(String a, String b) {
        updateLocator("sumInput", 1);
        sumInput.clear();
        sumInput.sendKeys(a);

        updateLocator("sumInput", 2);
        sumInput.clear();
        sumInput.sendKeys(b);
    }

    public String getTotal() {
        getTotal.click();
        return total.getText();
    }

}
