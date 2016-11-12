/**
 * 
 * Response.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.common;

import java.io.Serializable;

/**
 * @author Suneel Ayyaparaju
 * 
 */
public class Response implements Serializable
{
    /**
     *  Reference to serialVersionUID
     */
    private static final long serialVersionUID = -6995090012993675209L;
    
    /**
     * Reference to status
     */
    private IConstants.EnumResponseStatus status;
    /**
     * Reference to errorDetails
     */
    private String error;
   
    /**
     * Reference to data
     */
    public Object data;

    /**
     * Reference to referrers
     */
    private String referrer;
    /**
     * Reference to callbackUrl
     */
    private String callbackUrl;
    /**
     * Reference to errorCallbackUrl
     */
    private String errorCallbackUrl;

    /**
     * Reference to message
     */
    private String message;

    /**
     * Default constructor
     */

    public Response()
    {
        
    }

    /**
     * @return the status
     */
    public IConstants.EnumResponseStatus getStatus()
    {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus( IConstants.EnumResponseStatus status )
    {
        this.status = status;
    }

    /**
     * @return the error
     */
    public String getError()
    {
        return error;
    }

    /**
     * @param error
     *            the error to set
     */
    public void setError( String error )
    {
        this.error = error;
    }

    /**
     * @return the data
     */
    public Object getData()
    {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData( Object data )
    {
        this.data = data;
    }

    /**
     * @return the referrer
     */
    public String getReferrer()
    {
        return referrer;
    }

    /**
     * @param referrer
     *            the referrer to set
     */
    public void setReferrer( String referrer )
    {
        this.referrer = referrer;
    }

    /**
     * @return the callbackUrl
     */
    public String getCallbackUrl()
    {
        return callbackUrl;
    }

    /**
     * @param callbackUrl
     *            the callbackUrl to set
     */
    public void setCallbackUrl( String callbackUrl )
    {
        this.callbackUrl = callbackUrl;
    }

    /**
     * @return the errorCallbackUrl
     */
    public String getErrorCallbackUrl()
    {
        return errorCallbackUrl;
    }

    /**
     * @param errorCallbackUrl
     *            the errorCallbackUrl to set
     */
    public void setErrorCallbackUrl( String errorCallbackUrl )
    {
        this.errorCallbackUrl = errorCallbackUrl;
    }

    /**
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage( String message )
    {
        this.message = message;
    }

  
}
