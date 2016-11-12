/**
 * 
 * NotAuthenticatedException.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Suneel Ayyaparaju
 * 
 */
public class NotAuthenticatedException extends AuthenticationException
{

    /**
     * Reference to serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param msg
     * @param t
     */
    public NotAuthenticatedException( String msg, Throwable t )
    {
        super( msg, t );
    }

    /**
     * @param msg
     */
    public NotAuthenticatedException( String msg )
    {
        super( msg );
    }

}
