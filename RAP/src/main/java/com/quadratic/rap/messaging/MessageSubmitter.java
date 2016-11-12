/**
 * MessageSubmitter.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 */
package com.quadratic.rap.messaging;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.quadratic.rap.common.IConstants;

/**
 * This service will be used to submit messages to the queue.
 * 
 * @author Suneel Ayyaparaju
 * 
 */
@Service
public class MessageSubmitter
{

    @Autowired
    private JmsTemplate jmsTemplate;
    
    @Autowired
    private Queue queue;

    public void submitMessage( final String message, final IConstants.EnumMessageAction action )
    {
        this.jmsTemplate.send( queue, new MessageCreator()
        {
            @Override
            public Message createMessage( final Session session ) throws JMSException
            {
                final TextMessage msg = session.createTextMessage( message );
                msg.setStringProperty( "ACTION", action.name() );
                return session.createTextMessage( message );
            }
        } );
    }

    /**
     * @param serializableObject
     * @param action
     */
    public void submitMessage( final Serializable serializableObject, final IConstants.EnumMessageAction action )
    {
        this.jmsTemplate.send( queue, new MessageCreator()
        {
            @Override
            public Message createMessage( final Session session ) throws JMSException
            {
                final ObjectMessage msg = session.createObjectMessage( serializableObject );
                msg.setStringProperty( "ACTION", action.name() );
                return msg;
            }
        } );
    }
}
