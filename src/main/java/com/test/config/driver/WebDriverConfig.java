package com.test.config.driver;


import com.test.annotation.LazyConfiguration;
import com.test.annotation.ThreadScopeBean;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;

/**
 * This class has been defined with @LazyConfiguration(@Lazy and @Component).
 * RemoteWebDriver config is responsible to execute the tests on local machine which will instantiate WebDriver.
 * User needs to pass any non remote profile to activate this class.
 *   -DSpring.active.profiles=qa,dev,stg
 * */

@LazyConfiguration
@Profile("!remote")
public class WebDriverConfig {

    /**
     *   To avoid conflicts of browser , defined method as a @ThreadScopeBean.
     *  To invoke firefox browser , define browser property in profile.
     * */
    @ThreadScopeBean
    @ConditionalOnProperty(name="browser",havingValue = "firefox")
    public WebDriver firefoxDriver(){

          WebDriverManager.firefoxdriver().setup();
          return new FirefoxDriver();
    }

    /**
     *   To avoid conflicts of browser , defined method as a @ThreadScopeBean.
     *  By default it will execute Chrome driver
     * */
    @ThreadScopeBean
    @ConditionalOnMissingBean
    public WebDriver chromeDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
