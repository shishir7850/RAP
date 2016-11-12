/**
 * 
 * Location.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic Software Systems Pvt Ltd. 
 * 
 **/
package com.quadratic.rap.cms.composites.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table( name = "GEO" )
public class Location implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 6732798393472897351L;

    
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ID" )
    private Integer id;

    /**
     * Reference to geoId
     */
    @Id
    @Column( name = "GEO_ID" )
    private Integer geoId;

    /**
     * Reference to geoName
     */
    @Column( name = "GEO_NAME" )
    private String geoName;
    
    /**
     * Reference to geoType
     */
    @Column( name = "GEO_TYPE" )
    private String geoType;

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId( Integer id )
    {
        this.id = id;
    }

    /**
     * @return the geoId
     */
    public Integer getGeoId()
    {
        return geoId;
    }

    /**
     * @param geoId the geoId to set
     */
    public void setGeoId( Integer geoId )
    {
        this.geoId = geoId;
    }

    /**
     * @return the geoName
     */
    public String getGeoName()
    {
        return geoName;
    }

    /**
     * @param geoName the geoName to set
     */
    public void setGeoName( String geoName )
    {
        this.geoName = geoName;
    }

    /**
     * @return the geoType
     */
    public String getGeoType()
    {
        return geoType;
    }

    /**
     * @param geoType the geoType to set
     */
    public void setGeoType( String geoType )
    {
        this.geoType = geoType;
    }
    
    
}
