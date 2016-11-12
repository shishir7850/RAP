/**
 * 
 * DataImportMapping.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.dataimport.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Entity class for Data Import Mapping (DataImportMapping)
 * 
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table( name = "DATA_IMPORT_MAPPING", uniqueConstraints =  @UniqueConstraint( columnNames = { "IMPORT_ID",
        "IMPORT_ID" } ))
public class DataImportMapping implements Serializable
{
    /**
     * Reference to serialVersionUID
     */
    private static final long serialVersionUID = 4259766089372321981L;

    /**
     * Reference to streamFieldId
     */
    @Id
    @Column( name = "STREAM_FIELD_ID", nullable = false )
    private Integer streamFieldId;
    
    @ManyToOne()
    @JoinColumn( name = "STREAM_FIELD_ID", insertable=false,updatable=false)
    private DataImportStreamField streamField;

    /**
     * Reference to sequence
     */

    @Column( name = "SEQUENCE", nullable = false )
    private Integer sequence;

    /**
     * Reference to dataImportDefinition
     */

    @ManyToOne
    @JoinColumn( name = "IMPORT_ID", nullable = false )
    private DataImportDefinition dataImportDefinition;

    /**
     * Default constructor
     */
    public DataImportMapping()
    {

    }

    /**
     * @return the streamFieldId
     */
    public Integer getStreamFieldId()
    {
        return streamFieldId;
    }

    /**
     * @param streamFieldId
     *            the streamFieldId to set
     */
    public void setStreamFieldId( Integer streamFieldId )
    {
        this.streamFieldId = streamFieldId;
    }

    /**
     * @return the sequence
     */
    public Integer getSequence()
    {
        return sequence;
    }

    /**
     * @param sequence
     *            the sequence to set
     */
    public void setSequence( Integer sequence )
    {
        this.sequence = sequence;
    }

    /**
     * @return the dataImportDefinition
     */

    public DataImportDefinition getDataImportDefinition()
    {
        return dataImportDefinition;
    }

    /**
     * @param dataImportDefinition
     *            the dataImportDefinition to set
     */
    public void setDataImportDefinition( DataImportDefinition dataImportDefinition )
    {
        this.dataImportDefinition = dataImportDefinition;
    }

    /**
	 * @return the streamField
	 */
	public DataImportStreamField getStreamField() {
		return streamField;
	}

	/**
	 * @param streamField the streamField to set
	 */
	public void setStreamField(DataImportStreamField streamField) {
		this.streamField = streamField;
	}

	@Override
    public int hashCode()
    {
        return UUID.randomUUID().hashCode();
    }

    @Override
    public boolean equals( final Object obj )
    {
        if ( obj == null )
            return false;

        if ( !( obj instanceof DataImportMapping ) )
            return false;

        DataImportMapping other = (DataImportMapping) obj;
        if ( !( getStreamFieldId().equals( other.getStreamFieldId() ) ) )
            return false;

        return true;
    }

}
