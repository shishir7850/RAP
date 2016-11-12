/**
 * 
 * DataImportDefinition.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.dataimport.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.quadratic.rap.common.AbstractEntity;
import com.quadratic.rap.common.IConstants;

/**
 * Entity class for Data Import Definition (DataImportDefinition)
 * 
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table( name = "DATA_IMPORT_DEFINITION" )
public class DataImportDefinition extends AbstractEntity
{

    /**
     * Reference to serialVersionUID
     */
    private static final long serialVersionUID = 57105724564319381L;

    /**
     * Reference to objectUUID
     */
    @Column( name = "OBJECT_UUID" )
    private String objectUUID;

    /**
     * Reference to importId
     */
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "IMPORT_ID" )
    private Integer importId;

    /**
     * Reference to importName
     */
    @Column( name = "IMPORT_NAME" )
    private String importName;

    /**
     * Reference to streamId
     */
    @Column( name = "STREAM_ID" )
    private String streamId;

    /**
     * Reference to packageName
     */
    @Column( name = "PACKAGE_NAME" )
    private String packageName;

    /**
     * Reference to sourceType
     */
    @Enumerated( EnumType.STRING )
    @Column( name = "SOURCE_TYPE" )
    private IConstants.EnumSourceType sourceType;

    /**
     * Reference to columnSeparator
     */
    @Enumerated( EnumType.STRING )
    @Column( name = "COLUMN_SEPARATOR" )
    private IConstants.EnumColumnSeparator columnSeparator;

    /**
     * Reference to decimalSeparator
     */
    @Enumerated( EnumType.STRING )
    @Column( name = "DECIMAL_SEPARATOR" )
    private IConstants.EnumDecimalSeparator decimalSeparator;

    /**
     * Reference to numberOfHeaderLines
     */
    @Column( name = "HEADER_LINES" )
    private Integer numberOfHeaderLines;

    /**
     * Reference to numberOfFooterLines
     */
    @Column( name = "FOOTER_LINES" )
    private Integer numberOfFooterLines;

    /**
     * Reference to filePath
     */
    @Column( name = "FILE_PATH" )
    private String filePath;

    /**
     * Reference to fileName
     */
    @Column( name = "FILE_NAME" )
    private String fileName;

    @OneToMany( cascade = CascadeType.ALL,  fetch = FetchType.EAGER, orphanRemoval = true, mappedBy="dataImportDefinition" )
    private List<DataImportMapping> mappingFieldDetails;

    /**
     * Reference to dataImportDefinition
     */

    @ManyToOne
    @JoinColumn( name = "PACKAGE_ID" )
    private Package dataImportPackage;

    /**
     * Reference to dateFormat
     */
    @ManyToOne
    @JoinColumn( name = "DATE_FORMAT_ID" )
    private DateFormat dateFormat;
    
    @OneToOne( mappedBy = "dataImportDefinition",cascade=CascadeType.ALL )
    private LoadImportSchedule loadImportSchedule;

    /**
     * @return the objectUUID
     */
    public String getObjectUUID()
    {
        return objectUUID;
    }

    /**
     * @param objectUUID
     *            the objectUUID to set
     */
    public void setObjectUUID( String objectUUID )
    {
        this.objectUUID = objectUUID;
    }

    /**
     * @return the importId
     */
    public Integer getImportId()
    {
        return importId;
    }

    /**
     * @param importId
     *            the importId to set
     */
    public void setImportId( Integer importId )
    {
        this.importId = importId;
    }

    /**
     * @return the streamId
     */
    public String getStreamId()
    {
        return streamId;
    }

    /**
     * @param streamId
     *            the streamId to set
     */
    public void setStreamId( String streamId )
    {
        this.streamId = streamId;
    }

    /**
     * @return the importName
     */
    public String getImportName()
    {
        return importName;
    }

    /**
     * @param importName
     *            the importName to set
     */
    public void setImportName( String importName )
    {
        this.importName = importName;
    }

    /**
     * @return the packageName
     */
    public String getPackageName()
    {
        return packageName;
    }

    /**
     * @param packageName
     *            the packageName to set
     */
    public void setPackageName( String packageName )
    {
        this.packageName = packageName;
    }

    /**
     * @return the sourceType
     */
    public IConstants.EnumSourceType getSourceType()
    {
        return sourceType;
    }

    /**
     * @param sourceType
     *            the sourceType to set
     */
    public void setSourceType( IConstants.EnumSourceType sourceType )
    {
        this.sourceType = sourceType;
    }

    /**
     * @return the columnSeparator
     */
    public IConstants.EnumColumnSeparator getColumnSeparator()
    {
        return columnSeparator;
    }

    /**
     * @param columnSeparator
     *            the columnSeparator to set
     */
    public void setColumnSeparator( IConstants.EnumColumnSeparator columnSeparator )
    {
        this.columnSeparator = columnSeparator;
    }

    /**
     * @return the decimalSeparator
     */
    public IConstants.EnumDecimalSeparator getDecimalSeparator()
    {
        return decimalSeparator;
    }

    /**
     * @param decimalSeparator
     *            the decimalSeparator to set
     */
    public void setDecimalSeparator( IConstants.EnumDecimalSeparator decimalSeparator )
    {
        this.decimalSeparator = decimalSeparator;
    }

    /**
     * @return the numberOfHeaderLines
     */
    public Integer getNumberOfHeaderLines()
    {
        return numberOfHeaderLines;
    }

    /**
     * @param numberOfHeaderLines
     *            the numberOfHeaderLines to set
     */
    public void setNumberOfHeaderLines( Integer numberOfHeaderLines )
    {
        this.numberOfHeaderLines = numberOfHeaderLines;
    }

    /**
     * @return the numberOfFooterLines
     */
    public Integer getNumberOfFooterLines()
    {
        return numberOfFooterLines;
    }

    /**
     * @param numberOfFooterLines
     *            the numberOfFooterLines to set
     */
    public void setNumberOfFooterLines( Integer numberOfFooterLines )
    {
        this.numberOfFooterLines = numberOfFooterLines;
    }

    /**
     * @return the filePath
     */
    public String getFilePath()
    {
        return filePath;
    }

    /**
     * @param filePath
     *            the filePath to set
     */
    public void setFilePath( String filePath )
    {
        this.filePath = filePath;
    }

    /**
     * @return the fileName
     */
    public String getFileName()
    {
        return fileName;
    }

    /**
     * @param fileName
     *            the fileName to set
     */
    public void setFileName( String fileName )
    {
        this.fileName = fileName;
    }

    /**
     * @return the mappingFieldDetails
     */

    public List<DataImportMapping> getMappingFieldDetails()
    {
        return mappingFieldDetails;
    }

    /**
     * @param mappingFieldDetails
     *            the mappingFieldDetails to set
     */
    public void setMappingFieldDetails( List<DataImportMapping> mappingFieldDetails )
    {
        this.mappingFieldDetails = mappingFieldDetails;
    }

    public Package getDataImportPackage()
    {
        return dataImportPackage;
    }

    public void setDataImportPackage( Package dataImportPackage )
    {
        this.dataImportPackage = dataImportPackage;
    }

    /**
     * @return the dateFormat
     */
    public DateFormat getDateFormat()
    {
        return dateFormat;
    }

    /**
     * @param dateFormat
     *            the dateFormat to set
     */
    public void setDateFormat( DateFormat dateFormat )
    {
        this.dateFormat = dateFormat;
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

        if ( !( obj instanceof DataImportDefinition ) )
            return false;

        DataImportDefinition other = (DataImportDefinition) obj;
        if ( !( getImportId().equals( other.getImportId() ) ) )
            return false;

        return true;
    }

}
