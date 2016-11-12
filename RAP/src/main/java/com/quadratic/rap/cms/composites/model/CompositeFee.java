/**
 * 
 * CompositeFee.java
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table( name = "COMP_FEES" )
public class CompositeFee implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 2851210439869639387L;
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ID" )
    private Integer id;
    
    @OneToOne
    @JoinColumn( name = "FEE_SCHEDULE_ID" )
    private FeeSchedule feeSchedule;
    
    @ManyToOne
    @JoinColumn( name = "COMP_ID" )
    private Composite composite;
    
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
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId( Integer id )
    {
        this.id = id;
    }

    /**
     * @return the feeSchedule
     */
    public FeeSchedule getFeeSchedule()
    {
        return feeSchedule;
    }

    /**
     * @param feeSchedule the feeSchedule to set
     */
    public void setFeeSchedule( FeeSchedule feeSchedule )
    {
        this.feeSchedule = feeSchedule;
    }

    /**
     * @return the composite
     */
    public Composite getComposite()
    {
        return composite;
    }

    /**
     * @param composite the composite to set
     */
    public void setComposite( Composite composite )
    {
        this.composite = composite;
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
