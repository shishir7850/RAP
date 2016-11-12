/**
 * 
 * QSException.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.exceptions;

/**
 * @author Suneel Ayyaparaju
 * 
 */
public class QSException extends RuntimeException
{

    /**
     * Reference to serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor -
     */
    public QSException()
    {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public QSException(String message, Throwable cause)
    {
        super( message, cause );
    }

    /**
     * @param message
     */
    public QSException(String message)
    {
        super( message );
    }

    /**
     * @param cause
     */
    public QSException(Throwable cause)
    {
        super( cause );
    }

}
