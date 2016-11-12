/**
 * 
 * AccountNote.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic Software Systems Pvt Ltd. 
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
import javax.persistence.Table;

/**
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table( name = "account_notes" )
public class AccountNote
{
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id" )
    private Integer id;
    
    @Column( name = "account_notes") 
    private String accountNote;
    
    @Column( name = "start_date")
    private String entryDate;
    
    @ManyToOne
    @JoinColumn( name = "acc_id" )
    private AccountLKUP account;

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @return the accountNote
     */
    public String getAccountNote()
    {
        return accountNote;
    }

    /**
     * @param accountNote the accountNote to set
     */
    public void setAccountNote( String accountNote )
    {
        this.accountNote = accountNote;
    }

    /**
     * @return the entryDate
     */
    public String getEntryDate()
    {
        return entryDate;
    }

    /**
     * @param entryDate the entryDate to set
     */
    public void setEntryDate( String entryDate )
    {
        this.entryDate = entryDate;
    }

    /**
     * @return the accountLKUP
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
 
}
