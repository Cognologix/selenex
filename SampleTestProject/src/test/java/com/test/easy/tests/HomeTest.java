package com.test.easy.tests;

import com.test.easy.page.HomePage;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterSuite;

import javax.annotation.PostConstruct;

public class HomeTest extends SpringBaseTest {
    public static final Logger LOGGER = LoggerFactory.getLogger(SpringBaseTest.class);

    @Autowired
    private HomePage homePage;

    @PostConstruct
    private void setup() {
        LOGGER.info("setup");
        homePage.open();
        homePage.dismissAd();
    }
}
