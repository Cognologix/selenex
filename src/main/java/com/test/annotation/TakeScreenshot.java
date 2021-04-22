package com.test.annotation;

import java.lang.annotation.*;

/**
*  @TakeSceenshot should be used on methods to take the screen shots . The path has been defined in profile.
* */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface TakeScreenshot {


}
