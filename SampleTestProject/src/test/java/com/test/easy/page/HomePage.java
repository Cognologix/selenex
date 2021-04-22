package com.test.easy.page;

import com.test.annotation.Page;
import com.test.controller.WebBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Page
public class HomePage extends WebBase {
    @Value("${application.url}")
    private String url;

    @FindBy(partialLinkText = "No, thanks!")
    private WebElement noThanks;

    @FindBy(xpath = "(//a[contains(text(),'Input Forms')])[1]")
    private WebElement inputForms;

    @FindBy(xpath = "(//a[contains(text(),'Input Forms')])[1]/..//li")
    private List<WebElement> forms;

    public void open() {
        driver.manage().window().maximize();
        driver.get(url);
    }

    public void dismissAd() {
        if (isDisplayed(noThanks)) {
            noThanks.click();
        }
    }

    public void selectForm(FormType type) {
        inputForms.click();

//        for (int i = 0; i < 10; i++) {
//            if (isDisplayed(inputForms) && inputForms.getAttribute("aria-expanded").equals("true")) {
//                break;
//            }
//        }

        String formText = type.getText();
        for (WebElement form : forms) {
            if (form.getText().equals(formText)) {
                form.click();
                break;
            }
        }
    }

    public enum FormType {
        SIMPLE_FORM("Simple Form Demo"),
        CHECKBOX("Checkbox Demo");

        private final String text;

        FormType(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }
    }
}
