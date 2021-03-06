package com.test.config.driver;

import com.test.annotation.LazyConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;


/**
 *  This is an WebDriver wait class which will be implemented for implicit / explicit wait.
 * */
@LazyConfiguration
public class WebDriverWaitConfig {

    /**
     * define default.timeout property in a profile and default timeout is 30 secs.
     * */
    @Value("${default.timeout:30}")
    private int timeout;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public WebDriverWait webDriverWait(WebDriver driver){

        return new WebDriverWait(driver,this.timeout);
    }
}
