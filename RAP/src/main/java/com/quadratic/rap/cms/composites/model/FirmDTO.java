/**
 * 
 * FirmDTO.java
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
public class FirmDTO
{
    
    private List<Firm> firms;
    
    private Integer reasonId;
    
    private String reasonDesc;

    /**
     * @return the firms
     */
    public List<Firm> getFirms()
    {
        return firms;
    }

    /**
     * @param firms the firms to set
     */
    public void setFirms( List<Firm> firms )
    {
        this.firms = firms;
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
