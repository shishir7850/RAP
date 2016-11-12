/**
 * ActivationController.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.user.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.quadratic.rap.aspects.Loggable;
import com.quadratic.rap.exceptions.AccountNotActivatedException;
import com.quadratic.rap.service.EmailService;
import com.quadratic.rap.util.UrlUtils;
import com.quadratic.user.model.User;
import com.quadratic.user.repository.UserRepository;

/**
 * @author Suneel Ayyaparaju
 * 
 */
@Controller
public class ActivationController
{
    @Autowired
    UserRepository qsUserRepository;

    /**
     * Reference to messageSource
     */
    @Autowired
    MessageSource messageSource;

    /**
     * Reference to mailService
     */
    @Autowired
    EmailService mailService;

    /**
     * Reference to urlUtils
     */
    @Autowired
    UrlUtils qsUrlUtils;

    @Loggable
    @RequestMapping( "/activate/{uuid}" )
    public String activateAccount( @PathVariable( value = "uuid" ) String uuid, Model model, Locale locale )throws Exception
    {
    	try
    	{
    		final boolean activated = qsUserRepository.activate( uuid, true, locale );
            // If activated, take the user to user's home page
            if ( activated )
            {
                // Send a mail to support as well
                final User user = qsUserRepository.findByObjectUUID( uuid );
                if ( user != null && StringUtils.hasLength( user.getUserName() ) )
                {
                    return "redirect:/user";
                }
            }
    	}
    	catch(Exception e)
    	{
    		
    		if( e instanceof AccountNotActivatedException )
    		{
    			model.addAttribute( "error", e.getMessage() );
    		}
    		model.addAttribute( "error", e.getMessage() );
        	
    	}
    	return "/auth/login";
    }

}
