/**
 * 
 * Disclosure.java
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
@Table( name = "COMPOSITE_DISCLOSURES" )
public class Disclosure implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -5140512123501963646L;
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ID" )
    private Integer id;
   
    @OneToOne
    @JoinColumn( name = "DISCLOSURE_ID" )
    private DisclosureLKUP disclosure;
    
    @ManyToOne
    @JoinColumn( name = "COMP_ID" )
    private Composite composite;
    
    @Column( name = "START_DATE" )
    private String disclosureStartDate;
    
    @Column( name = "END_DATE" )
    private String disclosureEndDate;
    
    
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
     * @return the disclosure
     */
    public DisclosureLKUP getDisclosure()
    {
        return disclosure;
    }

    /**
     * @param disclosure the disclosure to set
     */
    public void setDisclosure( DisclosureLKUP disclosure )
    {
        this.disclosure = disclosure;
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
     * @return the disclosureStartDate
     */
    public String getDisclosureStartDate()
    {
        return disclosureStartDate;
    }

    /**
     * @param disclosureStartDate the disclosureStartDate to set
     */
    public void setDisclosureStartDate( String disclosureStartDate )
    {
        this.disclosureStartDate = disclosureStartDate;
    }

    /**
     * @return the disclosureEndDate
     */
    public String getDisclosureEndDate()
    {
        return disclosureEndDate;
    }

    /**
     * @param disclosureEndDate the disclosureEndDate to set
     */
    public void setDisclosureEndDate( String disclosureEndDate )
    {
        this.disclosureEndDate = disclosureEndDate;
    }
    

}
