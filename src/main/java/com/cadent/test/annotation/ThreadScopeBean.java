package com.cadent.test.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.lang.annotation.*;

/**
 *
 * @ThreadScopeBean - This has been defined as an scope - Browser scope.
 * The browser scope has been register with the class BrowserScopePostProcessor (Traverse the "scope" package )
 * This will help to instantiate single browser instance , (it will invoke if in case of session is null)
* */
@Bean
@Scope("browserscope")
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface ThreadScopeBean {
}
