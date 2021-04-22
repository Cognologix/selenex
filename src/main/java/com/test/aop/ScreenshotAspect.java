package com.test.aop;

import com.test.annotation.TakeScreenshot;
import com.test.service.ScreenShotService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * apo - Aspect oriented programming  - its way for adding behaviour to existing code without modifying that code.
 * @ScreenshotAspect - Wherever we defined @Takescreenshot annotation , it will caprture the screenshot of window.
* */

@Aspect
@Component
public class ScreenshotAspect {

    @Autowired
    private ScreenShotService service;

    @After("@annotation(takeScreenshot)")
    public  void after(TakeScreenshot takeScreenshot) throws IOException {
        this.service.takeScreenShot();
    }
}
