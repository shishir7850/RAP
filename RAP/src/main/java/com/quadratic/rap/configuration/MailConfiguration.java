/**
 * 
 * MailConfiguration.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.configuration;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.quadratic.rap.common.SMTPConfig;
import com.quadratic.rap.exceptions.QSException;
import com.quadratic.rap.util.Utils;
import com.quadratic.user.repository.UserRepository;

/**
 * @author Suneel Ayyaparaju
 * 
 */
@Configuration
public class MailConfiguration
{
    @Autowired
    Utils utils;

    /**
     * Reference to userRepository
     */
    @Autowired
    UserRepository userRepository;

    /**
     * Reference to messageSource
     */
    @Autowired
    MessageSource messageSource;

    @Bean
    public JavaMailSender mailSender()
    {
        SMTPConfig smtpConfig = userRepository.findSMTPConfig();

        if ( smtpConfig == null )
        {
            throw new QSException( messageSource.getMessage( "Invalid_SMTP_Found", null, null ) );
        }

        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost( smtpConfig.getSmtpHost() );
        mailSender.setPort( Integer.parseInt( smtpConfig.getSmtpPort() ) );
        mailSender.setUsername( smtpConfig.getSmtpUsername() );
        mailSender.setPassword( Utils.decodeString( smtpConfig.getSmtpPassword() ) );

        final Properties javaMailProperties = new Properties();
       // javaMailProperties.setProperty( "mail.transport.protocol", smtpConfig.getTransportProtocol() );
       // javaMailProperties.setProperty( "mail.smtp.ssl.enable", smtpConfig.getIsSSLEnable() );
        //javaMailProperties.setProperty( "mail.smtp.socketFactory.port", smtpConfig.getSslPort() );
        //javaMailProperties.setProperty( "mail.smtp.socketFactory.class", smtpConfig.getSslFactoryClass() );
       // javaMailProperties.setProperty( "mail.smtp.socketFactory.fallback", smtpConfig.getIsSSLFallback() );
        
        javaMailProperties.setProperty("mail.store.protocol", "smtp");
        javaMailProperties.setProperty( "mail.smtp.auth", smtpConfig.getIsAuth() );
        javaMailProperties.setProperty( "mail.smtp.starttls.enable", smtpConfig.getIsTLSEnable() );
        javaMailProperties.setProperty( "mail.smtp.port", smtpConfig.getTlsPort() );
        javaMailProperties.setProperty( "mail.server.protocol", smtpConfig.getServerProtocol() );
        javaMailProperties.setProperty( "mail.debug", smtpConfig.getIsDebug() );
        mailSender.setJavaMailProperties( javaMailProperties );

        return mailSender;
    }
}
