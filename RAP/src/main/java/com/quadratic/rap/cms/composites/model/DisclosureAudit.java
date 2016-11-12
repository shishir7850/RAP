/**
 * 
 * DisclosureAudit.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic Software Systems Pvt Ltd. 
 * 
 **/
package com.quadratic.rap.cms.composites.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table( name = "disclosure_audit" )
public class DisclosureAudit
{
    /**
     * Reference to id
     */
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Integer id;

    @OneToOne
    @JoinColumn( name = "disclosure_id" )
    private DisclosureLKUP disclosure;
    
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
     * @return the disclosure
     */
    public DisclosureLKUP getDisclosure()
    {
        return disclosure;
    }

    /**
     * @param disclosure the disclosure to set
     */
    public void setDisclosure( DisclosureLKUP disclosure )
    {
        this.disclosure = disclosure;
    }

    /**
     * @return the reasonId
     */
    public Integer getReasonId()
    {
        return reasonId;
    }

    /**
     * @param reasonId the reasonId to set
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
     * @param reasonDesc the reasonDesc to set
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
