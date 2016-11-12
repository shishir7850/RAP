/**
 *
 * DataImportLoadDTO.java
 *
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 *
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 *
 **/
package com.quadratic.rap.dto;

import java.io.Serializable;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.quadratic.rap.common.IConstants;
import com.quadratic.rap.common.IConstants.EnumDataImportLoadingType;
import com.quadratic.rap.common.IConstants.EnumLoadStatus;
import com.quadratic.rap.common.IConstants.EnumUploadLocation;
import com.quadratic.rap.dataimport.model.LoadImportSchedule;

/**
 * @author Suneel Ayyaparaju
 * 
 */
public class DataImportLoadDTO implements Serializable
{

    /**
     * Reference to serialVersionUID
     */
    private static final long serialVersionUID = -4285227356996340789L;

    /**
     * Reference to objectUUID
     */
    private String objectUUID;

    /**
     * Reference to fileData
     */
    private CommonsMultipartFile fileData;

    /**
     * Reference to importName
     */
    private String importName;

    /**
     * Reference to packageName
     */
    private String packageName;

    /**
     * Reference to streamName
     */
    private String streamName;
    
    /**
     * Reference to streamType
     */
    private String streamType;

    /**
     * Reference to filePath
     */
    private String filePath;

    /**
     * Reference to fileName
     */
    private String fileName;
   
    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm" )
	private DateTime effectiveStartDate;
    
    /**
     * Reference to uploadLocation
     */
    private EnumUploadLocation uploadLocation;

    /**
     * Reference to sourceType
     */
    @Enumerated( EnumType.STRING )
    private IConstants.EnumSourceType sourceType;
    
    /**
     * Reference to EnumDataImportLoadingType
     */
    @Enumerated( EnumType.STRING )
    private EnumDataImportLoadingType importLoadingType;
    
    /**
     * Reference to LoadImportSchedule
     */
    private LoadImportSchedule loadImportSchedule;

    /**
     * @return fileData
     */
    public CommonsMultipartFile getFileData()
    {
        return fileData;
    }

    /**
     * @param fileData
     *            fileData to set
     */
    public void setFileData( CommonsMultipartFile fileData )
    {
        this.fileData = fileData;
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
     * @return the streamName
     */
    public String getStreamName()
    {
        return streamName;
    }

    /**
     * @param streamName
     *            the streamName to set
     */
    public void setStreamName( String streamName )
    {
        this.streamName = streamName;
    }
    

    /**
     * @return the streamType
     */
    public String getStreamType()
    {
        return streamType;
    }

    /**
     * @param streamType the streamType to set
     */
    public void setStreamType( String streamType )
    {
        this.streamType = streamType;
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
	 * @return the loadImportSchedule
	 */
	public LoadImportSchedule getLoadImportSchedule() {
		return loadImportSchedule;
	}

	/**
	 * @param loadImportSchedule the loadImportSchedule to set
	 */
	public void setLoadImportSchedule(LoadImportSchedule loadImportSchedule) {
		this.loadImportSchedule = loadImportSchedule;
	}

	/**
	 * @return the importLoadingType
	 */
	public EnumDataImportLoadingType getImportLoadingType() {
		return importLoadingType;
	}

	/**
	 * @param importLoadingType the importLoadingType to set
	 */
	public void setImportLoadingType(EnumDataImportLoadingType importLoadingType) {
		this.importLoadingType = importLoadingType;
	}

	/**
	 * @return the effectiveStartDate
	 */
	public DateTime getEffectiveStartDate() {
		return effectiveStartDate;
	}

	/**
	 * @param effectiveStartDate the effectiveStartDate to set
	 */
	public void setEffectiveStartDate(DateTime effectiveStartDate) {
		this.effectiveStartDate = effectiveStartDate;
	}

}
