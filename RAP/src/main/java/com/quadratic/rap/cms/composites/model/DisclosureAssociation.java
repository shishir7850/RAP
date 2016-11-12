/**
 * 
 * DisclosureAssociation.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.cms.composites.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table( name = "disclosure_association" )
public class DisclosureAssociation
{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ID" )
    private Integer id;

    @Column( name = "FIRM_ID" )
    private String firmId;

    @Column( name = "DISCLOSURE_ID" )
    private String disclosureId;

    @Column( name = "START_DATE" )
    private String startDate;

    @Column( name = "END_DATE" )
    private String endDate;

    /**
     * Reference to associationAudit
     */
    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "disclosureAssociation" )
    private AssociationAudit associationAudit;

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId( Integer id )
    {
        this.id = id;
    }

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
     * @return the disclosureId
     */
    public String getDisclosureId()
    {
        return disclosureId;
    }

    /**
     * @param disclosureId
     *            the disclosureId to set
     */
    public void setDisclosureId( String disclosureId )
    {
        this.disclosureId = disclosureId;
    }

    /**
     * @return the startDate
     */
    public String getStartDate()
    {
        return startDate;
    }

    /**
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate( String startDate )
    {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate()
    {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate( String endDate )
    {
        this.endDate = endDate;
    }

    /**
     * @return the associationAudit
     */
    public AssociationAudit getAssociationAudit()
    {
        return associationAudit;
    }

    /**
     * @param associationAudit
     *            the associationAudit to set
     */
    public void setAssociationAudit( AssociationAudit associationAudit )
    {
        this.associationAudit = associationAudit;
    }

}
