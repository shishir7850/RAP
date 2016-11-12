package com.quadratic.rap.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This is a marker interface can be added to of any method. Adding this
 * annotation makes the method eligible for logging aspect.
 * 
 * @author Suneel Ayyaparaju
 * 
 */
@Retention( RetentionPolicy.RUNTIME )
@Target( ElementType.METHOD )
public @interface Loggable
{
    /**
     * Returns the value of the log level. If no value defined then the default
     * value of INFO is returned
     * 
     * @return LogLevel
     */
    LogLevel value() default LogLevel.INFO;
}
