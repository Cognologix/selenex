package com.test.easy.tests;

import com.test.SelenexApplication;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterSuite;


import javax.annotation.PostConstruct;

@SpringBootTest(classes = SelenexApplication.class)
public class SpringBaseTest extends AbstractTestNGSpringContextTests {
}