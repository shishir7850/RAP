/**
 * 
 */
package com.quadratic.rap.cms.composites.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table( name = "accounts" )
public class AccountLKUP
{

    /*
     * @GeneratedValue( strategy = GenerationType.AUTO )
     * 
     * @Column( name = "id" ) private Integer id;
     */

    @Id
    @Column( name = "acc_id" )
    private String accountId;

    @Column( name = "acc_code" )
    private String accountCode;

    /**
     * Reference to accountName
     */
    @Column( name = "acc_name" )
    private String accountName;

    @Column( name = "acc_long_name" )
    private String accountLongName;

    /**
     * Reference to currency
     */
    @OneToOne
    @JoinColumn( name = "curr_id" )
    private Currency currency;

    /**
     * Reference to startDate
     */
    @Column( name = "start_date" )
    private String startDate;

    /**
     * Reference to endDate
     */
    @Column( name = "end_date" )
    private String endDate;

    /**
     * Reference to trackingDate
     */
    @Column( name = "tracking_date" )
    private String trackingDate;

    /**
     * Reference to firmId
     */
    @Column( name = "firm_id" )
    private Integer firmId;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "account" )
    private List<AccountNote> notes;

    /**
     * Reference to reasonCodes
     */
    @OneToOne( cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "account" )
    private AccountAudit accountAudit;

    /**
     * @return the accountId
     */
    public String getAccountId()
    {
        return accountId;
    }

    /**
     * @param accountId
     *            the accountId to set
     */
    public void setAccountId( String accountId )
    {
        this.accountId = accountId;
    }

    /**
     * @return the accountCode
     */
    public String getAccountCode()
    {
        return accountCode;
    }

    /**
     * @param accountCode
     *            the accountCode to set
     */
    public void setAccountCode( String accountCode )
    {
        this.accountCode = accountCode;
    }

    /**
     * @return the accountName
     */
    public String getAccountName()
    {
        return accountName;
    }

    /**
     * @param accountName
     *            the accountName to set
     */
    public void setAccountName( String accountName )
    {
        this.accountName = accountName;
    }

    /**
     * @return the accountLongName
     */
    public String getAccountLongName()
    {
        return accountLongName;
    }

    /**
     * @param accountLongName
     *            the accountLongName to set
     */
    public void setAccountLongName( String accountLongName )
    {
        this.accountLongName = accountLongName;
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
     * @return the startDate
     */
    public String getStartDate()
    {
        return startDate;
    }

    /**
     * @param startDate
     *            the startDate to set
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
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate( String endDate )
    {
        this.endDate = endDate;
    }

    /**
     * @return the trackingDate
     */
    public String getTrackingDate()
    {
        return trackingDate;
    }

    /**
     * @param trackingDate
     *            the trackingDate to set
     */
    public void setTrackingDate( String trackingDate )
    {
        this.trackingDate = trackingDate;
    }

    /**
     * @return the firmId
     */
    public Integer getFirmId()
    {
        return firmId;
    }

    /**
     * @param firmId
     *            the firmId to set
     */
    public void setFirmId( Integer firmId )
    {
        this.firmId = firmId;
    }

    /**
     * @return the notes
     */
    public List<AccountNote> getNotes()
    {
        return notes;
    }

    /**
     * @param notes
     *            the notes to set
     */
    public void setNotes( List<AccountNote> notes )
    {
        this.notes = notes;
    }

    /**
     * @return the accountAudit
     */
    public AccountAudit getAccountAudit()
    {
        return accountAudit;
    }

    /**
     * @param accountAudit
     *            the accountAudit to set
     */
    public void setAccountAudit( AccountAudit accountAudit )
    {
        this.accountAudit = accountAudit;
    }

}
