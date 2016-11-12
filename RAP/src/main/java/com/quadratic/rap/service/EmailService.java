/**
 * 
 * EmailService.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.service;

import java.util.Locale;

import javax.mail.MessagingException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.quadratic.rap.aspects.Loggable;
import com.quadratic.rap.common.IConstants;
import com.quadratic.rap.dto.EmailMessageDTO;
import com.quadratic.rap.messaging.MessageSubmitter;
import com.quadratic.rap.util.UrlUtils;
import com.quadratic.user.model.User;
import com.quadratic.user.repository.UserRepository;

/**
 * @author Suneel Ayyaparaju
 * 
 */

@Service( "mailService" )
public class EmailService
{
    /**
     * Reference to the logger
     */
    private static Logger logger = Logger.getLogger( EmailService.class );

    @Autowired
    private MessageSubmitter messageSubmitter;

    /**
     * Reference to templateEngine
     */
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private UrlUtils urlUtils;

    @Autowired
    private UserRepository userRepository;

    /**
     * Reference to messageSource
     */
    @Autowired
    MessageSource messageSource;

    /**
     * @param rootContextPath
     * @param user
     * @param locale
     * @throws MessagingException
     */
    
    public void sendUserAccountActivationMail( String rootContextPath, User user, String password, Locale locale )
            throws MessagingException
    {
        if ( user != null )
        {
            // Send the activation link for the user.
            final String activationURL = rootContextPath + "activate/" + user.getObjectUUID();
            sendUserAccountActivationMail( user.getEmail(), activationURL, user.getUserName(), password, locale );
        }
    }

    /**
     * @param recipientEmail
     * @param activationLink
     * @param recipientUserName
     * @param recipientPassword
     * @param locale
     * @throws MessagingException
     */
    @Loggable
    @Transactional
    public void sendUserAccountActivationMail( final String recipientEmail, final String activationLink,
            final String recipientUserName, final String recipientPassword, final Locale locale )
            throws MessagingException
    {
        try
        {
            // Read the required messages from the bundle
            final String fromEmail = messageSource.getMessage( "accountActivationEmail.FROM", null, locale );
            final String fromEmailDisplayName = messageSource.getMessage( "accountActivationEmail.DISPLAY_NAME", null,
                    locale );
            final String subject = messageSource.getMessage( "accountActivationEmail.SUBJECT", null, locale );
            final String supportContact = messageSource.getMessage( "accountActivationEmail.SUPPORT_CONTACT", null,
                    locale );

            // Prepare the evaluation context
            final Context ctx = new Context( locale );
            ctx.setVariable( "activationLink", activationLink );
            ctx.setVariable( "supportContact", supportContact );
            ctx.setVariable( "userName", recipientUserName );
            ctx.setVariable( "password", recipientPassword );
            // Create the HTML body using Thymeleaf
            final String htmlContent = templateEngine.process( "user-activation.html", ctx );

            // Submit the message
            EmailMessageDTO emailMsg = new EmailMessageDTO();
            emailMsg.setSubject( subject );
            emailMsg.setRecipient( recipientEmail );
            emailMsg.setSender( fromEmail, fromEmailDisplayName );
            emailMsg.setMessage( htmlContent );
            emailMsg.setIsHtmlContent( true );
            messageSubmitter.submitMessage( emailMsg, IConstants.EnumMessageAction.SEND_EMAIL );
        }
        catch ( final Exception e )
        {
            final String msg = messageSource.getMessage( "mailServerBusy.MESSAGE", null, locale );
            throw new MessagingException( msg, e );
        }
    }

    /**
     * @param recipientEmail
     * @param resetPasswordLink
     * @param locale
     * @throws MessagingException
     */
    @Loggable
    public void sendUserResetPwdMail( final String recipientEmail, final String resetPasswordLink, final Locale locale )
            throws MessagingException
    {
        try
        {
            final String fromEmail = messageSource.getMessage( "accountActivationEmail.FROM", null, locale );
            final String fromEmailDisplayName = messageSource.getMessage( "accountActivationEmail.DISPLAY_NAME", null,
                    locale );
            final String subject = messageSource.getMessage( "resetPasswordEmail.SUBJECT", null, locale );
            final String supportContact = messageSource.getMessage( "accountActivationEmail.SUPPORT_CONTACT", null,
                    locale );

            // Prepare the evaluation context
            final Context ctx = new Context( locale );
            ctx.setVariable( "resetPasswordLink", resetPasswordLink );
            ctx.setVariable( "supportContact", supportContact );
            // Create the HTML body using Thymeleaf
            final String htmlContent = templateEngine.process( "reset-passwordlink.html", ctx );

            // Submit the message
            EmailMessageDTO emailMsg = new EmailMessageDTO();
            emailMsg.setSubject( subject );
            emailMsg.setRecipient( recipientEmail );
            emailMsg.setSender( fromEmail, fromEmailDisplayName );
            emailMsg.setMessage( htmlContent );
            emailMsg.setIsHtmlContent( true );
            messageSubmitter.submitMessage( emailMsg, IConstants.EnumMessageAction.SEND_EMAIL );
        }
        catch ( final Exception e )
        {
            final String msg = messageSource.getMessage( "mailServerBusy.MESSAGE", null, locale );
            throw new MessagingException( msg, e );
        }
    }

    /**
     * @param throwable
     */
    public void sendExceptionMail( Throwable throwable ) throws MessagingException
    {
        sendExceptionMail( throwable != null ? ExceptionUtils.getStackTrace( throwable ) : "" );
    }

    /**
     * @param message
     */
    @Loggable
    public void sendExceptionMail( String message ) throws MessagingException
    {
        sendMail( "Exception Notification", "qa@quadratics.com", "qa@quadratics.com", message, false );
    }

    /**
     * @param subject
     * @param recipient
     * @param sender
     * @param message
     * @param isHtml
     */
    public void sendMail( String subject, String recipient, String sender, String message, boolean isHtml )
    {
        if ( StringUtils.hasLength( message ) )
        {
            try
            {
                // Submit the message
                EmailMessageDTO emailMsg = new EmailMessageDTO();
                emailMsg.setSubject( subject );
                emailMsg.setRecipient( recipient );
                emailMsg.setSender( sender );
                emailMsg.setMessage( message );
                emailMsg.setIsHtmlContent( isHtml );
                messageSubmitter.submitMessage( emailMsg, IConstants.EnumMessageAction.SEND_EMAIL );
            }
            catch ( final Exception e )
            {
                EmailService.logger.error( "Exception while sending an email : " + e.getMessage() );
            }
        }
        else
        {
            EmailService.logger.error( message );
        }
    }

}
