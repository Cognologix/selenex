package com.cadent.test.scope;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.SimpleThreadScope;

import java.util.Objects;

/**
 *  This class is responsible to invoke single instance of browser , its extending simpleThreadscoTRpebean where checking the session
 *  Id of the webdriver.
 * */
public class BrowserScope extends SimpleThreadScope {

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object obj = super.get(name, objectFactory);
        SessionId sessionId = ((RemoteWebDriver) obj).getSessionId();

        if(Objects.isNull(sessionId)){
            super.remove(name);
            obj = super.get(name,objectFactory);
        }
        return obj;
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {

    }
}