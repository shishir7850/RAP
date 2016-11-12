/**
 * 
 * SMTPConfig.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Suneel Ayyaparaju.
 *
 */
@Entity
@Table( name = "SMTP_CONFIG" )
public class SMTPConfig
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ID" )
    private int id;

    @Column( name = "SMTP_HOST" )
    private String smtpHost;

    @Column( name = "SMTP_PORT" )
    private String smtpPort;

    @Column( name = "SMTP_USERNAME" )
    private String smtpUsername;

    @Column( name = "SMTP_PASSWORD" )
    private String smtpPassword;

    @Column( name = "TRANSPORT_PROTOCOL" )
    private String transportProtocol;

    @Column( name = "IS_SSL_ENABLE" )
    private String isSSLEnable;

    @Column( name = "SSL_PORT" )
    private String sslPort;

    @Column( name = "SSL_FACTORY_CLASS" )
    private String sslFactoryClass;

    @Column( name = "IS_SSL_FALLBACK" )
    private String isSSLFallback;

    @Column( name = "IS_AUTH" )
    private String isAuth;

    @Column( name = "IS_TLS_ENABLE" )
    private String isTLSEnable;

    @Column( name = "TLS_PORT" )
    private String tlsPort;

    @Column( name = "SERVER_PROTOCOL" )
    private String serverProtocol;

    @Column( name = "IS_DEBUG" )
    private String isDebug;

    @Column( name = "STATUS" )
    private String status;

    /**
     * @return the smtpHost
     */
    public String getSmtpHost()
    {
        return smtpHost;
    }

    /**
     * @param smtpHost
     *            the smtpHost to set
     */
    public void setSmtpHost( String smtpHost )
    {
        this.smtpHost = smtpHost;
    }

    /**
     * @return the smtpPort
     */
    public String getSmtpPort()
    {
        return smtpPort;
    }

    /**
     * @param smtpPort
     *            the smtpPort to set
     */
    public void setSmtpPort( String smtpPort )
    {
        this.smtpPort = smtpPort;
    }

    /**
     * @return the smtpUsername
     */
    public String getSmtpUsername()
    {
        return smtpUsername;
    }

    /**
     * @param smtpUsername
     *            the smtpUsername to set
     */
    public void setSmtpUsername( String smtpUsername )
    {
        this.smtpUsername = smtpUsername;
    }

    /**
     * @return the smtpPassword
     */
    public String getSmtpPassword()
    {
        return smtpPassword;
    }

    /**
     * @param smtpPassword
     *            the smtpPassword to set
     */
    public void setSmtpPassword( String smtpPassword )
    {
        this.smtpPassword = smtpPassword;
    }

    /**
     * @return the transportProtocol
     */
    public String getTransportProtocol()
    {
        return transportProtocol;
    }

    /**
     * @param transportProtocol
     *            the transportProtocol to set
     */
    public void setTransportProtocol( String transportProtocol )
    {
        this.transportProtocol = transportProtocol;
    }

    /**
     * @return the sslPort
     */
    public String getSslPort()
    {
        return sslPort;
    }

    /**
     * @param sslPort
     *            the sslPort to set
     */
    public void setSslPort( String sslPort )
    {
        this.sslPort = sslPort;
    }

    /**
     * @return the sslFactoryClass
     */
    public String getSslFactoryClass()
    {
        return sslFactoryClass;
    }

    /**
     * @param sslFactoryClass
     *            the sslFactoryClass to set
     */
    public void setSslFactoryClass( String sslFactoryClass )
    {
        this.sslFactoryClass = sslFactoryClass;
    }

    /**
     * @return the isAuth
     */
    public String getIsAuth()
    {
        return isAuth;
    }

    /**
     * @param isAuth
     *            the isAuth to set
     */
    public void setIsAuth( String isAuth )
    {
        this.isAuth = isAuth;
    }

    /**
     * @return the tlsPort
     */
    public String getTlsPort()
    {
        return tlsPort;
    }

    /**
     * @param tlsPort
     *            the tlsPort to set
     */
    public void setTlsPort( String tlsPort )
    {
        this.tlsPort = tlsPort;
    }

    /**
     * @return the serverProtocol
     */
    public String getServerProtocol()
    {
        return serverProtocol;
    }

    /**
     * @param serverProtocol
     *            the serverProtocol to set
     */
    public void setServerProtocol( String serverProtocol )
    {
        this.serverProtocol = serverProtocol;
    }

    /**
     * @return the isSSLEnable
     */
    public String getIsSSLEnable()
    {
        return isSSLEnable;
    }

    /**
     * @param isSSLEnable
     *            the isSSLEnable to set
     */
    public void setIsSSLEnable( String isSSLEnable )
    {
        this.isSSLEnable = isSSLEnable;
    }

    /**
     * @return the isSSLFallback
     */
    public String getIsSSLFallback()
    {
        return isSSLFallback;
    }

    /**
     * @param isSSLFallback
     *            the isSSLFallback to set
     */
    public void setIsSSLFallback( String isSSLFallback )
    {
        this.isSSLFallback = isSSLFallback;
    }

    /**
     * @return the isTLSEnable
     */
    public String getIsTLSEnable()
    {
        return isTLSEnable;
    }

    /**
     * @param isTLSEnable
     *            the isTLSEnable to set
     */
    public void setIsTLSEnable( String isTLSEnable )
    {
        this.isTLSEnable = isTLSEnable;
    }

    /**
     * @return the isDebug
     */
    public String getIsDebug()
    {
        return isDebug;
    }

    /**
     * @param isDebug
     *            the isDebug to set
     */
    public void setIsDebug( String isDebug )
    {
        this.isDebug = isDebug;
    }

    /**
     * @return the status
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus( String status )
    {
        this.status = status;
    }

}
