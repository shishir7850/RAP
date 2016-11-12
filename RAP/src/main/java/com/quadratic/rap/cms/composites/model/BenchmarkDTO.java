/**
 * 
 * BenchmarkDTO.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic Software Systems Pvt Ltd. 
 * 
 **/
package com.quadratic.rap.cms.composites.model;


/**
 * @author Suneel Ayyaparaju
 *
 */
public class BenchmarkDTO
{
    
    private Integer benchmarkId;
    
    private String benchmarkName;
   
    private String benchmarkType;
    
    private String startDate;
    
    private String endDate;
    
    private String country;
    
    private String category;
    
    

    /**
     * @return the benchmarkId
     */
    public Integer getBenchmarkId()
    {
        return benchmarkId;
    }

    /**
     * @param benchmarkId the benchmarkId to set
     */
    public void setBenchmarkId( Integer benchmarkId )
    {
        this.benchmarkId = benchmarkId;
    }

    /**
     * @return the benchmarkName
     */
    public String getBenchmarkName()
    {
        return benchmarkName;
    }

    /**
     * @param benchmarkName the benchmarkName to set
     */
    public void setBenchmarkName( String benchmarkName )
    {
        this.benchmarkName = benchmarkName;
    }

    /**
     * @return the benchmarkType
     */
    public String getBenchmarkType()
    {
        return benchmarkType;
    }

    /**
     * @param benchmarkType the benchmarkType to set
     */
    public void setBenchmarkType( String benchmarkType )
    {
        this.benchmarkType = benchmarkType;
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

    /**
     * @return the country
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry( String country )
    {
        this.country = country;
    }

    /**
     * @return the category
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory( String category )
    {
        this.category = category;
    }
    
    

}
