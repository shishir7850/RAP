/**
 * 
 * AuthenticationController.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/

package com.quadratic.user.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.quadratic.rap.aspects.Loggable;
import com.quadratic.user.repository.UserRepository;

/**
 * Controller for handling the authentication related request mappings.
 * 
 * @author Suneel Ayyaparaju
 * 
 */
@Controller
public class AuthenticationController
{
    /**
     * Reference to the logger
     */
	private static final Logger logger = Logger.getLogger( AuthenticationController.class );
    static int log = 0;
    static char[] name = new char[20];
    @Autowired
    UserRepository user;


    /**
     * This method handles the request with the request mapping
     * "/candev/auth/login"
     * 
     * @param error
     *            if true, the model is updated with a message which is further
     *            presented in the view.
     * @param model
     *            Model
     * @return view for login page
     */
/*    @Loggable
    @RequestMapping( value = "/auth/login", method = RequestMethod.GET )
    public String login( @RequestParam( value = "login_error", required = false ) boolean error, Model model,
            HttpServletRequest request )
    {
        if ( error )
        {
            AuthenticationController.logger.debug( "Authentication failed." );
            model.addAttribute( "error", "You have entered an invalid username or password!" );
        }
       
        // return the view that handles the login form
        return "/auth/login";
    }*/

    /**
     * This method handled the request with the request mapping
     * "/RAP/auth/loginfailed" which gets triggered in the case of a failed
     * authentication.
     * 
     * @param model
     *            Model which holds the information about the error.
     * @return view for login page
     */
    @Loggable
    @RequestMapping( value = "/auth/login/failed", method = RequestMethod.GET )
    public String authenticationFailure( Model model, HttpServletRequest request)
    {  	
    	WebApplicationContext applicationContext1 = ContextLoader.getCurrentWebApplicationContext();
		String Name = (String) applicationContext1.getServletContext().getAttribute("message");
		if (user.checkDeactivate(Name) == 1) {
			model.addAttribute( "error", "Your account is deactivated contact Adminstrator" );
			log = 0;
			return "auth/login";
		}
    	
    	if(log == 5) {
    		// Update the model with message
    		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
    		String username = (String) applicationContext.getServletContext().getAttribute("message");
    		applicationContext.getServletContext().removeAttribute("message");
    		if (Arrays.equals(name, username.toCharArray())) {
    			model.addAttribute("error", "You have failed to login unsuccessfully for the fifth time and this account has been locked");   		
        		// redirect back to the login page (note that this is not a view)
    			log = 0;
    			user.deactivate(username);
    			// redirect back to the login page (Lock the account)
        		return "auth/login";
    		} else {
    			model.addAttribute( "error", "You have entered an invalid username or password!" );
    			log = 1;
    			name = username.toCharArray();
    			// redirect back to the login page (Refresh)
        		return "auth/login";
    		}
    		
    	}else {
    		// Update the model with the error
			model.addAttribute( "error", "You have entered an invalid username or password!" );
    		if (log == 0) {
    			WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
        		String username = (String) applicationContext.getServletContext().getAttribute("message");
        		applicationContext.getServletContext().removeAttribute("message");
        		name = username.toCharArray();
        		log++;
        		return "auth/login";
    		}
    		WebApplicationContext applicationContext = ContextLoader.getCurrentWebApplicationContext();
    		String username = (String) applicationContext.getServletContext().getAttribute("message");
    		applicationContext.getServletContext().removeAttribute("message");
    		if (Arrays.equals(name, username.toCharArray())) {
        		// redirect back to the login page (note that this is not a view)
    			log++;
        		return "auth/login";
    		} else {
    			log = 0;
    			name = username.toCharArray();
    			return "auth/login";
    		}
    		
    	}
    }	

    /**
     * @param model
     * @param request
     * @return
     */
    @Loggable
    @RequestMapping( value = "/logout-success", method = RequestMethod.GET )
    public String loggedOut( Model model, HttpServletRequest request )
    {
        // return the view that handles the login form
        return "auth/logout";
    }
    
    @RequestMapping(value="/sessionexpired")
    public String invalidSession(){
        
        return "auth/login";
    }

}
