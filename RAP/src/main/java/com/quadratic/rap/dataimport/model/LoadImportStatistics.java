/**
 * 
 * LoadImportStatistics.java
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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.quadratic.rap.common.IConstants.EnumLoadStatus;
import com.quadratic.rap.common.IConstants.EnumUploadLocation;

/**
 * @author Suneel Ayyaparaju.
 *
 */
@Entity
@Table( name = "LOAD_IMPORT_STATISTICS" )
public class LoadImportStatistics implements Serializable
{

    /**
     * Reference to serialVersionUID
     */
    private static final long serialVersionUID = -8359263552642303351L;

    /**
     * Reference to id
     */
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "ID" )
    private Integer id;

    /**
     * Reference to importId
     */
    @Column( name = "IMPORT_ID" )
    private Integer importId;

    /**
     * Reference to uploadLocation
     */
    @Enumerated( EnumType.STRING )
    @Column( name = "UPLOAD_LOCATION" )
    private EnumUploadLocation uploadLocation;

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

    /**
     * Reference to machineIdentifier
     */
    @Column( name = "MACHINE_IDENTIFIER" )
    private String machineIdentifier;

    /**
     * Reference to loadStartTime
     */
    @Column( name = "LOAD_START_TIME" )
    private DateTime loadStartTime;

    /**
     * Reference to loadEndTime
     */
    @Column( name = "LOAD_END_TIME" )
    private DateTime loadEndTime;

    /**
     * Reference to loadStatus
     */
    @Enumerated( EnumType.STRING )
    @Column( name = "LOAD_STATUS" )
    private EnumLoadStatus loadStatus;

    /**
     * Reference to totalRecordCount
     */
    @Column( name = "TOTAL_REC_CNT" )
    private Integer totalRecordCount;

    /**
     * Reference to loadRecordCount
     */
    @Column( name = "LOAD_REC_CNT" )
    private Integer loadRecordCount;

    /**
     * Reference to statusMessage
     */
    @Column( name = "STATUS_MSG" )
    private String statusMessage;

    /**
     * Reference to createdBy
     */
    @Column( name = "CR_BY" )
    private String createdBy;

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId( Integer id )
    {
        this.id = id;
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
     * @return the uploadLocation
     */
    public EnumUploadLocation getUploadLocation()
    {
        return uploadLocation;
    }

    /**
     * @param uploadLocation
     *            the uploadLocation to set
     */
    public void setUploadLocation( EnumUploadLocation uploadLocation )
    {
        this.uploadLocation = uploadLocation;
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
     * @return the machineIdentifier
     */
    public String getMachineIdentifier()
    {
        return machineIdentifier;
    }

    /**
     * @param machineIdentifier
     *            the machineIdentifier to set
     */
    public void setMachineIdentifier( String machineIdentifier )
    {
        this.machineIdentifier = machineIdentifier;
    }

    /**
     * @return the loadStartTime
     */
    public DateTime getLoadStartTime()
    {
        return loadStartTime;
    }

    /**
     * @param loadStartTime
     *            the loadStartTime to set
     */
    public void setLoadStartTime( DateTime loadStartTime )
    {
        this.loadStartTime = loadStartTime;
    }

    /**
     * @return the loadEndTime
     */
    public DateTime getLoadEndTime()
    {
        return loadEndTime;
    }

    /**
     * @param loadEndTime
     *            the loadEndTime to set
     */
    public void setLoadEndTime( DateTime loadEndTime )
    {
        this.loadEndTime = loadEndTime;
    }

    /**
     * @return the loadStatus
     */
    public EnumLoadStatus getLoadStatus()
    {
        return loadStatus;
    }

    /**
     * @param loadStatus
     *            the loadStatus to set
     */
    public void setLoadStatus( EnumLoadStatus loadStatus )
    {
        this.loadStatus = loadStatus;
    }

    /**
     * @return the totalRecordCount
     */
    public Integer getTotalRecordCount()
    {
        return totalRecordCount;
    }

    /**
     * @param totalRecordCount
     *            the totalRecordCount to set
     */
    public void setTotalRecordCount( Integer totalRecordCount )
    {
        this.totalRecordCount = totalRecordCount;
    }

    /**
     * @return the loadRecordCount
     */
    public Integer getLoadRecordCount()
    {
        return loadRecordCount;
    }

    /**
     * @param loadRecordCount
     *            the loadRecordCount to set
     */
    public void setLoadRecordCount( Integer loadRecordCount )
    {
        this.loadRecordCount = loadRecordCount;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage()
    {
        return statusMessage;
    }

    /**
     * @param statusMessage
     *            the statusMessage to set
     */
    public void setStatusMessage( String statusMessage )
    {
        this.statusMessage = statusMessage;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy()
    {
        return createdBy;
    }

    /**
     * @param createdBy
     *            the createdBy to set
     */
    public void setCreatedBy( String createdBy )
    {
        this.createdBy = createdBy;
    }

}
