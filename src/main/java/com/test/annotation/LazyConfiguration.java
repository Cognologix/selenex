package com.test.annotation;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.lang.annotation.*;

/**
 *  This is custom Annotation interface where we can put @LazyAutowired over @Configuration ,
 *  it indicates classes should be loaded lazily
 *
 * */
@Lazy
@Configuration
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface LazyConfiguration {
}
