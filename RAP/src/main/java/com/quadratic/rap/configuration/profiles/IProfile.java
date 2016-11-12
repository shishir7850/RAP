/**
 * 
 * IProfile.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.configuration.profiles;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Suneel Ayyaparaju
 * 
 */
public interface IProfile
{
    /**
     * @param request
     * @return
     */
    public String getURLWithContextPath( HttpServletRequest request );

    /**
     * @return
     */
    public String getProfileName();

    

    /**
     * Method to indicate if an email has to be sent (ex. exceptions, new user
     * signups, etc). By default, this is turned off in development mode and
     * turned on in production mode
     * 
     * @return
     */
    public boolean sendEmails();


    /**
     * @return
     */
    public String getServerHostPort();
}
