/**
 * 
 * IConstants.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.common;

import java.util.Random;

/**
 * Constants
 * 
 * @author Suneel Ayyaparaju
 * 
 */
public interface IConstants
{
    static final int DEFAULT_VALUE_SEARCH_RESULTS_PAGE_SIZE = 20;

    static final int DEFAULT_VALUE_SEARCH_RESULTS_INITIAL_PAGE_NUMBER = 1;

    static final String DEFAULT_DATE_FORMAT = "dd-MMM-yyyy";

    static final String DEFAULT_DATETIME_FORMAT = "dd-MMM-yyyy HH:mm";

    static final String RAP_DOMAIN_CONTEXT_PATH = "http://74.93.88.26:8080/RAP/";

    static final String DEV_DOMAIN_CONTEXT_PATH = "http://localhost:8080/";

    static final String PREFIX_HTTP = "http://";

    static final String PREFIX_REQUEST_ID = "REQ";
    
    static final String REGEX_EMAIL = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b";

    static final String VALIDATION_RESPONSE_SUCCESS = "SUCCESS";

    static final String VALIDATION_RESPONSE_ERRORS = "ERRORS";

    static final String APPROVE = "approve";

    static final String REJECT = "reject";

    static final String INITIATED_BY = "initiatedby";

    static final String KEY = "key";

    static final String SECRET = "secret";

    static final String NAMESPACE = "namespace";

    static final String PROFILE_DEVELOPMENT = "development";

    static final String PROFILE_PRODUCTION = "production";

    static final long TIME_FRAME_BETWEEN_INVITES = ( 1 * 24 * 60 * 60 * 1000 );

    // Timezone filtering
    static final String TIMEZONE_ID_PREFIXES = "^(Africa|America|Asia|Atlantic|Australia|Europe|Indian|Pacific)/.*";

    static final String TITLE_DEFAULT_EVENT_SESSION = "Session - 1";

    static final String SEARCH_WILDCARD_CHARACTER = "*";
    
    static final String BASE_FOR_RANDOM_STRING = "QAa0bcLdUK2eHfJgTP8XhiFj61DOklNm9nBoI5pGqYVrs3CtSuMZvwWx4yE7zR";
      
    static final Random random = new Random();
    

    public enum EnumDeletedStatus
    {
        Y, N
    }
    
    public enum EnumRequiredStatus
    {
        Y, N
    }

    // Enum for response status sent by the server to the client (as part of
    // QSJSONResponse object)
    public enum EnumResponseStatus
    {
        OK, ERROR, APPROVAL_REQUIRED, REDIRECT
    }
    // Enum for actions to be submitted asynchronously via JMS
    public enum EnumMessageAction
    {
        PROFILE_UPDATE, SEND_EMAIL
    }

    // Enum for ConnectionRequestStatus
    public enum EnumConnectionRequestStatus
    {
        PENDING, ACCEPTED, CANCELLED, REJECTED
    }

    // Enum for Gender
    public enum EnumUserGender
    {
        MALE, FEMALE
    }

    // Enum for UserStatus
    public enum EnumUserStatus
    {
        ACTIVATION_EMAIL_NOT_SENT, PENDING_ACTIVATION, ACTIVATED, DEACTIVATED, DELETED
    }

    // Enum for Contact Status
    public enum EnumContactStatus
    {
        ACTIVE, DELETED
    }

    // Enum for Yes or No. This can be used in modeling the methods which
    // exhibit only 2 behaviors (yes or no, 0 or 1, follow or stop-following,
    // tag or untag, etc)
    public enum EnumYesNo
    {
        YES, NO
    }

    //Enum for Source Type
    public enum EnumSourceType
    {
        FLAT_FILES_CSV, EXCEL
    }

    //Enum for Column Separator
    public enum EnumColumnSeparator
    {
        COMMA, SEMICOLON, PIPE
    }
    //Enum for Decimal Separator
    public enum EnumDecimalSeparator
    {
        DOT, DASH
    }
    
    //Enum for Upload Location
    public enum EnumUploadLocation
    {
        STANDARD_LOC, FROM_SERVER, FROM_LOCAL
    }
    
    public enum EnumLoadStatus
    {
        INIT, LOADING, LOAD_SUCCESS, LOAD_FAILURE
    }
    
    public enum EnumImportSheduleStatus
    {
    	ACTIVE, IN_ACTIVE, DELETED 
    }
    
    public enum EnumDataImportLoadingType
    {
    	LOAD_NOW, SCHEDULE_LOAD
    }
    
}
