/**
 * 
 * ReasonCodeLKUP.java
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
@Table( name = "REASON_CODE_LKUP")
public class ReasonCodeLKUP implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -7664497127262325183L;
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "reason_id" )
    private Integer reasonId;
    
    @Column( name = "screen" )
    private String screenName;
    
    @Column( name = "tab_name" )
    private String tabName;
    
    @Column( name = "mode" )
    private String mode;
    
    @Column( name = "reason_code" )
    private String reasonCode;

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
     * @return the screenName
     */
    public String getScreenName()
    {
        return screenName;
    }

    /**
     * @param screenName the screenName to set
     */
    public void setScreenName( String screenName )
    {
        this.screenName = screenName;
    }

    /**
     * @return the tabName
     */
    public String getTabName()
    {
        return tabName;
    }

    /**
     * @param tabName the tabName to set
     */
    public void setTabName( String tabName )
    {
        this.tabName = tabName;
    }
    
  
    /**
     * @return the mode
     */
    public String getMode()
    {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode( String mode )
    {
        this.mode = mode;
    }

    /**
     * @return the reasonCode
     */
    public String getReasonCode()
    {
        return reasonCode;
    }

    /**
     * @param reasonCode the reasonCode to set
     */
    public void setReasonCode( String reasonCode )
    {
        this.reasonCode = reasonCode;
    }
    

}
