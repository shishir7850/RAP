/**
 * 
 * Firm.java
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

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



/**
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table( name = "FIRMS" )
public class Firm implements Serializable
{

    /**
     * Reference to serialVersionUID
     */
    private static final long serialVersionUID = -1333788884099448448L;

    @Id
    @Column( name = "FIRM_ID" )
    private String firmId;

    @Column( name = "FIRM_NAME" )
    private String firmName;

    @OneToOne
    @JoinColumn( name = "GEO_ID" )
    private Location location;

    /**
     * Reference to firmAudit
     */
    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "firm" )
    private FirmAudit firmAudit;

    /**
     * @return the firmId
     */
    public String getFirmId()
    {
        return firmId;
    }

    /**
     * @param firmId
     *            the firmId to set
     */
    public void setFirmId( String firmId )
    {
        this.firmId = firmId;
    }

    /**
     * @return the firmName
     */
    public String getFirmName()
    {
        return firmName;
    }

    /**
     * @param firmName
     *            the firmName to set
     */
    public void setFirmName( String firmName )
    {
        this.firmName = firmName;
    }

    /**
     * @return the location
     */
    public Location getLocation()
    {
        return location;
    }

    /**
     * @param location
     *            the location to set
     */
    public void setLocation( Location location )
    {
        this.location = location;
    }

    /**
     * @return the firmAudit
     */
    public FirmAudit getFirmAudit()
    {
        return firmAudit;
    }

    /**
     * @param firmAudit
     *            the firmAudit to set
     */
    public void setFirmAudit( FirmAudit firmAudit )
    {
        this.firmAudit = firmAudit;
    }

}
