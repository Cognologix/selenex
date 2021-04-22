package com.test.page.controller;

import com.test.controller.WebBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides uniform way to perform operations on tables.
 */
public class Table extends WebBase {
    private static final Logger LOGGER = LoggerFactory.getLogger (Table.class);
    private WebElement table;

    /**
     * Private constructor to force usage through static instance sfactory.
     */
    private Table () {
    }

    /**
     * Static instance factory to return table instance by wrapping provided table WebElement.
     *
     * @param table WebElement with table tag.
     * @return Table instance by wrapping provided table WebElement.
     */
    public static Table getInstance (WebElement table) {
        Table instance = new Table ();
        instance.table = table;
        return instance;
    }

    /**
     * Returns list of list of table cells.
     * usage: getCells().get(0).get(1) gives 0th row's 1st column.
     *
     * @return list of list of table cells.
     */
    public List<List<WebElement>> getCells () {
        List<WebElement> rows = this.table.findElements (By.xpath (".//tbody/tr"));
        List<List<WebElement>> cells = new ArrayList<> ();
        for (WebElement row : rows) {
            cells.add (row.findElements (By.tagName ("td")));
        }
        return cells;
    }
}

