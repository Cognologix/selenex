package com.test.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.lang.annotation.*;

/**
*  This is custom Annotation interface where we can put @LazyAutowired over ,
 *  @Autowired it indicates Class Initialization should be loaded lazily
 *
 *  @Autowried - it resolves and inject , collaborating beans into our beans.
* */

@Lazy
@Autowired
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface LazyAutoWired {
}
