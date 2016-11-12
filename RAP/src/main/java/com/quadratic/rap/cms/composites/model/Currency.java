/**
 * 
 * Currency.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.cms.composites.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table( name = "CURRENCY" )
public class Currency implements Serializable
{

    /**
     * Reference to serialVersionUID
     */
    private static final long serialVersionUID = 3062390951777079995L;

    @Id
    @Column( name = "CURR_ID" )
    private Integer currencyId;

    @Column( name = "CURR_NAME" )
    private String currencyName;

    @Column( name = "CURR_LOCATION" )
    private String currencyLocation;

    /**
     * @return the currencyId
     */
    public Integer getCurrencyId()
    {
        return currencyId;
    }

    /**
     * @param currencyId
     *            the currencyId to set
     */
    public void setCurrencyId( Integer currencyId )
    {
        this.currencyId = currencyId;
    }

    /**
     * @return the currencyName
     */
    public String getCurrencyName()
    {
        return currencyName;
    }

    /**
     * @param currencyName
     *            the currencyName to set
     */
    public void setCurrencyName( String currencyName )
    {
        this.currencyName = currencyName;
    }

    /**
     * @return the currencyLocation
     */
    public String getCurrencyLocation()
    {
        return currencyLocation;
    }

    /**
     * @param currencyLocation
     *            the currencyLocation to set
     */
    public void setCurrencyLocation( String currencyLocation )
    {
        this.currencyLocation = currencyLocation;
    }

}
