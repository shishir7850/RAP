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
import javax.persistence.Table;

/**
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table( name = "CATEGORIES" )
public class CategoryLKUP implements Serializable
{

    /**
     * Reference to serialVersionUID
     */
    private static final long serialVersionUID = -1095610649805907423L;
    
    
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ID" )
    private Integer id;
    
    @Id
    @Column( name = "CATG_ID" )
    private Integer categoryId;
    
    @Column( name = "CATG_NAME")
    private String categoryName;
    
    
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
    public Integer getCategoryId()
    {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId( Integer categoryId )
    {
        this.categoryId = categoryId;
    }

    /**
     * @return the categoryName
     */
    public String getCategoryName()
    {
        return categoryName;
    }

    /**
     * @param categoryName the categoryName to set
     */
    public void setCategoryName( String categoryName )
    {
        this.categoryName = categoryName;
    }

}
