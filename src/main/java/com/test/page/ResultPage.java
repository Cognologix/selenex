package com.test.page;

import com.test.annotation.PageFragment;
import com.test.controller.WebBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page representing the dashboard and tiles.
 */
@PageFragment
public class ResultPage extends WebBase {

    @FindBy(xpath = "(//span[@class=\"home-product-link\"])[1]")
    private WebElement identityIcon;

}
