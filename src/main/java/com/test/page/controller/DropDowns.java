package com.test.page.controller;

import com.test.annotation.PageFragment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Provides uniform way to perform operations on dropdowns.
 * Handles standard html dropdown as well as 4info-dropdown.
 */
@PageFragment
public class DropDowns {
    private static final Logger LOGGER = LoggerFactory.getLogger(DropDowns.class);
    private WebElement select;

    private Select selectWrapper;

    /**
     * Returns DropDowns instance by wrapping provided select WebElement.
     * @param select Either standard select WebElement of Four-info dropdown.
     * @return DropDowns instance by wrapping provided select WebElement.
     */
    public DropDowns get(WebElement select) {
        LOGGER.info ("Dropdown webelement"+select);
        this.select = select;
        if (select.getTagName().equalsIgnoreCase("select")) {
            this.selectWrapper = new Select(select);
        } else {
            this.selectWrapper = null;
        }
        LOGGER.info (this.toString ());
        return this;
    }

    /**
     * Selects provided text value from dropdown.
     * @param text the dropdown text to be selected
     */
    public void selectByText(String text) {
        text = text.trim();
        if (selectWrapper != null) {
            selectWrapper.selectByVisibleText(text);
            return;
        }

        String lowerText = text.toLowerCase();
        for (int i = 0; i < 5; i++) {
            List<WebElement> options = loadOptions();
            for (WebElement item : options) {
                String actual = item.getText();
                if (actual.equalsIgnoreCase(text) || actual.toLowerCase().contains(lowerText)) {
                    StackTraceElement st = Thread.currentThread().getStackTrace()[2];
                    LOGGER.info(String.format("Selecting %s\nsource .(%s:%s)", actual, st.getFileName(), st.getLineNumber()));
                    try {
                        item.click();
                        return;
                    } catch (Exception ex) {
                        LOGGER.warn(ex.getMessage());
                    }
                }
            }
        }
    }

    /**
     * Selects provided text value from dropdown.
     *
     * @param index the dropdown elements index to be selected.
     */
    public void selectByIndex(int index) {
        if (selectWrapper != null) {
            selectWrapper.selectByIndex(index);
            return;
        }
        List<WebElement> options = loadOptions();
        options.get(index).click();
    }

    private List<WebElement> loadOptions() {

        LOGGER.info (this.toString ());
        select.click();
        for (int i = 0; i < 100; i++) {
            WebElement ul = select.findElement(By.xpath(".//ul[contains(@class,'options')]"));
            if (ul.getAttribute("style").contains("visible")) {
                break;
            }
        }
        return select.findElements(By.tagName("li"));
    }

}
