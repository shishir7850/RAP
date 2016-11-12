/**
 * 
 * DevProfile.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.configuration.profiles;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StringUtils;

import com.quadratic.rap.common.IConstants;

/**
 * @author Suneel Ayyaparaju
 * 
 */
@Configuration
@Profile( IConstants.PROFILE_DEVELOPMENT )
public class DevProfile implements IProfile
{
    /**
     * Constructor -
     */
    public DevProfile()
    {
        super();
        
    }

    @Override
    public String getURLWithContextPath( HttpServletRequest request )
    {
        String urlWithContextPath = "";
        if ( request != null )
        {
            try
            {
                final String requestURL = request.getRequestURL().toString();
                final String cPath = request.getContextPath();
                if ( StringUtils.hasLength( cPath ) )
                {
                    final int cPathStartIndex = requestURL.indexOf( cPath );
                    if ( cPathStartIndex > 0 )
                    {
                        final int cPathEndIndex = cPathStartIndex + cPath.length();
                        urlWithContextPath = requestURL.substring( 0, cPathEndIndex );
                    }
                }
                else
                {
                    final URL serverURL = new URL( request.getScheme(), request.getServerName(),
                            request.getServerPort(), "" );
                    urlWithContextPath = serverURL.toString();
                }
            }
            catch ( final Exception e )
            {
                urlWithContextPath = IConstants.DEV_DOMAIN_CONTEXT_PATH;
            }
        }
        else
        {
            urlWithContextPath = IConstants.DEV_DOMAIN_CONTEXT_PATH;
        }
        return urlWithContextPath;
    }

    @Override
    public String getProfileName()
    {
        return IConstants.PROFILE_DEVELOPMENT;
    }

    @Override
    public boolean sendEmails()
    {
        return false;
    }
    
    @Override
    public String getServerHostPort()
    {
        return "http://localhost:8080";
    }
    
}
