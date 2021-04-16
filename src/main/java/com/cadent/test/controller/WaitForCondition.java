package com.cadent.test.controller;

import com.cadent.test.annotation.PageFragment;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

/**
 *
* */

@PageFragment
public class WaitForCondition extends WebBase{

    @Value("${default.timeout:30}")
    private Long defaultTimeout;


    protected  String timeoutErrorMessage(WebElement element ) {
        return "Unable to find visibility of element located by " +element + " within " + defaultTimeout + " seconds \n "
                + "\nThere several reasons why this may have occurred including: \n" +
                "\n - The page did not finish loading some ui features before the expected timeout (usually related to Ajaxian elements)" +
                "\n - The ui element is legitimately missing from the page due to a bug or feature change " +
                "\n - There was a problem with communication within the Selenium stack" +
                "\nSee image below (if available) for clues.\n If all else fails, RERUN THE TEST MANUALLY! - CPK\n\n";
    }

    /**
     * Condition to use wait for list of web elements
     * */
    public WaitForCondition waitForListElementDisplayed(List<WebElement> elements){

            driverWait.until(d ->
                    elements
                            .stream()
                            .findFirst()
                            .filter(element -> element.isEnabled() && element.isDisplayed())
            );

        return this;
    };

    /**
     * Condition to use wait for of web element
     * */
    public void waitForElement(WebElement element){

        driverWait.until(ExpectedConditions.visibilityOf(element));

    }


}
