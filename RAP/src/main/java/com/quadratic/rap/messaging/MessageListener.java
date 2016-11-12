/**
 * MessageListener.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 */
package com.quadratic.rap.messaging;

import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.quadratic.rap.common.IConstants;
import com.quadratic.rap.dto.EmailMessageDTO;

/**
 * This class is a message listener from the MDBQueue. This will be invoked
 * whenever there is a message in the queue. All async processing is going route
 * from here.
 * 
 * @author Suneel Ayyaparaju
 * 
 */
public class MessageListener implements javax.jms.MessageListener
{

    @Autowired
    private ApplicationContext springContext;

    /**
     * Reference to mailSender
     */
    @Autowired
    private JavaMailSender mailSender;

    private static final Logger LOGGER = Logger.getLogger( MessageListener.class );

    @Override
    public void onMessage( final Message message )
    {
        try
        {
            LOGGER.debug( "Got message with action: " + message.getStringProperty( "ACTION" ) );
            final IConstants.EnumMessageAction action = IConstants.EnumMessageAction.valueOf( message
                    .getStringProperty( "ACTION" ) );

            switch ( action )
            {
                case PROFILE_UPDATE:
                {
                    // Moved the TextMessage casting from above to this block as
                    // we also handle ObjectMessage for sending emails
                    // Not sure if profile update is a text message (should be
                    // ok if it is json/xml, etc)
                    if ( message instanceof TextMessage )
                    {
                        final TextMessage txtMessage = (TextMessage) message;
                        LOGGER.debug( txtMessage.getText() );
                    }
                    break;
                }
                case SEND_EMAIL:
                {
                    if ( message instanceof ObjectMessage )
                    {
                        ObjectMessage msg = (ObjectMessage) message;
                        if ( msg.getObject() instanceof EmailMessageDTO )
                        {
                            sendMail( (EmailMessageDTO) msg.getObject() );
                        }
                        else
                        {
                            LOGGER.error( "Received message [" + message.getClass() + " - " + message
                                    + "] is not an ObjectMessage" );
                        }
                    }
                    break;
                }
            }
        }
        catch ( final Throwable e )
        {
            LOGGER.error( "Error in handling message", e );
        }
    }

    /**
     * @throws MailException
     */
    public void sendMail( final EmailMessageDTO msg ) throws MailException
    {
        if ( mailSender != null )
        {
            try
            {
                // Prepare message using a Spring helper
                final MimeMessage mimeMessage = mailSender.createMimeMessage();
                final MimeMessageHelper message = new MimeMessageHelper( mimeMessage, "UTF-8" );
                message.setSubject( msg.getSubject() );
                message.setFrom( msg.getSender(), msg.getSenderDisplayName() );
                message.setTo( msg.getRecipient() );
                message.setReplyTo( msg.getSender(), msg.getSenderDisplayName() );
                message.setText( msg.getMessage(), msg.isHtmlContent() );

                mailSender.send( mimeMessage );
            }
            catch ( Exception e )
            {
                LOGGER.error( "Unable to send email titled : " + msg.getSubject() );
                LOGGER.error( e.getMessage() );
            }
        }
    }
}
