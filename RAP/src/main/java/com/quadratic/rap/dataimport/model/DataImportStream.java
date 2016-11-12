/**
 * 
 * DataImportStream.java
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
import javax.persistence.Id;
import javax.persistence.Table;

import com.quadratic.rap.common.AbstractEntity;

/**
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table(name = "DATA_IMPORT_STREAMS")
public class DataImportStream extends AbstractEntity {

	/**
	 * Reference to serialVersionUID
	 */
	private static final long serialVersionUID = 8275625059693330365L;

	/**
	 * Reference to objectUUID
	 */
	@Column(name = "OBJECT_UUID")
	private String objectUUID;

	/**
	 * Reference to streamId
	 */
	@Id
	@Column(name = "STREAM_ID")
	private String streamId;

	/**
	 * Reference to streamName
	 */
	@Column(name = "STREAM_NAME")
	private String streamName;

	/**
	 * Reference to streamType
	 */
	@Column(name = "STREAM_TYPE")
	private String streamType;

	/**
	 * Reference to databaseTableName
	 */
	@Column(name = "DB_TABLE")
	private String databaseTableName;

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
	 * @return the streamName
	 */
	public String getStreamName() {
		return streamName;
	}

	/**
	 * @param streamName
	 *            the streamName to set
	 */
	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}

	/**
	 * @return the streamType
	 */
	public String getStreamType() {
		return streamType;
	}

	/**
	 * @param streamType
	 *            the streamType to set
	 */
	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}

	/**
	 * @return the databaseTableName
	 */
	public String getDatabaseTableName() {
		return databaseTableName;
	}

	/**
	 * @param databaseTableName
	 *            the databaseTableName to set
	 */
	public void setDatabaseTableName(String databaseTableName) {
		this.databaseTableName = databaseTableName;
	}

}
