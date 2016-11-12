/**
 * 
 * AccountDTO.java
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

/**
 * @author Suneel Ayyaparaju
 *
 */
public class AccountDTO implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 7224240607675971540L;

    /**
     * Reference to compositeCode
     */

    private String compositeCode;

    /**
     * Reference to compositeName
     */

    private String compositeName;

    /**
     * Reference to startDate
     */

    private String startDate;

    /**
     * Reference to endDate
     */
    private String endDate;

    /**
     * @return the compositeCode
     */
    public String getCompositeCode()
    {
        return compositeCode;
    }

    /**
     * @param compositeCode
     *            the compositeCode to set
     */
    public void setCompositeCode( String compositeCode )
    {
        this.compositeCode = compositeCode;
    }

    /**
     * @return the compositeName
     */
    public String getCompositeName()
    {
        return compositeName;
    }

    /**
     * @param compositeName
     *            the compositeName to set
     */
    public void setCompositeName( String compositeName )
    {
        this.compositeName = compositeName;
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

}
