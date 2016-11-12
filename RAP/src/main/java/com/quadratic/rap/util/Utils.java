/**
 * 
 * Utils.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.quadratic.rap.common.IConstants;
import com.quadratic.rap.common.IExceptionConstants;
import com.quadratic.rap.exceptions.NotAuthenticatedException;
import com.quadratic.user.model.User;
import com.quadratic.user.service.QSUserDetails;

/**
 * @author Suneel Ayyaparaju
 * 
 */

public final class Utils
{
    /**
     * Reference to the logger
     */
    private static final Logger logger = LoggerFactory.getLogger( Utils.class );

    /**
     * Reference to random
     */
    private static Random random = new Random();

    /**
     * Reference to gender
     */
    private static List<String> gender = null;

    /**
     * Reference to languages
     */
    private static List<String> languages = null;

    /**
     * Reference to messageSource
     */
    @Autowired
    private MessageSource messageSource;

    //
    // Instance methods
    //

    //
    // Authentication related
    //

    /**
     * This method returns the Authentication object from the security context.
     * 
     * @return Authentication object if one exists else returns null
     */
    public Authentication getAuthentication()
    {
        final SecurityContext context = SecurityContextHolder.getContext();
        if ( context != null )
        {
            return context.getAuthentication();
        }
        return null;
    }

    /**
     * This method returns the principal object from the Authentication object.
     * If there is no Authentication object, this method returns null.
     * 
     * @return Principal (QSUserDetails) object associated with the
     *         Authentication
     */
    public QSUserDetails getPrincipal()
    {
        final Authentication auth = getAuthentication();
        final Object principal = auth != null ? auth.getPrincipal() : null;
        if ( principal instanceof QSUserDetails)
        {
            return (QSUserDetails) principal;
        }
        return null;
    }

    /**
     * This method returns the current logged in user (User). If there is no
     * logged in user, this method returns null.
     * 
     * @return User object representing the current logged in user. If there
     *         is no authenticated user, this method returns null.
     */
    public User getAuthenticatedUser()
    {
        QSUserDetails principal = getPrincipal();
        if ( principal != null )
        {
            return principal.getUser();
        }
        return null;
    }

