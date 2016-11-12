/**
 * 
 * EmailMessage.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * @author Suneel Ayyaparaju
 * 
 */
public class EmailMessageDTO implements Serializable
{
    /**
     * Reference to serialVersionUID
     */
    private static final long serialVersionUID = 4967890111343001835L;

    private String subject;

    /**
     * Reference to from email
     */
    private String sender;

    /**
     * Reference to fromDisplayName
     */
    private String senderDisplayName;

    /**
     * Reference to recipients
     */
    private List<String> recipients;


    /**
     * Reference to message
     */
    private String message;

    /**
     * Reference to isHtmlContent
     */
    private boolean isHtmlContent = true;

    /**
     * @return the subject
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject( String subject )
    {
        this.subject = subject;
    }

    /**
     * @return the sender
     */
    public String getSender()
    {
        return sender;
    }

    /**
     * @return the senderDisplayName
     */
    public String getSenderDisplayName()
    {
        return senderDisplayName;
    }

    /**
     * @param sender
     *            the sender to set
     */
    public void setSender( String sender )
    {
        setSender( sender, "" );
    }

    /**
     * @param sender
     * @param senderDisplayName
     */
    public void setSender( String sender, String senderDisplayName )
    {
        this.sender = sender;
        this.senderDisplayName = senderDisplayName;
    }

    /**
     * @return
     */
    public String getRecipient()
    {
        if ( recipients != null && !recipients.isEmpty() )
        {
            return recipients.get( 0 );
        }
        return null;
    }

    /**
     * @return the recipients
     */
    public List<String> getRecipients()
    {
        return recipients;
    }

    /**
     * @param recipients
     *            the recipients to set
     */
    public void setRecipients( List<String> recipients )
    {
        this.recipients = recipients;
    }

    /**
     * @param recipient
     */
    public void setRecipient( String recipient )
    {
        if ( StringUtils.hasLength( recipient ) )
        {
            if ( recipients == null )
            {
                recipients = new ArrayList<String>( 1 );
            }
            recipients.add( recipient );
        }
    }

    /**
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage( String message )
    {
        this.message = message;
    }

    /**
     * @param recipient
     */
    public void addRecipient( String recipient )
    {
        if ( recipients == null )
        {
            recipients = new ArrayList<String>( 1 );
        }
        recipients.add( recipient );
    }

    /**
     * @return the isHtmlContent
     */
    public boolean isHtmlContent()
    {
        return isHtmlContent;
    }

    /**
     * @param isHtmlContent
     *            the isHtmlContent to set
     */
    public void setIsHtmlContent( boolean isHtmlContent )
    {
        this.isHtmlContent = isHtmlContent;
    }

}
