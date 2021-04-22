package com.test.annotation;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;


/**
 * @Lazy Annotation - classes gets loaded lazily.
 * @Component annotation - Allows spring to automatically detect our custom beans, scan the classes and inject them whenever needed.
 * @Page annotation can be used to declare a class as  @Component and @Lazy  .
 * */
@Lazy
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface Page {
}
