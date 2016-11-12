/**
 * 
 * Account.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.cms.composites.model;

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
@Table( name = "COMPOSITE_ACCOUNTS" )
public class Account
{
 
    /**
     * Reference to id
     */
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ID" )
    private Integer id;
    
    /**
     * Reference to component
     */
    @OneToOne
    @JoinColumn( name = "ACCOUNT_ID" )
    private AccountLKUP account;

    /**
     * Reference to accountGeo
     */
    @Column( name = "ACCOUNT_GEO" )
    private Integer accountGeo;

    /**
     * Reference to accountStartDate
     */
    @Column( name = "ACCOUNT_START" )
    private String accountStartDate;

    /**
     * Reference to accountEndDate
     */
    @Column( name = "ACCOUNT_END" )
    private String accountEndDate;

    /**
     * Reference to accountReasonIn
     */
    @Column( name = "REASON_IN" )
    private String accountReasonIn;

    /**
     * Reference to accountReasonOut
     */
    @Column( name = "REASON_OUT" )
    private String accountReasonOut;

    @ManyToOne
    @JoinColumn( name = "COMP_ID" )
    private Composite composite;

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
     * @return the account
     */
    public AccountLKUP getAccount()
    {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount( AccountLKUP account )
    {
        this.account = account;
    }

    /**
     * @return the accountGeo
     */
    public Integer getAccountGeo()
    {
        return accountGeo;
    }

    /**
     * @param accountGeo the accountGeo to set
     */
    public void setAccountGeo( Integer accountGeo )
    {
        this.accountGeo = accountGeo;
    }

    /**
     * @return the accountStartDate
     */
    public String getAccountStartDate()
    {
        return accountStartDate;
    }

    /**
     * @param accountStartDate the accountStartDate to set
     */
    public void setAccountStartDate( String accountStartDate )
    {
        this.accountStartDate = accountStartDate;
    }

    /**
     * @return the accountEndDate
     */
    public String getAccountEndDate()
    {
        return accountEndDate;
    }

    /**
     * @param accountEndDate the accountEndDate to set
     */
    public void setAccountEndDate( String accountEndDate )
    {
        this.accountEndDate = accountEndDate;
    }

    /**
     * @return the accountReasonIn
     */
    public String getAccountReasonIn()
    {
        return accountReasonIn;
    }

    /**
     * @param accountReasonIn the accountReasonIn to set
     */
    public void setAccountReasonIn( String accountReasonIn )
    {
        this.accountReasonIn = accountReasonIn;
    }

    /**
     * @return the accountReasonOut
     */
    public String getAccountReasonOut()
    {
        return accountReasonOut;
    }

    /**
     * @param accountReasonOut the accountReasonOut to set
     */
    public void setAccountReasonOut( String accountReasonOut )
    {
        this.accountReasonOut = accountReasonOut;
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
 
   
}
