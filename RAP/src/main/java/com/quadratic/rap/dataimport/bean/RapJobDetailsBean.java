package com.quadratic.rap.dataimport.bean;

import java.util.Date;
import java.util.Locale;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.quadratic.rap.dataimport.repository.DataImportRepository;
import com.quadratic.rap.dto.DataImportLoadDTO;
import com.quadratic.user.model.User;

public class RapJobDetailsBean extends QuartzJobBean{

	/**
	 * Reference to {@link DataImportRepository}
	 */
	private DataImportRepository dataImportRepository;
	
	/**
	 * execute the job 
	 */
	@Override
	protected void executeInternal(JobExecutionContext jobContext)
			throws JobExecutionException {
		
		JobDataMap jobDataMap = jobContext.getMergedJobDataMap();
		Locale locale = (Locale) (jobDataMap.containsKey("LOCALE")?jobDataMap.get("locale"):new Locale("en"));
		
		if( !jobDataMap.containsKey("IMPORT_ID") )
		{
			throw new JobExecutionException();
		}
		Integer importId = (Integer) jobDataMap.get("IMPORT_ID");
		User currentUser = (User) jobDataMap.get("CURRENT_USER");
		try 
		{
			System.out.println(new Date());
			DataImportLoadDTO importDTO = dataImportRepository.createDataImport( importId );
			dataImportRepository.loadDataImport( importDTO, locale, currentUser );
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the dataImportRepository
	 */
	public DataImportRepository getDataImportRepository() {
		return dataImportRepository;
	}

	/**
	 * @param dataImportRepository the dataImportRepository to set
	 */
	public void setDataImportRepository(DataImportRepository dataImportRepository) {
		this.dataImportRepository = dataImportRepository;
	}

	
}