    /**
     * This method sets the specified user as the authenticated user on the
     * security context. This user will be set in the context if and only if the
     * specified user is not null and there is no authenticated user
     * 
     * @param user
     */
    public void setUserInSession( User user )
    {
        if ( user != null && getAuthenticatedUser() == null )
        {
            final SecurityContext context = SecurityContextHolder.getContext();
            final QSUserDetails userDetails = new QSUserDetails( user );
            final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, user.getPassword() );
            context.setAuthentication( authentication );
        }
    }

    /**
     * This method returns the current logged in user (User). If there is no
     * logged in user, this method throws a NotAuthenticatedException.
     * 
     * @return User object representing the current logged in user. This
     *         method throws a NotAuthenticatedException if there is no
     *         authenticated user
     * @throws com.quadratic.rap.exceptions.NotAuthenticatedException
     */
    public User getAuthenticatedUserEx() throws NotAuthenticatedException
    {
        User user = getAuthenticatedUser();
        if ( user == null )
        {
            throw new NotAuthenticatedException( getMessage( IExceptionConstants.AUTH_NOT_LOGGED_IN ) );
        }
        return user;
    }

    //
    // Message related instance methods
    //

    /**
     * @param key
     * @return
     */
    public String getMessage( String key )
    {
        return getMessage( key, null, getLocale() );
    }

    /**
     * @param key
     * @param locale
     * @return
     */
    public String getMessage( String key, Locale locale )
    {
        return getMessage( key, null, locale );
    }

    /**
     * @param key
     * @param args
     * @return
     */
    public String getMessage( String key, Object[] args )
    {
        return getMessage( key, null, getLocale() );
    }

    /**
     * @param key
     * @param args
     * @param locale
     * @return
     */
    public String getMessage( String key, Object[] args, Locale locale )
    {
        if ( StringUtils.hasLength( key ) )
        {
            return getMessageForKey( key, args, locale );
        }
        return "";
    }

    //
    // Locale helpers
    //

    /**
     * This method returns the locale. First it tries to fetch the current
     * HttpServletRequest and if there is a locale on that request, this method
     * returns it else returns the default locale (Locale.getDefault())
     * 
     * @return Locale
     */
    public Locale getLocale()
    {
        HttpServletRequest request = getRequest();
        Locale locale = request != null ? request.getLocale() : Locale.getDefault();
        return locale == null ? Locale.getDefault() : locale;
    }

    //
    // Request related urls
    //

    /**
     * @return
     */
    public HttpServletRequest getRequest()
    {
        ServletRequestAttributes sra = ( (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes() );
        return sra.getRequest();
    }

    //
    // Private instance based internal helpers
    //

    /**
     * @param key
     * @param args
     * @param locale
     * @return
     */
    private String getMessageForKey( String key, Object[] args, Locale locale )
    {
        // Determine the locale if it is not specified
        Locale loc = locale;
        if ( loc == null )
        {
            HttpServletRequest req = getRequest();
            loc = req != null ? req.getLocale() : Locale.getDefault();
        }

        String message = key;
        try
        {
            message = messageSource.getMessage( key, args, loc );
        }
        catch ( NoSuchMessageException e )
        {
            message = key;
        }
        return message;
    }

    //
    // Non-Instance (STATIC) methods
    //

    /**
     * @param sourceString
     * @return
     */
    public static String encodeString( String sourceString )
    {
        return new String( Base64.encode( sourceString.getBytes() ) );
    }

    /**
     * @param encodedString
     * @return
     */
    public static String decodeString( String encodedString )
    {
        return new String( Base64.decode( encodedString.getBytes() ) );
    }

    /**
     * @param jsonString
     * @param valueType
     * @return
     */
    public static <T> T convertJSONToJavaObject( String jsonString, Class<T> valueType )
    {
        final ObjectMapper objectMapper = new ObjectMapper();
        T value = null;

        try
        {
            value = objectMapper.readValue( jsonString, valueType );
        }
        catch ( final Exception e )
        {
            value = null;
            Utils.logger.debug( e.getMessage() );
        }
        return value;
    }

    /**
     * @param javaObject
     * @return
     */
    public static String convertJavaObjectToJSON( Object javaObject )
    {
        final ObjectMapper objectMapper = new ObjectMapper();
        String value = null;
        try
        {
            value = objectMapper.writeValueAsString( javaObject );
        }
        catch ( final Exception e )
        {
            value = null;
            Utils.logger.debug( e.getMessage() );
        }
        return value;
    }

    /**
     * Case insensitive comparison of two strings
     * 
     * @param str1
     * @param str2
     * @return
     */
    public static boolean stringCompare( String str1, String str2 )
    {
        return Utils.stringCompare(str1, str2, true);
    }

    /**
     * @param str1
     * @param str2
     * @param ignoreCase
     * @return
     */
    public static boolean stringCompare( String str1, String str2, boolean ignoreCase )
    {
        boolean verdict = false;
        if ( str1 == str2 )
        {
            verdict = true;
        }
        else if ( ( StringUtils.hasLength( str1 ) && !StringUtils.hasLength( str2 ) )
                || ( StringUtils.hasLength( str2 ) && !StringUtils.hasLength( str1 ) ) )
        {
            verdict = false;
        }
        else
        {
            verdict = ignoreCase ? str1.equalsIgnoreCase( str2 ) : str1.equals( str2 );
        }
        return verdict;
    }

    /**
     * @param length
     * @return
     */
    public static synchronized String generateRandomString( int length )
    {
        StringBuilder b = new StringBuilder();
        for ( int i = 0; i < length; i++ )
        {
            b.append( IConstants.BASE_FOR_RANDOM_STRING.charAt( Utils.random
                    .nextInt( IConstants.BASE_FOR_RANDOM_STRING.length() ) ) );
        }
        return b.toString();
    }

    /** HARDCODED FOR THE TIMEBEING ***/

    /**
     * @return
     */
    public static List<String> getGenderOptions()
    {
        if ( Utils.gender == null )
        {
            Utils.gender = new ArrayList<>( 0 );
            Utils.gender.add( "Male" );
            Utils.gender.add( "Female" );
            Utils.gender.add( "Prefer not to answer" );

        }
        return Utils.gender;
    }

    /**
     * @return
     */
    public static List<String> getLanguages()
    {
        if ( Utils.languages == null )
        {
            Utils.languages = new ArrayList<>( 0 );
            Utils.languages.add( "English" );
            Utils.languages.add( "French" );
            Utils.languages.add( "German" );
            Utils.languages.add( "Korean" );
            Utils.languages.add( "Japanese " );

        }
        return Utils.languages;
    }

   /* public static void main(String[] args) throws Exception {
        DateFormat formatter = new SimpleDateFormat("dd-MMM-yy hh:mm:ss a");
        System.out.println(getDateStringToShow(formatter.parse("26-Nov-10 03:31:20 PM +0000"), "Asia/Calcutta", "Europe/Dublin", false));
        System.out.println(getDateStringToShow(formatter.parse("02-Oct-10 10:00:00 AM +0530"), "Asia/Calcutta", "Europe/Dublin", false));
        System.out.println(getDateStringToShow(formatter.parse("26-Nov-10 11:51:20 PM +0530"), "Asia/Kolkata", "Europe/Dublin", false));
        System.out.println(getDateStringToShow(formatter.parse("02-Oct-10 01:01:00 AM +0530"), "Asia/Kolkata", "Europe/Dublin", false));
    }*/

    public static void main(String[] args) throws Exception {
    	/*DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Date date = sdf.parse("2013-01-09T19:32:49.103+00:00"); 
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
        System.out.println(sdf.format(date));*/
    	Locale locale2=new Locale("en","US");
    	
    	DateFormatter dateFormatter =new DateFormatter("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    	dateFormatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));
    	try {
    		//System.out.println(dateFormat.parse("2015-04-15 03:42:00 +00:00"));
			System.out.println(dateFormatter.parse("2015-04-15 03:42:00.000+00:00",locale2));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public static String getDateStringToShow(Date date, String sourceTimeZoneId, String targetTimeZoneId, boolean includeTime) {
        DateTime dateRes = new DateTime(date);

        DateTime nowIndia = dateRes.toDateTime(DateTimeZone.forID(sourceTimeZoneId));
        DateTime nowDub = nowIndia.toDateTime(DateTimeZone.forID(targetTimeZoneId));

        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd-MMM-yy z");
        System.out.println("nowIndia : " + fmt.print(nowIndia));
        System.out.println("nowDub : " + fmt.print(nowDub));
        return "---next---";
    }
}
