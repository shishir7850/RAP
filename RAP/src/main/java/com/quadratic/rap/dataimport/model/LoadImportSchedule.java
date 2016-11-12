package com.quadratic.rap.dataimport.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.quadratic.rap.common.IConstants.EnumImportSheduleStatus;

@Entity
@Table( name = "load_import_schedule" )
public class LoadImportSchedule
{

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column( name = "FILE_PATH" )
	private String filePath;
	
	@Column( name = "FILE_NAME" )
	private String fileName;
	
	@Column( name = "EFF_START_DATE" )
	
	private DateTime effectiveStartDate;
	
	@Column( name= "EFF_END_DATE" )
	private DateTime effectiveEndDate;
	
	@Column( name = "NEXT_RUN_TIME" )
	private DateTime nextRunTime;
	
	private EnumImportSheduleStatus status;
	
	@OneToOne()
	@JoinColumn( name = "IMPORT_ID" )
	private DataImportDefinition dataImportDefinition;
	
	

	/**
	 * @return the id
	 */
	public Integer getId() {
		
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the effectiveEndDate
	 */
	public DateTime getEffectiveEndDate() {
		return effectiveEndDate;
	}

	/**
	 * @param effectiveEndDate the effectiveEndDate to set
	 */
	public void setEffectiveEndDate(DateTime effectiveEndDate) {
		this.effectiveEndDate = effectiveEndDate;
	}

	/**
	 * @return the nextRunTime
	 */
	public DateTime getNextRunTime() {
		return nextRunTime;
	}

	/**
	 * @param nextRunTime the nextRunTime to set
	 */
	public void setNextRunTime(DateTime nextRunTime) {
		this.nextRunTime = nextRunTime;
	}

	/**
	 * @return the status
	 */
	public EnumImportSheduleStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(EnumImportSheduleStatus status) {
		this.status = status;
	}

	/**
	 * @return the dataImportDefinition
	 */
	public DataImportDefinition getDataImportDefinition() {
		return dataImportDefinition;
	}

	/**
	 * @param dataImportDefinition the dataImportDefinition to set
	 */
	public void setDataImportDefinition(DataImportDefinition dataImportDefinition) {
		this.dataImportDefinition = dataImportDefinition;
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
