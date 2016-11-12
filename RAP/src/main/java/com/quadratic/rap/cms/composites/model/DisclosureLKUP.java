/**
 * 
 * DisclosureLKUP.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic Software Systems Pvt Ltd. 
 * 
 **/
package com.quadratic.rap.cms.composites.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table( name = "DISCLOSURES" )
public class DisclosureLKUP implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -2030387928292403339L;
    
    @Id
    @Column( name = "DISCLOSURE_ID" )
    private String disclosureId;
    
    @Column( name = "SEQ_NO" )
    private String seqNo;
    
    @Column( name = "DISCLOSURE_NAME" )
    private String disclosureName;
    
    @Column( name = "DISCLOSURE_DESC" )
    private String disclosureDesc;
    
    @Column( name = "DISCLOSURE_TYPE" )
    private String disclosureType;
    
    @Column( name = "START_DATE" )
    private String disclosureStartDate;
    
    @Column( name = "END_DATE" )
    private String disclosureEndDate;
    
    /**
     * Reference to disclosureAudit
     */
    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "disclosure" )
    private DisclosureAudit disclosureAudit;

    /**
     * @return the disclosureId
     */
    public String getDisclosureId()
    {
        return disclosureId;
    }

    /**
     * @param disclosureId the disclosureId to set
     */
    public void setDisclosureId( String disclosureId )
    {
        this.disclosureId = disclosureId;
    }
    
  
    /**
     * @return the seqNo
     */
    public String getSeqNo()
    {
        return seqNo;
    }

    /**
     * @param seqNo the seqNo to set
     */
    public void setSeqNo( String seqNo )
    {
        this.seqNo = seqNo;
    }

    /**
     * @return the disclosureDesc
     */
    public String getDisclosureDesc()
    {
        return disclosureDesc;
    }

    /**
     * @param disclosureDesc the disclosureDesc to set
     */
    public void setDisclosureDesc( String disclosureDesc )
    {
        this.disclosureDesc = disclosureDesc;
    }

    /**
     * @return the disclosureType
     */
    public String getDisclosureType()
    {
        return disclosureType;
    }

    /**
     * @param disclosureType the disclosureType to set
     */
    public void setDisclosureType( String disclosureType )
    {
        this.disclosureType = disclosureType;
    }

    /**
     * @return the disclosureName
     */
    public String getDisclosureName()
    {
        return disclosureName;
    }

    /**
     * @param disclosureName the disclosureName to set
     */
    public void setDisclosureName( String disclosureName )
    {
        this.disclosureName = disclosureName;
    }

    /**
     * @return the disclosureStartDate
     */
    public String getDisclosureStartDate()
    {
        return disclosureStartDate;
    }

    /**
     * @param disclosureStartDate the disclosureStartDate to set
     */
    public void setDisclosureStartDate( String disclosureStartDate )
    {
        this.disclosureStartDate = disclosureStartDate;
    }

    /**
     * @return the disclosureEndDate
     */
    public String getDisclosureEndDate()
    {
        return disclosureEndDate;
    }

    /**
     * @param disclosureEndDate the disclosureEndDate to set
     */
    public void setDisclosureEndDate( String disclosureEndDate )
    {
        this.disclosureEndDate = disclosureEndDate;
    }

    /**
     * @return the disclosureAudit
     */
    public DisclosureAudit getDisclosureAudit()
    {
        return disclosureAudit;
    }

    /**
     * @param disclosureAudit the disclosureAudit to set
     */
    public void setDisclosureAudit( DisclosureAudit disclosureAudit )
    {
        this.disclosureAudit = disclosureAudit;
    }
 
}
