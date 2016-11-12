/**
 * 
 * DisclosureAssociationDTO.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic Software Systems Pvt Ltd. 
 * 
 **/
package com.quadratic.rap.cms.composites.model;

import java.util.List;


/**
 * @author Suneel Ayyaparaju
 *
 */
public class DisclosureAssociationDTO
{
    
    private List<DisclosureAssociation> disclosureAssociations;
    
    private Integer reasonId;
    
    private String reasonDesc;

    /**
     * @return the disclosureAssociations
     */
    public List<DisclosureAssociation> getDisclosureAssociations()
    {
        return disclosureAssociations;
    }

    /**
     * @param disclosureAssociations the disclosureAssociations to set
     */
    public void setDisclosureAssociations( List<DisclosureAssociation> disclosureAssociations )
    {
        this.disclosureAssociations = disclosureAssociations;
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
    
}
