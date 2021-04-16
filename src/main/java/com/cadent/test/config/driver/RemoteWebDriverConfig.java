package com.cadent.test.config.driver;

import com.cadent.test.annotation.LazyConfiguration;
import com.cadent.test.annotation.ThreadScopeBean;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Profile;

import java.net.URL;

/**
 * This class has been defined with @LazyConfiguration(@Lazy and @Component).
 * RemoteWebDriver config is responsible to execute the tests on remote machine which will instantiate RemoteWebDriver driver.
 * User needs to pass remote profile to activate this class.
 *   -DSpring.active.profiles=remote
 * */

@LazyConfiguration
@Profile("remote")
public class RemoteWebDriverConfig {

    /**
     *  Pick the value from the remote profile. Define the Remote web server URL.
    * */
    @Value("${selenium.grid.url}")
    private URL url;

    /**
     *   To avoid conflicts of browser , defined method as a @ThreadScopeBean.
     *   To invoke a firefox , pass firefox property from profile.
     * */
    @ThreadScopeBean
    @ConditionalOnProperty(name="browser",havingValue="firefox")
    public RemoteWebDriver firefoxDriver(){

        return new RemoteWebDriver(this.url, DesiredCapabilities.firefox());
    }

    /**
     *   To avoid conflicts of browser , defined method as a @ThreadScopeBean.
     *  By default it will execute Chrome driver
     * */
    @ThreadScopeBean
    @ConditionalOnMissingBean
    public RemoteWebDriver chromeDriver(){

        return new RemoteWebDriver(this.url, DesiredCapabilities.chrome());
    }


}
