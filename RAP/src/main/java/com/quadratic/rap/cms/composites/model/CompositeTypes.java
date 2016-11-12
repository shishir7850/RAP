/**
 * 
 * CompositeTypes.java
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table( name = "COMP_TYPES" )
public class CompositeTypes implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -1758293667110766328L;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "COMP_TYPE_ID" )
    private Integer compositeTypeId;

    /**
     * Reference to compositeCode
     */
    @Column( name = "COMP_TYPE" )
    private String compositeTypeName;

    /**
     * @return the compositeTypeId
     */
    public Integer getCompositeTypeId()
    {
        return compositeTypeId;
    }

    /**
     * @param compositeTypeId
     *            the compositeTypeId to set
     */
    public void setCompositeTypeId( Integer compositeTypeId )
    {
        this.compositeTypeId = compositeTypeId;
    }

    /**
     * @return the compositeTypeName
     */
    public String getCompositeTypeName()
    {
        return compositeTypeName;
    }

    /**
     * @param compositeTypeName
     *            the compositeTypeName to set
     */
    public void setCompositeTypeName( String compositeTypeName )
    {
        this.compositeTypeName = compositeTypeName;
    }

}
