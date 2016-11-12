/**
 * 
 * BenchmarkLKUP.java
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Suneel Ayyaparaju.
 *
 */
@Entity
@Table( name = "BENCHMARKS" )
public class BenchmarkLKUP implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1675718232752646524L;

    /**
     * Reference to benchmarkId
     */
    @Id
    @Column( name = "BENCHMARK_ID" )
    private Integer benchmarkId;

    @Column( name = "BENCHMARK_NAME" )
    private String benchmarkName;

    @Column( name = "BENCHMARK_CATG" )
    private Integer benchmarkCategory;

    @Column( name = "BENCHMARK_GEO" )
    private Integer benchmarkGeo;

    @Column( name = "RISK_FREE_FLAG" )
    private String riskFreeFlag;

    @Column( name = "BENCHMARK_TYPE" )
    private String benchmarkType;

    @Column( name = "BENCHMARK_VENDOR" )
    private String benchmarkVendor;

    /**
     * @return the benchmarkId
     */
    public Integer getBenchmarkId()
    {
        return benchmarkId;
    }

    /**
     * @param benchmarkId
     *            the benchmarkId to set
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
     * @param benchmarkName
     *            the benchmarkName to set
     */
    public void setBenchmarkName( String benchmarkName )
    {
        this.benchmarkName = benchmarkName;
    }

    /**
     * @return the benchmarkCategory
     */
    public Integer getBenchmarkCategory()
    {
        return benchmarkCategory;
    }

    /**
     * @param benchmarkCategory
     *            the benchmarkCategory to set
     */
    public void setBenchmarkCategory( Integer benchmarkCategory )
    {
        this.benchmarkCategory = benchmarkCategory;
    }

    /**
     * @return the benchmarkGeo
     */
    public Integer getBenchmarkGeo()
    {
        return benchmarkGeo;
    }

    /**
     * @param benchmarkGeo
     *            the benchmarkGeo to set
     */
    public void setBenchmarkGeo( Integer benchmarkGeo )
    {
        this.benchmarkGeo = benchmarkGeo;
    }

    /**
     * @return the riskFreeFlag
     */
    public String getRiskFreeFlag()
    {
        return riskFreeFlag;
    }

    /**
     * @param riskFreeFlag
     *            the riskFreeFlag to set
     */
    public void setRiskFreeFlag( String riskFreeFlag )
    {
        this.riskFreeFlag = riskFreeFlag;
    }

    /**
     * @return the benchmarkType
     */
    public String getBenchmarkType()
    {
        return benchmarkType;
    }

    /**
     * @param benchmarkType
     *            the benchmarkType to set
     */
    public void setBenchmarkType( String benchmarkType )
    {
        this.benchmarkType = benchmarkType;
    }

    /**
     * @return the benchmarkVendor
     */
    public String getBenchmarkVendor()
    {
        return benchmarkVendor;
    }

    /**
     * @param benchmarkVendor
     *            the benchmarkVendor to set
     */
    public void setBenchmarkVendor( String benchmarkVendor )
    {
        this.benchmarkVendor = benchmarkVendor;
    }

}
