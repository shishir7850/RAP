/**
 * 
 * AbstractEntity.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.common;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.joda.time.DateTime;

/**
 * Base class to derive entity classes from.
 * 
 * @author Suneel Ayyaparaju
 */

@MappedSuperclass
public abstract class AbstractEntity implements Serializable
{

    /**
     * Reference to serialVersionUID
     */
    private static final long serialVersionUID = -1593039094202012790L;

       /**
     * Reference to createdBy
     */
    @Column( name = "CR_BY", nullable = false )
    private String createdBy;

    /**
     * Reference to createdDate
     */
    @Column( name = "DT_CR", nullable = false )
    private DateTime  createdDate;

    /**
     * Reference to modifiedBy
     */
    @Column( name = "MOD_BY" )
    private String modifiedBy;

    /**
     * Reference to lastModifiedDate
     */
    @Column( name = "DT_MOD" )
    private DateTime  lastModifiedDate;

    /**
     * @return the createdBy
     */
    public String getCreatedBy()
    {
        return createdBy;
    }

    /**
     * @param createdBy
     *            the createdBy to set
     */
    public void setCreatedBy( String createdBy )
    {
        this.createdBy = createdBy;
    }

    /**
     * @return the createdDate
     */
    public DateTime  getCreatedDate()
    {
        return createdDate;
    }

    /**
     * @param createdDate
     *            the createdDate to set
     */
    public void setCreatedDate( DateTime  createdDate )
    {
        this.createdDate = createdDate;
    }

    /**
     * @return the modifiedBy
     */
    public String getModifiedBy()
    {
        return modifiedBy;
    }

    /**
     * @param modifiedBy
     *            the modifiedBy to set
     */
    public void setModifiedBy( String modifiedBy )
    {
        this.modifiedBy = modifiedBy;
    }

    /**
     * @return the lastModifiedDate
     */
    public DateTime  getLastModifiedDate()
    {
        return lastModifiedDate;
    }

    /**
     * @param lastModifiedDate
     *            the lastModifiedDate to set
     */
    public void setLastModifiedDate( DateTime  lastModifiedDate )
    {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * @return String representation of the generated UUID
     */
    public synchronized String generateUUID()
    {
        return UUID.randomUUID().toString();
    }

}
