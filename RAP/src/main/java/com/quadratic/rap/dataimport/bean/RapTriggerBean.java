package com.quadratic.rap.dataimport.bean;

import java.sql.Timestamp;
import java.util.Locale;

import javax.sound.midi.MidiDevice.Info;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.quartz.JobDataMap;
import org.quartz.Trigger;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.quadratic.rap.common.IConstants.EnumImportSheduleStatus;
import com.quadratic.rap.common.IConstants.EnumResponseStatus;
import com.quadratic.rap.common.Response;
import com.quadratic.rap.dataimport.model.DataImportDefinition;
import com.quadratic.rap.dataimport.model.LoadImportSchedule;
import com.quadratic.rap.dataimport.repository.DataImportRepository;
import com.quadratic.rap.dto.DataImportLoadDTO;
import com.quadratic.rap.util.Utils;
import com.quadratic.user.model.User;

public class RapTriggerBean
{
	/**
     * Reference to the logger
     */
    private static final Logger logger = Logger.getLogger( RapTriggerBean.class );
    
	/**
	 * Reference to DataImportRepository
	 */
	private DataImportRepository dataImportRepository;
	
	/**
	 * Reference to SchedulerFactoryBean 
	 */
	private SchedulerFactoryBean schedulerFactoryBean;
	
	/**
	 * Reference to JobDetailFactoryBean 
	 */
	private JobDetailFactoryBean jobDetailFactoryBean;
	
	/**
	 * Reference to Utils 
	 */
	private Utils utils;
	
	
	public RapTriggerBean(
			DataImportRepository dataImportRepository,
			SchedulerFactoryBean schedulerFactoryBean,
			JobDetailFactoryBean jobDetailFactoryBean,
			Utils utils) {
		
		this.dataImportRepository=dataImportRepository;
		this.schedulerFactoryBean=schedulerFactoryBean;
		this.jobDetailFactoryBean=jobDetailFactoryBean;
		this.utils=utils;
	}
	
	/**
	 * It is Creating Trigger when user create schedule for importing a data
	 * @param importDTO
	 * @throws Exception
	 */
	public Response createSimpleTrigger( DataImportLoadDTO importDTO, Locale locale )throws Exception
	{
		Response response = new Response();
		try
		{
			logger.info("trigger initalization started");
			User currentUser = utils.getAuthenticatedUserEx();
			
			LoadImportSchedule loadImportSchedule = importDTO.getLoadImportSchedule();
			loadImportSchedule.setStatus(EnumImportSheduleStatus.ACTIVE);
			
			//fetching Data import definition
			DataImportDefinition dataImportDefinition = 
					dataImportRepository.findByObjectUUID(importDTO.getObjectUUID());
			loadImportSchedule.setDataImportDefinition(dataImportDefinition);
			loadImportSchedule.setEffectiveEndDate(new DateTime());
			loadImportSchedule.setEffectiveStartDate(importDTO.getEffectiveStartDate());
			logger.info(" scheduled date :"+importDTO.getEffectiveStartDate());
			//saving scheduling information into data base
			dataImportRepository.saveOrUpdate(loadImportSchedule);
			
			//passing this trigger schedule data import information to JobDetailBean 
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("IMPORT_ID", dataImportDefinition.getImportId());
			jobDataMap.put("LOCALE", locale);
			jobDataMap.put("CURRENT_USER", currentUser);
			SimpleTriggerFactoryBean ctb = new SimpleTriggerFactoryBean();
			
			DateTime startTime=importDTO.getLoadImportSchedule().getEffectiveStartDate();
			ctb.setStartTime( new Timestamp(startTime.getMillis()));
			ctb.setJobDetail(jobDetailFactoryBean.getObject());
			ctb.setJobDataMap(jobDataMap);
			
			ctb.setRepeatCount(0);
			ctb.setRepeatInterval(0);
			ctb.setName(dataImportDefinition.getImportName()+"_Trigger");
			ctb.afterPropertiesSet();
			
			schedulerFactoryBean.setTriggers(new Trigger[]{ctb.getObject()});	    		
			schedulerFactoryBean.afterPropertiesSet();
			schedulerFactoryBean.start();
			response.setMessage("scheduled on '<b> "+
					importDTO.getLoadImportSchedule().getEffectiveStartDate()+"</b>' this Data Import Successfully..");
			response.setStatus(EnumResponseStatus.OK);
		}
		catch(Exception e)
		{
			response.setMessage("due to some reason scheduling on '<b> "+
					importDTO.getLoadImportSchedule().getEffectiveStartDate()+"</b>' this Data Import is Failed..");
			response.setStatus(EnumResponseStatus.ERROR);
			e.printStackTrace();
			throw e;
		}
		return response;
	}
}
