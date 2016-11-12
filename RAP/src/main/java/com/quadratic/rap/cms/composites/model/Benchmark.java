/**
 * 
 * Benchmark.java
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
@Table( name = "COMPOSITE_BENCHMARKS" )
public class Benchmark implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1029873758032046057L;

    /**
     * Reference to id
     */
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ID" )
    private Integer id;
    
    /**
     * Reference to composite
     */
    @ManyToOne
    @JoinColumn( name = "COMP_ID" )
    private Composite composite;
    
    /**
     * Reference to benchmark
     */
    @OneToOne
    @JoinColumn( name = "BENCHMARK_ID" )
    private BenchmarkLKUP benchmark;
    
    /**
     * Reference to benchmarkStartDate
     */
    @Column( name = "BENCHMARK_START" )
    private String benchmarkStartDate;
    
    /**
     * Reference to benchmarkEndDate
     */
    @Column( name = "BENCHMARK_END" )
    private String benchmarkEndDate;
    
    /**
     * Reference to benchmarkCategory
     */
    @Column( name = "BENCHMARK_CATG" )
    private Integer benchmarkCategory;
    
    /**
     * Reference to benchmarkGeo
     */
    @Column( name = "BENCHMARK_GEO" )
    private Integer benchmarkGeo;
    
    /**
     * Reference to benchmarkType
     */
    @Column( name = "BENCHMARK_TYPE" )
    private String benchmarkType;
    
    /**
     * Reference to currency
     */
    @OneToOne
    @JoinColumn( name = "CURR_ID" )
    private Currency currency;

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId( Integer id )
    {
        this.id = id;
    }

    /**
     * @return the composite
     */
    public Composite getComposite()
    {
        return composite;
    }

    /**
     * @param composite
     *            the composite to set
     */
    public void setComposite( Composite composite )
    {
        this.composite = composite;
    }

    /**
     * @return the benchmark
     */
    public BenchmarkLKUP getBenchmark()
    {
        return benchmark;
    }

    /**
     * @param benchmark
     *            the benchmark to set
     */
    public void setBenchmark( BenchmarkLKUP benchmark )
    {
        this.benchmark = benchmark;
    }

    /**
     * @return the benchmarkStartDate
     */
    public String getBenchmarkStartDate()
    {
        return benchmarkStartDate;
    }

    /**
     * @param benchmarkStartDate
     *            the benchmarkStartDate to set
     */
    public void setBenchmarkStartDate( String benchmarkStartDate )
    {
        this.benchmarkStartDate = benchmarkStartDate;
    }

    /**
     * @return the benchmarkEndDate
     */
    public String getBenchmarkEndDate()
    {
        return benchmarkEndDate;
    }

    /**
     * @param benchmarkEndDate
     *            the benchmarkEndDate to set
     */
    public void setBenchmarkEndDate( String benchmarkEndDate )
    {
        this.benchmarkEndDate = benchmarkEndDate;
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
     * @return the currency
     */
    public Currency getCurrency()
    {
        return currency;
    }

    /**
     * @param currency
     *            the currency to set
     */
    public void setCurrency( Currency currency )
    {
        this.currency = currency;
    }

}
