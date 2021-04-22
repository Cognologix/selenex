package com.test.annotation;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @Lazy Annotation - classes gets loaded lazily.
 * @Component annotation - Allows spring to automatically detect our custom beans, scan the classes and inject them whenever needed.
 * @PageFragment annotation can be used to declare a class as  @Component and @Lazy ,also defining Scope as a prototype will help to create different instance every time.
 * We can use it for Pages .
 * */
@Lazy
@Component
@Scope("prototype")
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PageFragment {
}
