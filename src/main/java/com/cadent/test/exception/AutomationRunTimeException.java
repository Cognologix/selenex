package com.cadent.test.exception;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Base class for all exceptions thrown by the automation framework.
 */
@Lazy
@Component
public class AutomationRunTimeException extends Exception{

    public AutomationRunTimeException() {
        super();
    }

    public AutomationRunTimeException(String message) {
        super(message);
    }

    public AutomationRunTimeException(Throwable cause) {
        super(cause);
    }

    public AutomationRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
