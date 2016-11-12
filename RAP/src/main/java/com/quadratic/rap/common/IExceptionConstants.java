/**
 * 
 * IExceptionConstants.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.common;

/**
 * @author Suneel Ayyaparaju
 * 
 */
public interface IExceptionConstants
{
    // Input related exception constants
    public static final String INPUT_USER_EMPTY = "Input_User_Empty";
 
    public static final String INPUT_ORGANIZATION_EMPTY = "Input_Organization_Empty";
    public static final String INPUT_CONTACT_EMPTY = "Input_Contact_Empty";
    public static final String INPUT_CONNECTION_EMPTY = "Input_Connection_Empty";
  
    // Authentication related exception constants
    public static final String AUTH_NOT_LOGGED_IN = "Auth_Not_Logged_In";

    // Authorization related exception constants
    public static final String AUTHORIZATION_NOT_AUTHORIZED = "Authorization_Not_Authorized";
    
    // AccountLKUP related exception constants
    public static final String ACCOUNT_NOT_ACTIVATED = "Account_Not_Activated";
    
    
    // Operation related exception constants
    public static final String OPERATION_INVALID = "Operation_Invalid";
    
    // User related exception constants
    public static final String USER_NOT_FOUND = "User_Not_Found";
    
    // Login related exception constants
    public static final String LOGIN_ALREADY_TAKEN = "Login_Already_Taken";
    
    // SocialConnection related exception constants
}
