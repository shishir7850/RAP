/**
 * 
 * DataImportStreamField.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.dataimport.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.quadratic.rap.common.AbstractEntity;
import com.quadratic.rap.common.IConstants;
import com.quadratic.rap.dataimport.parser.util.EnumFieldType;

/**
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table(name = "DATA_IMPORT_STREAM_FIELD")
public class DataImportStreamField extends AbstractEntity {

	/**
	 * Reference to serialVersionUID
	 */
	private static final long serialVersionUID = 1482384920523655997L;

	/**
	 * Reference to objectUUID
	 */
	@Column(name = "OBJECT_UUID")
	private String objectUUID;

	/**
	 * Reference to streamFieldId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "STREAM_FIELD_ID")
	private Integer streamFieldId;

	/**
	 * Reference to streamId
	 */
	@Column(name = "STREAM_ID")
	private String streamId;

	/**
	 * Reference to fieldName
	 */
	@Column(name = "FIELD_NAME")
	private String fieldName;

	/**
	 * Reference to fieldType
	 */
	@Column(name = "FIELD_TYPE")
	private String fieldType;
	
	@Column (name = "JAVA_FIELD_TYPE")
	@Enumerated( EnumType.STRING )
	private EnumFieldType JavaFieldType;

	/**
	 * Reference to databaseFieldName
	 */
	@Column(name = "DB_FIELD_NAME")
	private String databaseFieldName;

	/**
	 * Reference to databaseTable
	 */
	@Column(name = "DB_TABLE")
	private String databaseTable;

	/**
	 * Reference to isRequired
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "REQUIRED")
	private IConstants.EnumRequiredStatus isRequired;

	/**
	 * @return the objectUUID
	 */
	public String getObjectUUID() {
		return objectUUID;
	}

	/**
	 * @param objectUUID
	 *            the objectUUID to set
	 */
	public void setObjectUUID(String objectUUID) {
		this.objectUUID = objectUUID;
	}

	/**
	 * @return the streamFieldId
	 */
	public Integer getStreamFieldId() {
		return streamFieldId;
	}

	/**
	 * @param streamFieldId
	 *            the streamFieldId to set
	 */
	public void setStreamFieldId(Integer streamFieldId) {
		this.streamFieldId = streamFieldId;
	}

	/**
	 * @return the streamId
	 */
	public String getStreamId() {
		return streamId;
	}

	/**
	 * @param streamId
	 *            the streamId to set
	 */
	public void setStreamId(String streamId) {
		this.streamId = streamId;
	}

	/**
	 * @return the fieldName
	 */
	public String getFieldName() {
		return fieldName;
	}

	/**
	 * @param fieldName
	 *            the fieldName to set
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	/**
	 * @return the fieldType
	 */
	public String getFieldType() {
		return fieldType;
	}

	/**
	 * @param fieldType
	 *            the fieldType to set
	 */
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	/**
	 * @return the databaseFieldName
	 */
	public String getDatabaseFieldName() {
		return databaseFieldName;
	}

	/**
	 * @param databaseFieldName
	 *            the databaseFieldName to set
	 */
	public void setDatabaseFieldName(String databaseFieldName) {
		this.databaseFieldName = databaseFieldName;
	}

	/**
	 * @return the databaseTable
	 */
	public String getDatabaseTable() {
		return databaseTable;
	}

	/**
	 * @param databaseTable
	 *            the databaseTable to set
	 */
	public void setDatabaseTable(String databaseTable) {
		this.databaseTable = databaseTable;
	}

	/**
	 * @return the isRequired
	 */
	public IConstants.EnumRequiredStatus getIsRequired() {
		return isRequired;
	}

	/**
	 * @param isRequired
	 *            the isRequired to set
	 */
	public void setIsRequired(IConstants.EnumRequiredStatus isRequired) {
		this.isRequired = isRequired;
	}

	/**
	 * @return the javaFieldType
	 */
	public EnumFieldType getJavaFieldType() {
		return JavaFieldType;
	}

	/**
	 * @param javaFieldType the javaFieldType to set
	 */
	public void setJavaFieldType(EnumFieldType javaFieldType) {
		JavaFieldType = javaFieldType;
	}

}
