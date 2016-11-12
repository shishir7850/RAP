/**
 * 
 * UrlUtils.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.util;

import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.quadratic.rap.common.IConstants;

/**
 * @author Suneel Ayyaparaju
 * 
 */
public class UrlUtils
{
  
    /**
     * @param user
     * @return
     */
    public String getUserHomeUrl()
    {
        return getBaseUrl() + "user";
    }
    
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

    

    /**
     * @param request
     * @return
     */
    public String getBaseUrl()
    {
        String baseUrl = getURLWithContextPath( getHttpServletRequest() );
        baseUrl += baseUrl.endsWith( "/" ) ? "" : "/";
        return baseUrl;
    }

    /**
     * @return
     */
    public HttpServletRequest getHttpServletRequest()
    {
        ServletRequestAttributes sra = ( (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes() );
        return sra.getRequest();
    }

}
