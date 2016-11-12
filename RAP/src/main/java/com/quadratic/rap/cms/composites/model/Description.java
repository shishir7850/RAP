/**
 * 
 */
package com.quadratic.rap.cms.composites.model;

import java.io.Serializable;

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
@Table( name = "COMPOSITE_DESC" )
public class Description implements Serializable
{

    /**
	 * 
	 */
    private static final long serialVersionUID = -7509403600934724391L;

    /**
     * Reference to id
     */
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ID" )
    private Integer id;

    @ManyToOne
    @JoinColumn( name = "COMP_ID" )
    private Composite composite;

    @Column( name = "COMP_SHORT_DESC" )
    private String compositeShortDesc;

    @Column( name = "COMP_DESC" )
    private String compositeDesc;

    @Column( name = "START_DATE" )
    private String compositeDescStartDate;

    @Column( name = "END_DATE" )
    private String compositeDescEndDate;
    
   
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
     * @return the compositeShortDesc
     */
    public String getCompositeShortDesc()
    {
        return compositeShortDesc;
    }

    /**
     * @param compositeShortDesc
     *            the compositeShortDesc to set
     */
    public void setCompositeShortDesc( String compositeShortDesc )
    {
        this.compositeShortDesc = compositeShortDesc;
    }

    /**
     * @return the compositeDesc
     */
    public String getCompositeDesc()
    {
        return compositeDesc;
    }

    /**
     * @param compositeDesc
     *            the compositeDesc to set
     */
    public void setCompositeDesc( String compositeDesc )
    {
        this.compositeDesc = compositeDesc;
    }

    /**
     * @return the compositeDescStartDate
     */
    public String getCompositeDescStartDate()
    {
        return compositeDescStartDate;
    }

    /**
     * @param compositeDescStartDate
     *            the compositeDescStartDate to set
     */
    public void setCompositeDescStartDate( String compositeDescStartDate )
    {
        this.compositeDescStartDate = compositeDescStartDate;
    }

    /**
     * @return the compositeDescEndDate
     */
    public String getCompositeDescEndDate()
    {
        return compositeDescEndDate;
    }

    /**
     * @param compositeDescEndDate
     *            the compositeDescEndDate to set
     */
    public void setCompositeDescEndDate( String compositeDescEndDate )
    {
        this.compositeDescEndDate = compositeDescEndDate;
    }

}
