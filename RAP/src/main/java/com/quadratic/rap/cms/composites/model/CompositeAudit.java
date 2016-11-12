/**
 * 
 * CompositeAudit.java
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table( name = "composite_audit" )
public class CompositeAudit implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = -1750445986584960147L;

    /**
     * Reference to id
     */
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Integer id;

    @ManyToOne
    @JoinColumn( name = "comp_id" )
    private Composite composite;
    
    @Column( name = "reason_id" )
    private Integer reasonId;

    @Column( name = "reason_desc" )
    private String reasonDesc;
    
    @Column( name = "user_id" )
    private String userId;
    
    @Column( name = "dt_cr" )
    private String createDate;
    
    @Column( name = "dt_mod" )
    private String modifiedDate;

    /**
     * @return the composite
     */
    public Composite getComposite()
    {
        return composite;
    }

    /**
     * @param composite
     *            the composite to set
     */
    public void setComposite( Composite composite )
    {
        this.composite = composite;
    }

    /**
     * @return the reasonId
     */
    public Integer getReasonId()
    {
        return reasonId;
    }

    /**
     * @param reasonId
     *            the reasonId to set
     */
    public void setReasonId( Integer reasonId )
    {
        this.reasonId = reasonId;
    }

    /**
     * @return the reasonDesc
     */
    public String getReasonDesc()
    {
        return reasonDesc;
    }

    /**
     * @param reasonDesc
     *            the reasonDesc to set
     */
    public void setReasonDesc( String reasonDesc )
    {
        this.reasonDesc = reasonDesc;
    }

    /**
     * @return the userId
     */
    public String getUserId()
    {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId( String userId )
    {
        this.userId = userId;
    }

    /**
     * @return the createDate
     */
    public String getCreateDate()
    {
        return createDate;
    }

    /**
     * @param createDate the createDate to set
     */
    public void setCreateDate( String createDate )
    {
        this.createDate = createDate;
    }

    /**
     * @return the modifiedDate
     */
    public String getModifiedDate()
    {
        return modifiedDate;
    }

    /**
     * @param modifiedDate the modifiedDate to set
     */
    public void setModifiedDate( String modifiedDate )
    {
        this.modifiedDate = modifiedDate;
    }
 
}
