/**
 * 
 * ProdProfile.java
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

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.quadratic.rap.common.IConstants;

/**
 * @author Suneel Ayyaparaju
 * 
 */
@Configuration
@Profile( IConstants.PROFILE_PRODUCTION )
public class ProdProfile implements IProfile
{
    @Override
    public String getURLWithContextPath( HttpServletRequest request )
    {
        return IConstants.RAP_DOMAIN_CONTEXT_PATH;
    }

    @Override
    public String getProfileName()
    {
        return IConstants.PROFILE_PRODUCTION;
    }

    @Override
    public boolean sendEmails()
    {
        return true;
    }

    @Override
    public String getServerHostPort()
    {
        return "http://74.93.88.26:8080";
    }
    
}
