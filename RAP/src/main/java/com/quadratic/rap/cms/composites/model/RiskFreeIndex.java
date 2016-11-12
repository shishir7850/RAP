/**
 * 
 * RiskFreeIndex.java
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table( name = "RISK_FREE_INDEX" )
public class RiskFreeIndex implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 5884433046024650381L;
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "RISK_FREE_ID" )
    private Integer riskFreeId;
    
    @Column( name = "RISK_FREE_INDX" )
    private String riskFreeIndex;

    /**
     * @return the riskFreeId
     */
    public Integer getRiskFreeId()
    {
        return riskFreeId;
    }

    /**
     * @param riskFreeId the riskFreeId to set
     */
    public void setRiskFreeId( Integer riskFreeId )
    {
        this.riskFreeId = riskFreeId;
    }

    /**
     * @return the riskFreeIndex
     */
    public String getRiskFreeIndex()
    {
        return riskFreeIndex;
    }

    /**
     * @param riskFreeIndex the riskFreeIndex to set
     */
    public void setRiskFreeIndex( String riskFreeIndex )
    {
        this.riskFreeIndex = riskFreeIndex;
    }
    
    

}
