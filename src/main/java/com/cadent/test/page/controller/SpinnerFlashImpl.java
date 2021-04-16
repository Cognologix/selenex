package com.cadent.test.page.controller;

import com.cadent.test.controller.WebBase;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SpinnerFlashImpl extends WebBase{

    private static final Logger LOGGER = LoggerFactory.getLogger (SpinnerFlashImpl.class);


    public void waitTillCompletion (WebElement element) {

        StackTraceElement st = Thread.currentThread().getStackTrace()[2];
        LOGGER.info(String.format("spinner.waitTillCompletion \n\t\t\tcaller .(%s:%s)",
                st.getFileName(), st.getLineNumber()));
        WebDriverWait wait = new WebDriverWait(driver, 60);
        try {
            if (isDisplayed(element)) {
                wait.until(condition -> !isDisplayed(element));
            }
        } catch (TimeoutException ignored) {
        }
        LOGGER.info("spinner completed.");

    }


}
