/**
 * 
 * InvalidDataException.java
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
public class InvalidDataException extends Exception
{
    /**
     * Reference to serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor -
     */
    public InvalidDataException()
    {
        super();
    }

    /**
     * Constructor - @param message Constructor - @param cause
     */
    public InvalidDataException( String message, Throwable cause )
    {
        super( message, cause );
    }

    /**
     * Constructor - @param message
     */
    public InvalidDataException( String message )
    {
        super( message );
    }

    /**
     * Constructor - @param cause
     */
    public InvalidDataException( Throwable cause )
    {
        super( cause );
    }

}
