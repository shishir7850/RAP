/**
 * 
 */
package com.quadratic.rap.cms.composites.model;

import java.util.List;

/**
 * @author Suneel Ayyaparaju
 *
 */
public class DisclosureDTO
{
    private List<DisclosureLKUP> disclosures;
    
    private Integer reasonId;
    
    private String reasonDesc;

    /**
     * @return the disclosures
     */
    public List<DisclosureLKUP> getDisclosures()
    {
        return disclosures;
    }

    /**
     * @param disclosures the disclosures to set
     */
    public void setDisclosures( List<DisclosureLKUP> disclosures )
    {
        this.disclosures = disclosures;
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
