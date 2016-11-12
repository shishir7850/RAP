/**
 * 
 * FeeSchedule.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic Software Systems Pvt Ltd. 
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
@Table( name = "FEE_SCHEDULE" )
public class FeeSchedule implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1472515038617241812L;
    
    @Id
    @Column( name = "FEE_SCHEDULE_ID" )
    private Integer feeScheduleId;

    /**
     * Reference to feeName
     */
    @Column( name = "FEE_NAME" )
    private String feeName;

    /**
     * Reference to methodology
     */
    @Column( name = "METHODOLOGY" )
    private String methodology;
    
    /**
     * Reference to feeRate
     */
    @Column( name = "FEE_RATE" )
    private String feeRate;
    
    /**
     * Reference to lowerMarketValue
     */
    @Column( name = "MIN_MKT_VAL" )
    private String lowerMarketValue;
    
    /**
     * Reference to upperMarketValue
     */
    @Column( name = "MAX_MKT_VAL" )
    private String upperMarketValue;
    
    /**
     * Reference to startDate
     */
    @Column( name = "START_DATE" )
    private String startDate;
    
    /**
     * Reference to endDate
     */
    @Column( name = "END_DATE" )
    private String endDate;

    /**
     * @return the feeScheduleId
     */
    public Integer getFeeScheduleId()
    {
        return feeScheduleId;
    }

    /**
     * @param feeScheduleId the feeScheduleId to set
     */
    public void setFeeScheduleId( Integer feeScheduleId )
    {
        this.feeScheduleId = feeScheduleId;
    }

    /**
     * @return the feeName
     */
    public String getFeeName()
    {
        return feeName;
    }

    /**
     * @param feeName the feeName to set
     */
    public void setFeeName( String feeName )
    {
        this.feeName = feeName;
    }

    /**
     * @return the methodology
     */
    public String getMethodology()
    {
        return methodology;
    }

    /**
     * @param methodology the methodology to set
     */
    public void setMethodology( String methodology )
    {
        this.methodology = methodology;
    }

    /**
     * @return the feeRate
     */
    public String getFeeRate()
    {
        return feeRate;
    }

    /**
     * @param feeRate the feeRate to set
     */
    public void setFeeRate( String feeRate )
    {
        this.feeRate = feeRate;
    }

    /**
     * @return the lowerMarketValue
     */
    public String getLowerMarketValue()
    {
        return lowerMarketValue;
    }

    /**
     * @param lowerMarketValue the lowerMarketValue to set
     */
    public void setLowerMarketValue( String lowerMarketValue )
    {
        this.lowerMarketValue = lowerMarketValue;
    }

    /**
     * @return the upperMarketValue
     */
    public String getUpperMarketValue()
    {
        return upperMarketValue;
    }

    /**
     * @param upperMarketValue the upperMarketValue to set
     */
    public void setUpperMarketValue( String upperMarketValue )
    {
        this.upperMarketValue = upperMarketValue;
    }

    /**
     * @return the startDate
     */
    public String getStartDate()
    {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
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
     * @param endDate the endDate to set
     */
    public void setEndDate( String endDate )
    {
        this.endDate = endDate;
    }

}
