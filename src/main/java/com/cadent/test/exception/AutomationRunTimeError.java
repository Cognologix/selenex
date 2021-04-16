package com.cadent.test.exception;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/*
* Base class for all errors thrown by the automation framework.
* */
@Lazy
@Component
public class AutomationRunTimeError extends Error{

    public AutomationRunTimeError() {
    }

    public AutomationRunTimeError(String message) {
        super(message);
    }

    public AutomationRunTimeError(String message, Throwable cause) {
        super(message, cause);
    }

    public AutomationRunTimeError(Throwable cause) {
        super(cause);
    }

    public AutomationRunTimeError(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
