/**
 * 
 * CompositeYears.java
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
@Table( name = "COMP_YEARS")
public class CompositeYears implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 8346798849127556810L;
    
    @Id
    @Column( name = "YEAR_ID" )
    private Integer year;

    /**
     * @return the year
     */
    public Integer getYear()
    {
        return year;
    }

    /**
     * @param year
     *            the year to set
     */
    public void setYear( Integer year )
    {
        this.year = year;
    }

}
