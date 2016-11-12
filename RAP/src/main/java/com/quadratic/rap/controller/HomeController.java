/**
 * 
 * HomeController.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;

import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.WebRequest;

import com.quadratic.rap.aspects.Loggable;

/**
 * Controller class that handles the requests for the application home page.
 * 
 * @author Suneel Ayyaparaju
 * 
 */

@Controller
public class HomeController
{

	/*@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;
	
	@Autowired
	private WebApplicationContext applicationContext;
	
	@Autowired
	private MethodInvokingJobDetailFactoryBean jobDetailFactoryBean;
	
	@RequestMapping(value="/scheduleJob",method=RequestMethod.GET)
	@ResponseBody
	public String scheduleJob()throws Exception
	{
		
		
		//MethodInvokingJobDetailFactoryBean jdfb = new MethodInvokingJobDetailFactoryBean();
        jobDetailFactoryBean.setTargetClass(com.quadratics.rap.test.AnotherBean.class);
        jobDetailFactoryBean.setTargetMethod("printAnotherMessage");
        jobDetailFactoryBean.setName("Trial program");
        jobDetailFactoryBean.afterPropertiesSet();
        JobDetail jd = (JobDetail)jobDetailFactoryBean.getObject();
		//jobDetailFactoryBean.afterPropertiesSet();
        SimpleTriggerFactoryBean ctb = new SimpleTriggerFactoryBean();
        //CronTriggerFactoryBean ctb = new CronTriggerFactoryBean();
        ctb.setJobDetail(jobDetailFactoryBean.getObject());
        ctb.setName("Daily cron");
        
        try {
        	//ctb.setCronExpression("10 15 6 * * ? *");
        	
            ctb.setStartTime(DateBuilder.dateOf(19, 0, 0, 8, 4, 2015));
            //ctb.setRepeatInterval(DateBuilder.dateOf(6, 45, 10, 8, 4, 2015).getTime());
            ctb.setRepeatCount(5);
            ctb.setRepeatInterval(5000);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        ctb.afterPropertiesSet();
        
		
        //SchedulerFactoryBean sfb = new SchedulerFactoryBean();
        //schedulerFactoryBean.setJobDetails(new JobDetail[]{(JobDetail)jobDetailFactoryBean.getObject()});
        //SimpleTriggerImpl impl=new SimpleTriggerImpl();
        //impl.setName("simple");
        
        //impl.setStartTime(DateBuilder.dateOf(6, 24, 10, 8, 4, 2015));
        schedulerFactoryBean.setTriggers(new Trigger[]{ctb.getObject()});
        
        schedulerFactoryBean.afterPropertiesSet();
        try {
            schedulerFactoryBean.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
		return "";
	}*/
	
	
    /**
     * Selects the home view to render by returning the view name
     * 
     * @param locale
     * @param model
     * @return view name for the application home page.
     */
    @Loggable
    @RequestMapping( value = "/", method = RequestMethod.GET )
    public String home( WebRequest webRequest, Locale locale, Model model )
    {
        // Commented the below country_code as this introduces a side-effect where the
        // user cannot navigate to the main page (as the moment the user tries
        // to navigate to the main page, it hits this method which further
        // redirects the user to the user's home page

        /*
         * QSUserDetails userDetails = Utils.getPrincipal(); if ( userDetails !=
         * null && userDetails.isAccountNonLocked() ) { User user =
         * userDetails.getCDUser(); if ( user != null && user.isActivated() ) {
         * return "redirect:/user"; } }
         */

        // view name
        return "auth/login";
    }
    
    @RequestMapping( value= "/test")
    @ResponseBody
    public String test(Locale locale)
    {
    	
    	
    	Locale locale2=new Locale("en","US");
    	
    	DateFormatter dateFormatter =new DateFormatter("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
    	DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.TIMEZONE_FIELD, locale2);
    	try {
    		System.out.println(dateFormat.parse("2015-04-15 03:42:00 +00:00"));
			System.out.println(dateFormatter.parse("2015-04-15 03:42:00 +00:00", locale));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "";
    }
 
}
