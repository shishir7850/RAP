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
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table( name = "COMPOSITE_CATEGORY" )
public class Category implements Serializable
{

    /**
     * Reference to serialVersionUID
     */
    private static final long serialVersionUID = 1643501259636458353L;

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

    @OneToOne
    @JoinColumn( name = "CATG_ID" )
    private CategoryLKUP category;

    @Column( name = "START_DATE" )
    private String categoryStartDate;

    @Column( name = "END_DATE" )
    private String categoryEndDate;
    
    
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
     * @return the categoryId
     */
    public CategoryLKUP getCategory()
    {
        return category;
    }

    /**
     * @param category
     *            the category to set
     */
    public void setCategory( CategoryLKUP category )
    {
        this.category = category;
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
     * @return the categoryStartDate
     */
    public String getCategoryStartDate()
    {
        return categoryStartDate;
    }

    /**
     * @param categoryStartDate
     *            the categoryStartDate to set
     */
    public void setCategoryStartDate( String categoryStartDate )
    {
        this.categoryStartDate = categoryStartDate;
    }

    /**
     * @return the categoryEndDate
     */
    public String getCategoryEndDate()
    {
        return categoryEndDate;
    }

    /**
     * @param categoryEndDate
     *            the categoryEndDate to set
     */
    public void setCategoryEndDate( String categoryEndDate )
    {
        this.categoryEndDate = categoryEndDate;
    }

}
