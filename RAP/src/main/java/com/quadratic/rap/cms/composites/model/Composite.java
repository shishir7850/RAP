/**
 * 
 * Composite.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.cms.composites.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table( name = "COMPOSITES" )
public class Composite
{

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "COMP_ID" )
    private Integer compositeId;

    /**
     * Reference to compositeCode
     */
    @Column( name = "COMP_CODE" )
    private String compositeCode;

    /**
     * Reference to compositeName
     */
    @Column( name = "COMP_NAME" )
    private String compositeName;

    /**
     * Reference to firm
     */
    @OneToOne
    @JoinColumn( name = "FIRM_ID" )
    private Firm firm;

    /**
     * Reference to currencyId
     */
    @OneToOne
    @JoinColumn( name = "CURR_ID" )
    private Currency currency;

    /**
     * Reference to riskFreeId
     */
    @Column( name = "RISK_FREE_ID" )
    private Integer riskFreeId;

    /**
     * Reference to compositeTypeId
     */
    @Column( name = "COMP_TYPE_ID" )
    private Integer compositeTypeId;

    /**
     * Reference to compositeStartDate
     */
    @Column( name = "COMP_START" )
    private String compositeStartDate;

    /**
     * Reference to compositeEndDate
     */
    @Column( name = "COMP_END" )
    private String compositeEndDate;

    /**
     * Reference to minimumAccountSize
     */
    @Column( name = "MIN_PORT_SIZE" )
    private Integer minimumAccountSize;

    /**
     * Reference to monthOutsideMinAcctSize
     */
    @Column( name = "MON_OUTSIDE_MIN_SIZE" )
    private Integer monthOutsideMinAcctSize;

    @Column( name = "YEAR_EXAMINED" )
    private Integer yearExamined;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "composite" )
    private List<Category> categories;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "composite" )
    private List<Account> accounts;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "composite" )
    private List<Benchmark> benchmarks;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "composite" )
    private List<Disclosure> disclosures;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "composite" )
    private List<Description> descriptions;

    @OneToMany( cascade = CascadeType.ALL,  orphanRemoval = true, mappedBy = "composite" )
    private List<CompositeFee> fees;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "composite" )
    private List<CompositeNote> notes;

    /**
     * Reference to reasonCodes
     */
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "composite" )
    private List<CompositeAudit> reasonCodes;

    /**
     * @return the compositeId
     */
    public Integer getCompositeId()
    {
        return compositeId;
    }

    /**
     * @param compositeId
     *            the compositeId to set
     */
    public void setCompositeId( Integer compositeId )
    {
        this.compositeId = compositeId;
    }

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
     * @return the firm
     */
    public Firm getFirm()
    {
        return firm;
    }

    /**
     * @param firm
     *            the firm to set
     */
    public void setFirm( Firm firm )
    {
        this.firm = firm;
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

    /**
     * @return the riskFreeId
     */
    public Integer getRiskFreeId()
    {
        return riskFreeId;
    }

    /**
     * @param riskFreeId
     *            the riskFreeId to set
     */
    public void setRiskFreeIndex( Integer riskFreeId )
    {
        this.riskFreeId = riskFreeId;
    }

    /**
     * @return the compositeTypeId
     */
    public Integer getCompositeTypeId()
    {
        return compositeTypeId;
    }

    /**
     * @param compositeTypeId
     *            the compositeTypeId to set
     */
    public void setCompositeType( Integer compositeTypeId )
    {
        this.compositeTypeId = compositeTypeId;
    }

    /**
     * @return the compositeStartDate
     */
    public String getCompositeStartDate()
    {
        return compositeStartDate;
    }

    /**
     * @param compositeStartDate
     *            the compositeStartDate to set
     */
    public void setCompositeStartDate( String compositeStartDate )
    {
        this.compositeStartDate = compositeStartDate;
    }

    /**
     * @return the compositeEndDate
     */
    public String getCompositeEndDate()
    {
        return compositeEndDate;
    }

    /**
     * @param compositeEndDate
     *            the compositeEndDate to set
     */
    public void setCompositeEndDate( String compositeEndDate )
    {
        this.compositeEndDate = compositeEndDate;
    }

    /**
     * @return the minimumAccountSize
     */
    public Integer getMinimumAccountSize()
    {
        return minimumAccountSize;
    }

    /**
     * @param minimumAccountSize
     *            the minimumAccountSize to set
     */
    public void setMinimumAccountSize( Integer minimumAccountSize )
    {
        this.minimumAccountSize = minimumAccountSize;
    }

    /**
     * @return the monthOutsideMinAcctSize
     */
    public Integer getMonthOutsideMinAcctSize()
    {
        return monthOutsideMinAcctSize;
    }

    /**
     * @param monthOutsideMinAcctSize
     *            the monthOutsideMinAcctSize to set
     */
    public void setMonthOutsideMinAcctSize( Integer monthOutsideMinAcctSize )
    {
        this.monthOutsideMinAcctSize = monthOutsideMinAcctSize;
    }

    /**
     * @return the yearExamined
     */
    public Integer getYearExamined()
    {
        return yearExamined;
    }

    /**
     * @param yearExamined
     *            the yearExamined to set
     */
    public void setYearExamined( Integer yearExamined )
    {
        this.yearExamined = yearExamined;
    }

    /**
     * @return the categories
     */
    public List<Category> getCategories()
    {
        return categories;
    }

    /**
     * @param categories
     *            the categories to set
     */
    public void setCategories( List<Category> categories )
    {
        this.categories = categories;
    }

    /**
     * @return the accounts
     */
    public List<Account> getAccounts()
    {
        return accounts;
    }

    /**
     * @param accounts
     *            the accounts to set
     */
    public void setAccounts( List<Account> accounts )
    {
        this.accounts = accounts;
    }

    /**
     * @param riskFreeId
     *            the riskFreeId to set
     */
    public void setRiskFreeId( Integer riskFreeId )
    {
        this.riskFreeId = riskFreeId;
    }

    /**
     * @param compositeTypeId
     *            the compositeTypeId to set
     */
    public void setCompositeTypeId( Integer compositeTypeId )
    {
        this.compositeTypeId = compositeTypeId;
    }

    /**
     * @return the compositeAudits
     */
    public List<CompositeAudit> getReasonCodes()
    {
        return reasonCodes;
    }

    /**
     * @param reasonCodes
     *            the reasonCodes to set
     */
    public void setReasonCodes( List<CompositeAudit> reasonCodes )
    {
        this.reasonCodes = reasonCodes;
    }

    /**
     * @return the benchmarks
     */
    public List<Benchmark> getBenchmarks()
    {
        return benchmarks;
    }

    /**
     * @param benchmarks
     *            the benchmarks to set
     */
    public void setBenchmarks( List<Benchmark> benchmarks )
    {
        this.benchmarks = benchmarks;
    }

    /**
     * @return the disclosures
     */
    public List<Disclosure> getDisclosures()
    {
        return disclosures;
    }

    /**
     * @param disclosures
     *            the disclosures to set
     */
    public void setDisclosures( List<Disclosure> disclosures )
    {
        this.disclosures = disclosures;
    }

    /**
     * @return the descriptions
     */
    public List<Description> getDescriptions()
    {
        return descriptions;
    }

    /**
     * @param descriptions
     *            the descriptions to set
     */
    public void setDescriptions( List<Description> descriptions )
    {
        this.descriptions = descriptions;
    }

    /**
     * @return the fees
     */
    public List<CompositeFee> getFees()
    {
        return fees;
    }

    /**
     * @param fees
     *            the fees to set
     */
    public void setFees( List<CompositeFee> fees )
    {
        this.fees = fees;
    }

    /**
     * @return the notes
     */
    public List<CompositeNote> getNotes()
    {
        return notes;
    }

    /**
     * @param notes
     *            the notes to set
     */
    public void setNotes( List<CompositeNote> notes )
    {
        this.notes = notes;
    }

}
