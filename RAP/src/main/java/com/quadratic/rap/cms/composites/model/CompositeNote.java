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
@Table( name = "COMP_NOTES" )
public class CompositeNote implements Serializable
{
    /**
	 * 
	 */
    private static final long serialVersionUID = 6622520336660918261L;
    
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ID" )
    private Integer id;
    
    @Column( name = "COMP_NOTES") 
    private String compositeNote;
    
    @Column( name = "START_DATE")
    private String entryDate;
    
    @ManyToOne
    @JoinColumn( name = "COMP_ID" )
    private Composite composite;
    

    /**
     * @return the compositeNote
     */
    public String getCompositeNote()
    {
        return compositeNote;
    }

    /**
     * @param compositeNote
     *            the compositeNote to set
     */
    public void setCompositeNote( String compositeNote )
    {
        this.compositeNote = compositeNote;
    }

    /**
     * @return the entryDate
     */
    public String getEntryDate()
    {
        return entryDate;
    }

    /**
     * @param entryDate
     *            the entryDate to set
     */
    public void setEntryDate( String entryDate )
    {
        this.entryDate = entryDate;
    }

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
     * @param composite the composite to set
     */
    public void setComposite( Composite composite )
    {
        this.composite = composite;
    }
 
}
