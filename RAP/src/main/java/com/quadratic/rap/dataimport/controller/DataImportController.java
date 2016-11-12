/**
 *
 * DataImportController.java
 *
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 *
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 *
 **/
package com.quadratic.rap.dataimport.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quadratic.rap.aspects.Loggable;
import com.quadratic.rap.common.EnumSessionConstants;
import com.quadratic.rap.common.IConstants;
import com.quadratic.rap.common.IConstants.EnumResponseStatus;
import com.quadratic.rap.common.IConstants.EnumSourceType;
import com.quadratic.rap.common.Response;
import com.quadratic.rap.dataimport.bean.RapTriggerBean;
import com.quadratic.rap.dataimport.model.DataImportDefinition;
import com.quadratic.rap.dataimport.model.DataImportMapping;
import com.quadratic.rap.dataimport.model.DataImportStream;
import com.quadratic.rap.dataimport.model.DataImportStreamField;
import com.quadratic.rap.dataimport.model.DateFormat;
import com.quadratic.rap.dataimport.model.LoadImportSchedule;
import com.quadratic.rap.dataimport.model.Package;
import com.quadratic.rap.dataimport.repository.DataImportRepository;
import com.quadratic.rap.dto.DataImportLoadDTO;
import com.quadratic.rap.exceptions.NotAuthenticatedException;
import com.quadratic.rap.exceptions.QSException;
import com.quadratic.rap.util.UrlUtils;
import com.quadratic.rap.util.Utils;
import com.quadratic.user.model.User;
import com.quadratic.user.repository.UserRepository;

/**
 * @author Suneel Ayyaparaju

 */
@Controller
public class DataImportController
{
    /**
     * Reference to the logger
     */
    private static final Logger logger = Logger.getLogger( DataImportController.class );

    /**
     * Reference to the userRepository
     */
    @Autowired
    UserRepository userRepository;

    /**
     * Reference to the dataImportRepository
     */
    @Autowired
    DataImportRepository dataImportRepository;

    /**
     * Reference to messageSource
     */
    @Autowired
    MessageSource messageSource;

    /**
     * Reference to utils
     */
    @Autowired
    Utils utils;

    /**
     * Reference to urlUtils
     */
    @Autowired
    UrlUtils urlUtils;

    @PersistenceContext
    EntityManager entityManager;
    
    /**
   	 * Reference to SchedulerFactoryBean 
   	 */
       @Autowired
   	private SchedulerFactoryBean schedulerFactoryBean;
   	
   	/**
   	 * Reference to JobDetailFactoryBean 
   	 */
       @Autowired
   	private JobDetailFactoryBean jobDetailFactoryBean;
    
    @Loggable
    @RequestMapping( value = "/dataimport/imports/list", method = RequestMethod.GET )
    public String showPackages( HttpServletRequest request, Model model, Locale locale )
    {

        User currentUser = utils.getAuthenticatedUser();
        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }

        try
        {
            List<Package> packages = dataImportRepository.findAllPackages();
            List<DataImportStream> dataImportStreams = dataImportRepository.findAllDataImportStreams();
            List<DataImportStreamField> dataImportStreamFields = dataImportRepository
                    .findDataImportStreamFieldsByStreamId( "PM" );

            DataImportDefinition dataImportDefinition = new DataImportDefinition();
            dataImportDefinition.setObjectUUID( dataImportDefinition.generateUUID() );
            model.addAttribute( "qsDataImportDefinition", dataImportDefinition );
            model.addAttribute( "packages", packages );
            model.addAttribute( "dataImportStreams", dataImportStreams );
            model.addAttribute( "availableColumns", dataImportStreamFields );

        }
        catch ( Exception e )
        {
            DataImportController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
        }

        return "/dataimport/dataimportmanagement :: #dataImportManagement";

    }

    @Loggable
    @RequestMapping( value = "/dataimport/package/create/form", method = RequestMethod.POST )
    public String createPackageForm( String packageId, HttpServletRequest request, Model model, Locale locale )
    {

        User currentUser = utils.getAuthenticatedUser();
        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }
        try
        {
        	if( packageId != null && StringUtils.hasLength(packageId))
        	{
        		Package qsPackage = dataImportRepository.findByPackageId( Integer.parseInt(packageId));
        		model.addAttribute( "qsPackage", qsPackage );
        	}
        	else
        	{
        		Package qsPackage = new Package();
                qsPackage.setObjectUUID( qsPackage.generateUUID() );
                model.addAttribute( "qsPackage", qsPackage );
        	}
        }
        catch ( Exception e )
        {
            DataImportController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
        }

        return "/dataimport/createpackage :: #createPackageFormDialog";

    }

    @Loggable
    @RequestMapping( value = "/dataimport/package/create", method = RequestMethod.POST, produces = "application/json" )
    @ResponseBody
    public Response createPackage(Package qsPackage, BindingResult bindingResult,Model model, Locale locale )
    {
        Response qsResponse = new Response();
        try
        {
            User currentUser = utils.getAuthenticatedUser();

            if ( qsPackage != null && StringUtils.hasLength( qsPackage.getObjectUUID() ) )
            {
                Integer packageId = qsPackage.getPackageId();
                if ( packageId != null )
                {
                    Package currentPackage = dataImportRepository.findByPackageId( packageId );
                    if ( currentPackage == null )
                    {
                        qsResponse.setMessage( messageSource.getMessage( "invalidPackage.MESSAGE",
                                new Object[] { qsPackage.getPackageName() }, locale ) );
                        qsResponse.setStatus( EnumResponseStatus.ERROR );
                        return qsResponse;
                    }
                    currentPackage.setPackageName( qsPackage.getPackageName() );
                    currentPackage.setModifiedBy( currentUser.getUserName() );
                    currentPackage.setLastModifiedDate( DateTime.now() );
                    dataImportRepository.updatePackage( currentPackage );
                    qsResponse.setStatus( EnumResponseStatus.OK );
                    qsResponse.setMessage( messageSource.getMessage( "packageUpdated.MESSAGE",
                            new Object[] { qsPackage.getPackageName() }, locale ) );
                    qsResponse.setCallbackUrl( urlUtils.getBaseUrl() + "dataimport/imports/list" );
                }
                else
                {
                    qsPackage = dataImportRepository.createPackage( qsPackage );
                    qsResponse.setStatus( EnumResponseStatus.OK );
                    qsResponse.setMessage( messageSource.getMessage( "packageCreated.MESSAGE",
                            new Object[] { qsPackage.getPackageName() }, locale ) );
                    qsResponse.setCallbackUrl( urlUtils.getBaseUrl() + "dataimport/imports/list" );
                }

            }

        }
        catch ( Exception e )
        {
            DataImportController.logger.error( "Exception: " + e );
            qsResponse.setStatus( IConstants.EnumResponseStatus.ERROR );
            qsResponse.setMessage( e.getMessage() );
            if ( e.getCause() instanceof ConstraintViolationException )
            {
                qsResponse.setStatus( EnumResponseStatus.ERROR );
                qsResponse.setMessage( messageSource.getMessage( "packageAlreadyExist.MESSAGE",
                        new Object[] { qsPackage.getPackageName() }, locale ) );
            }
            else if ( e instanceof NotAuthenticatedException )
            {
                qsResponse.setCallbackUrl( urlUtils.getBaseUrl() + "auth/login" );
            }
        }
        return qsResponse;
    }

    @ResponseBody
    @RequestMapping( value = "/package/delete", method = RequestMethod.POST )
    public Response deletePackage( Integer packageId , String packageName, Locale locale )
    {
        Response qsResponse = new Response();
        
        try
        {
        	if( packageId != null)
        	{
        		Package qsPackage = dataImportRepository.findByPackageId(packageId);
	            if ( qsPackage != null  )
	            {
	                dataImportRepository.deletePackage( qsPackage.getPackageId() );
	                qsResponse.setMessage( messageSource.getMessage( "packageDeleted.MESSAGE",
	                        new Object[] { qsPackage.getPackageName() }, locale ) );
	                qsResponse.setStatus(EnumResponseStatus.OK);
	            }
	            else
	            {
	                qsResponse.setStatus( EnumResponseStatus.ERROR );
	                qsResponse.setMessage( messageSource.getMessage( "createPackageNameEmpty.MESSAGE", new Object[] {},
	                        locale ) );
	            }
        	}
        	else
        	{
        		 qsResponse.setStatus( EnumResponseStatus.ERROR );
        		 qsResponse.setMessage(messageSource.getMessage( "createPackageNameEmpty.MESSAGE", new Object[] {},
	                        locale ));
        	}
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            qsResponse.setStatus( IConstants.EnumResponseStatus.ERROR );
            qsResponse.setMessage( messageSource.getMessage( "unknownException.MESSAGE", new Object[] { "Deleting",
            		packageName }, locale ) );
            if ( e.getCause() instanceof ConstraintViolationException )
            {
                qsResponse.setStatus( EnumResponseStatus.ERROR );
                qsResponse.setMessage( messageSource.getMessage( "deletePackageHasDataImports.MESSAGE",
                        new Object[] { packageName }, locale ) );
            }
            else if ( e instanceof NotAuthenticatedException )
            {
                qsResponse.setCallbackUrl( urlUtils.getBaseUrl() + "auth/login" );
            }
        }
        return qsResponse;
    }

    @Loggable
    @RequestMapping( value = "/dataimport/import/create", method = RequestMethod.POST, produces = "application/json" )
    @ResponseBody
    public Response createDataImportDefinition( DataImportDefinition qsDataImportDefinition,
            @RequestParam( value = "selectedColumns" ) List<String> selectedColumns, Model model, Locale locale )
    {

        Response qsResponse = new Response();
        Package qsPackage = null;
        try
        {
            if ( qsDataImportDefinition != null && StringUtils.hasLength( qsDataImportDefinition.getObjectUUID() ) )
            {

                if ( qsDataImportDefinition.getDataImportPackage() != null
                        && qsDataImportDefinition.getDataImportPackage().getPackageId() != null )
                {
                    qsPackage = dataImportRepository.findByPackageId( qsDataImportDefinition.getDataImportPackage()
                            .getPackageId() );
                    if ( qsPackage != null )
                    {
                        qsDataImportDefinition.setPackageName( qsPackage.getPackageName() );
                    }
                }

                qsDataImportDefinition = dataImportRepository.createDataImportDefinition( qsDataImportDefinition,
                        selectedColumns );
                qsResponse.setStatus( IConstants.EnumResponseStatus.OK );
                qsResponse.setMessage( messageSource.getMessage( "dataImportCreated.MESSAGE",
                        new Object[] { qsDataImportDefinition.getImportName() }, locale ) );
            }

        }
        catch ( Exception e )
        {
            e.printStackTrace();
            DataImportController.logger.error( "Exception: " + e );
            qsResponse.setStatus( IConstants.EnumResponseStatus.ERROR );

            if ( e.getCause() instanceof ConstraintViolationException )
            {
                qsResponse.setMessage( messageSource.getMessage( "uniqueConstraintException.MESSAGE", new Object[] {
                        "Data Import Name:" + qsDataImportDefinition.getImportName(), "Name" }, locale ) );
                qsResponse.setCallbackUrl( urlUtils.getBaseUrl() + "auth/login" );
            }
            else if ( e instanceof NotAuthenticatedException )
            {
                qsResponse.setCallbackUrl( urlUtils.getBaseUrl() + "auth/login" );
            }
            else
            {
                qsResponse.setMessage( messageSource.getMessage( "unknownException.MESSAGE", new Object[] { "Saving",
                        "Data Import Definition" }, locale ) );
            }

        }
        return qsResponse;
    }

    @Loggable
    @RequestMapping( value = "/dataimport/import/create", method = RequestMethod.GET )
    public String createDataImportDefinitionForm( HttpServletRequest request, Model model, Locale locale )
    {
        User currentUser = utils.getAuthenticatedUser();

        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }
        try
        {
            if ( currentUser != null )
            {
                List<Package> packages = dataImportRepository.findAllPackages();
                List<DataImportStreamField> dataImportStreamFields = dataImportRepository
                        .findDataImportStreamFieldsByStreamId( "PM" );

                DataImportDefinition dataImportDefinition = new DataImportDefinition();
                dataImportDefinition.setObjectUUID( dataImportDefinition.generateUUID() );
                List<DataImportStream> dataImportStreams = dataImportRepository.findAllDataImportStreams();
                model.addAttribute( "packages", packages );
                model.addAttribute( "qsDataImportDefinition", dataImportDefinition );
                model.addAttribute( "dataImportStreams", dataImportStreams );
                model.addAttribute( "availableColumns", dataImportStreamFields );
                model.addAttribute( "actionMessage", EnumSessionConstants.CREATE_DATA_IMPORT_DEFINITION.name() );
                model.addAttribute( "previousState", EnumSessionConstants.ENTERED_INTO_DATA_IMPORT_MANAGEMENT.name() );
            }

        }
        catch ( Exception e )
        {
            DataImportController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
        }

        return "/dataimport/dataimportdefinition :: #dataImportDefinitionContainer";
    }

    @Loggable
    @RequestMapping( value = "/dataimport/import/{dataImportDefinitionObjectUUID}/edit", method = RequestMethod.GET )
    public String editDataImportDefinitionForm( @PathVariable String dataImportDefinitionObjectUUID, Model model,
            Locale locale )
    {
        User currentUser = utils.getAuthenticatedUser();

        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }
        try
        {
            if ( dataImportDefinitionObjectUUID != null && StringUtils.hasLength( dataImportDefinitionObjectUUID ) )
            {
                List<Package> packages = dataImportRepository.findAllPackages();
                List<DataImportStream> dataImportStreams = dataImportRepository.findAllDataImportStreams();
                DataImportDefinition qsDataImportDefinition = dataImportRepository
                        .findByObjectUUID( dataImportDefinitionObjectUUID );
                List<DataImportStreamField> dataImportStreamFields = dataImportRepository
                        .findDataImportStreamFieldsByStreamId( qsDataImportDefinition.getStreamId() );
                List<DataImportMapping> dataImportMappings = qsDataImportDefinition.getMappingFieldDetails();

                //sorting dataImportMappings
                Collections.sort( dataImportMappings, new Comparator<DataImportMapping>()
                {
                    public int compare( DataImportMapping m1, DataImportMapping m2 )
                    {
                        return m1.getSequence().compareTo( m2.getSequence() );
                    }
                } );

                List<DataImportStreamField> availableColumns = new ArrayList<>( 0 );
                List<DataImportStreamField> selectedColumns = new ArrayList<>( 0 );

                for ( DataImportStreamField dataImportStreamField : dataImportStreamFields )
                {

                    boolean isAvailableStreamField = false;
                    for ( DataImportMapping dataImportMapping : dataImportMappings )
                    {
                        isAvailableStreamField = true;
                        int result = dataImportStreamField.getStreamFieldId().compareTo(
                                dataImportMapping.getStreamFieldId() );
                        if ( result == 0 )
                        {
                            selectedColumns.add( dataImportStreamField );
                            isAvailableStreamField = false;
                            break;

                        }
                    }
                    if ( isAvailableStreamField )
                    {
                        availableColumns.add( dataImportStreamField );
                    }
                }
                List<DataImportStreamField> selectedCols = new ArrayList<>( 0 );
                if ( selectedColumns.size() == dataImportMappings.size() )
                {

                    for ( DataImportMapping dataImportMapping : dataImportMappings )
                    {
                        for ( DataImportStreamField dataImportStreamField : dataImportStreamFields )
                        {
                            int result = dataImportMapping.getStreamFieldId().compareTo(
                                    dataImportStreamField.getStreamFieldId() );
                            if ( result == 0 )
                            {
                                selectedCols.add( dataImportStreamField );
                            }
                        }

                    }
                }
                // getting date formats
                EnumSourceType enumSourceType = qsDataImportDefinition.getSourceType();
                List<DateFormat> dateFormats = null;
                if ( EnumSourceType.EXCEL.equals( enumSourceType ) )
                {
                    dateFormats = dataImportRepository.findAllDateFormatsBySourceType( enumSourceType );

                }
                else
                {
                    dateFormats = dataImportRepository.findAllDateFormats();
                }

                // setting formatted Date
                DateFormat qsDateFormat = dataImportRepository.findByDateFormatId( qsDataImportDefinition
                        .getDateFormat().getDateFormatId() );

                String dateFormat = qsDateFormat.getDateFormat();

                DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern( dateFormat );
                String formatDate = dateTimeFormatter.print( new DateTime() );
                model.addAttribute( "formatDate", formatDate );
                model.addAttribute( "dateFormats", dateFormats );
                model.addAttribute( "packages", packages );
                model.addAttribute( "dataImportStreams", dataImportStreams );
                model.addAttribute( "qsDataImportDefinition", qsDataImportDefinition );
                model.addAttribute( "selectedColumns", selectedCols );
                model.addAttribute( "availableColumns", availableColumns );
                model.addAttribute( "actionMessage", EnumSessionConstants.EDIT_DATA_IMPORT_DEFINITION.name() );
                model.addAttribute( "previousState", EnumSessionConstants.ENTERED_INTO_DATA_IMPORT_MANAGEMENT.name() );
            }

        }
        catch ( Exception e )
        {
            DataImportController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
        }

        return "/dataimport/dataimportdefinition :: #dataImportDefinitionContainer";
    }

    @Loggable
    @RequestMapping( value = "/dataimport/import/{dataImportDefinitionObjectUUID}/edit", method = RequestMethod.POST, produces = "application/json" )
    @ResponseBody
    public Response updateDataImportDefinition( DataImportDefinition qsDataImportDefinition,
            @PathVariable String dataImportDefinitionObjectUUID,
            @RequestParam( value = "selectedColumns" ) List<String> selectedColumns, Model model, Locale locale )
    {

        Response qsResponse = new Response();
        Package qsPackage = null;
        try
        {
            if ( qsDataImportDefinition != null && StringUtils.hasLength( qsDataImportDefinition.getObjectUUID() )
                    && dataImportDefinitionObjectUUID != null && StringUtils.hasLength( dataImportDefinitionObjectUUID )
                    && qsDataImportDefinition.getObjectUUID().equals( dataImportDefinitionObjectUUID ) )
            {

                if ( qsDataImportDefinition.getDataImportPackage() != null
                        && qsDataImportDefinition.getDataImportPackage().getPackageId() != null )
                {
                    qsPackage = dataImportRepository.findByPackageId( qsDataImportDefinition.getDataImportPackage()
                            .getPackageId() );
                    if ( qsPackage != null )
                    {
                        qsDataImportDefinition.setPackageName( qsPackage.getPackageName() );
                    }
                }
                qsDataImportDefinition = dataImportRepository.updateDataImportDefinition( qsDataImportDefinition,
                        selectedColumns );
                qsResponse.setStatus( IConstants.EnumResponseStatus.OK );
                qsResponse.setMessage( messageSource.getMessage( "dataImportUpdated.MESSAGE",
                        new Object[] { qsDataImportDefinition.getImportName() }, locale ) );
            }
        }
        catch ( Exception e )
        {
            DataImportController.logger.error( "Exception: " + e );
            qsResponse.setStatus( IConstants.EnumResponseStatus.ERROR );
            qsResponse.setMessage( e.getMessage() );
            if ( e instanceof NotAuthenticatedException )
            {
                qsResponse.setCallbackUrl( urlUtils.getBaseUrl() + "auth/login" );
            }
        }
        return qsResponse;
    }

    @Loggable
    @RequestMapping( value = "/dataimport/import/{dataImportDefinitionObjectUUID}/delete", method = RequestMethod.GET, produces = "application/json" )
    @ResponseBody
    public Response deleteDataImportDefinition( @PathVariable String dataImportDefinitionObjectUUID, Model model,
            Locale locale )
    {
        Response qsResponse = new Response();
        try
        {
            if ( dataImportDefinitionObjectUUID != null && StringUtils.hasLength( dataImportDefinitionObjectUUID ) )
            {
                DataImportDefinition qsDataImportDefinition = dataImportRepository
                        .findByObjectUUID( dataImportDefinitionObjectUUID );
                if ( qsDataImportDefinition != null )
                {
                    dataImportRepository.deleteDataImport( qsDataImportDefinition );
                    qsResponse.setStatus( IConstants.EnumResponseStatus.OK );
                    qsResponse.setMessage( messageSource.getMessage( "dataImportDeleted.MESSAGE",
                            new Object[] { qsDataImportDefinition.getImportName() }, locale ) );
                }

            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            DataImportController.logger.error( "Exception: " + e );
            qsResponse.setStatus( IConstants.EnumResponseStatus.ERROR );
            qsResponse.setMessage( e.getMessage() );
            if ( e instanceof NotAuthenticatedException )
            {
                qsResponse.setCallbackUrl( urlUtils.getBaseUrl() + "auth/login" );
            }
        }
        return qsResponse;
    }

    @Loggable
    @RequestMapping( value = "/dataimport/imports/load/list", method = RequestMethod.GET )
    public String importPackagesList( HttpServletRequest request, Model model, Locale locale )
    {
        User currentUser = utils.getAuthenticatedUser();
        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }

        try
        {
            List<Package> packages = dataImportRepository.findAllPackages();
            List<DataImportStream> dataImportStreams = dataImportRepository.findAllDataImportStreams();
            model.addAttribute( "packages", packages );
            model.addAttribute( "dataImportStreams", dataImportStreams );

        }
        catch ( Exception e )
        {
            DataImportController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
        }

        return "/dataimport/dataimportloadlist :: #fragmentDataImportManagement";

    }

    @Loggable
    @RequestMapping( value = "/dataimport/import/{dataImportObjectUUID}/load", method = RequestMethod.GET )
    public String loadImportForm( @PathVariable String dataImportObjectUUID, Model model, Locale locale, TimeZone timeZone )
    {
    	logger.info("Load importing form is called");
        User currentUser = utils.getAuthenticatedUser();
        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }
        DataImportLoadDTO dataImportLoadDTO = new DataImportLoadDTO();
        try
        {
            if ( dataImportObjectUUID != null && StringUtils.hasLength( dataImportObjectUUID ) )
            {
                DataImportDefinition dataImportDefinition = dataImportRepository
                        .findByObjectUUID( dataImportObjectUUID );
                dataImportLoadDTO.setObjectUUID( dataImportDefinition.getObjectUUID() );
                dataImportLoadDTO.setImportName( dataImportDefinition.getImportName() );
                dataImportLoadDTO.setPackageName( dataImportDefinition.getPackageName() );
                DataImportStream dataImportStream = dataImportRepository.findByStreamId( dataImportDefinition
                        .getStreamId() );
                dataImportLoadDTO.setStreamName( dataImportStream.getStreamName() );
                dataImportLoadDTO.setStreamType( dataImportStream.getStreamType() );
                dataImportLoadDTO.setSourceType( dataImportDefinition.getSourceType() );
                dataImportLoadDTO.setFilePath( dataImportDefinition.getFilePath() );
                dataImportLoadDTO.setFileName( dataImportDefinition.getFileName() );
                model.addAttribute( "dataImportLoadDTO", dataImportLoadDTO );
                
                LoadImportSchedule importSchedule = dataImportRepository.findScheduleByImportId( dataImportDefinition.getImportId() );
                
                logger.info(" databse retrived startDate :"+importSchedule.getEffectiveStartDate());
                DateTime t=importSchedule.getEffectiveStartDate();
                
                DateFormatter dateFormatter =new DateFormatter("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
                
                logger.info("date fromat to convert:yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            	dateFormatter.setTimeZone(timeZone);
            	
            	logger.info("timeZone of request"+timeZone.toString());
            	DateTime startDate = new DateTime(dateFormatter.parse(importSchedule.getEffectiveStartDate().toString(), locale));
            	logger.info("parsed date:"+startDate);
                dataImportLoadDTO.setEffectiveStartDate(startDate);
                logger.info("load Import asigned startDate:"+dataImportLoadDTO.getEffectiveStartDate());
                dataImportLoadDTO.setLoadImportSchedule(importSchedule);
                
            }

        }
        catch ( Exception e )
        {
            DataImportController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
            e.printStackTrace();
        }

        return "/dataimport/loadimport";

    }

    @Loggable
    @RequestMapping( value = "/dataimport/import/{importObjectUUID}/scheduleload", method = RequestMethod.POST )
    @ResponseBody
    public Response scheduleLoadImport( @PathVariable String importObjectUUID, DataImportLoadDTO importDTO, Model model,
            Locale locale )
    {

        @SuppressWarnings( "unused" )
        User currentUser = utils.getAuthenticatedUser();
        Response response = new Response();

        try
        {
            if ( importObjectUUID == null || !StringUtils.hasLength( importObjectUUID ) || importDTO == null
                    || importDTO.getObjectUUID() == null || !StringUtils.hasLength( importDTO.getObjectUUID() ) )
            {
                throw new QSException( messageSource.getMessage( "Invalid_DataImport_Found", null, locale ) );
            }

            if ( importDTO.getStreamType() == null || !StringUtils.hasLength( importDTO.getStreamType() ) )
            {
                throw new QSException( messageSource.getMessage( "Invalid_StreamType_Found", null, locale ) );
            }
            if ( importDTO.getStreamType() != null )
            {
                
            	//create schedule for importing data at certain time
    			RapTriggerBean triggerBean = 
    					new RapTriggerBean(
    							dataImportRepository, 
    							schedulerFactoryBean, 
    							jobDetailFactoryBean,
    							utils);
    			response = triggerBean.createSimpleTrigger(importDTO, locale);
            	
            	if(EnumResponseStatus.ERROR.equals(response.getStatus()))
            	{
            		/*model.addAttribute("response",response);
            		return "result/loadedresults :: #loadedDataErrorsContent";*/
            	}
            }

        }
        catch ( Exception e )
        {
        	e.printStackTrace();
            DataImportController.logger.error( "Exception: " + e );

            if ( e instanceof NotAuthenticatedException )
            {
                response.setCallbackUrl( urlUtils.getBaseUrl() + "auth/login" );
            }
            response.setStatus(EnumResponseStatus.ERROR);
            response.setMessage("Some probleam in scheduling this Data Import plase contact support.");
        }
        return response;

    }
    
    @Loggable
    @ResponseBody
    @RequestMapping( value = "/dataimport/import/{importObjectUUID}/loadnow", method = RequestMethod.POST)
    public Response loadImport( @PathVariable String importObjectUUID, DataImportLoadDTO importDTO, Model model,
            Locale locale)
    {
    	
    	
        User currentUser = utils.getAuthenticatedUser();
        Response response = new Response();

        try
        {
            if ( importObjectUUID == null || !StringUtils.hasLength( importObjectUUID ) || importDTO == null
                    || importDTO.getObjectUUID() == null || !StringUtils.hasLength( importDTO.getObjectUUID() ) )
            {
                throw new QSException( messageSource.getMessage( "Invalid_DataImport_Found", null, locale ) );
            }

            if ( importDTO.getStreamType() == null || !StringUtils.hasLength( importDTO.getStreamType() ) )
            {
                throw new QSException( messageSource.getMessage( "Invalid_StreamType_Found", null, locale ) );
            }
            if ( importDTO.getStreamType() != null )
            {
                /*response = dataImportRepository.loadDataImport( importDTO );*/
            	response = dataImportRepository.loadDataImport(importDTO, locale, currentUser);
            	if(EnumResponseStatus.ERROR.equals(response.getStatus()))
            	{
            		/*model.addAttribute("response",response);
            		return "result/loadedresults :: #loadedDataErrorsContent";*/
            	}
            }

        }
        catch ( Exception e )
        {
        	e.printStackTrace();
            DataImportController.logger.error( "Exception: " + e );

            if ( e instanceof NotAuthenticatedException )
            {
                response.setCallbackUrl( urlUtils.getBaseUrl() + "auth/login" );
            }
        }
        return response;
		
    }
    
    @Loggable
    @RequestMapping( value = "/dataimport/import/{sourceType}/dateformats", method = RequestMethod.GET, produces = "application/json" )
    @ResponseBody
    public List<DateFormat> getDateFormats( @PathVariable( value = "sourceType" ) String sourceType )
    {
        List<DateFormat> dateFormats = null;
        try
        {
            if ( EnumSourceType.EXCEL.name().equals( sourceType ) )
            {
                dateFormats = dataImportRepository
                        .findAllDateFormatsBySourceType( EnumSourceType.valueOf( sourceType ) );
            }
            else
            {
                dateFormats = dataImportRepository.findAllDateFormats();
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
        return dateFormats;
    }

    @Loggable
    @RequestMapping( value = "/dataimport/import/{dateFormatId}/getformatdate", method = RequestMethod.GET, produces = "application/json" )
    @ResponseBody
    public Response getFormatDate( @PathVariable( value = "dateFormatId" ) Integer dateFormatId )
    {
        String formatDate = null;
        Response qsResponse = new Response();
        try
        {
            DateFormat qsDateFormat = dataImportRepository.findByDateFormatId( dateFormatId );
            String dateFormat = qsDateFormat.getDateFormat();

            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern( dateFormat );
            formatDate = dateTimeFormatter.print( new DateTime() );
            qsResponse.setStatus( IConstants.EnumResponseStatus.OK );
            qsResponse.setData( formatDate );

        }
        catch ( Exception e )
        {
            qsResponse.setStatus( IConstants.EnumResponseStatus.ERROR );
            qsResponse.setMessage( e.getMessage() );
            if ( e instanceof NotAuthenticatedException )
            {
                qsResponse.setCallbackUrl( urlUtils.getBaseUrl() + "auth/login" );
            }
        }
        return qsResponse;
    }

    @Loggable
    @RequestMapping( value = "/dataimport/import/{streamId}/streamfields", method = RequestMethod.GET )
    @ResponseBody
    public Response getDataImportStreamFields( @PathVariable( value = "streamId" ) String streamId, Model model,
            Locale locale )
    {
        Response qsResponse = new Response();
        try
        {
            if ( streamId != null )
            {
                List<DataImportStreamField> qsDataImportStreamFields = dataImportRepository
                        .findDataImportStreamFieldsByStreamId( streamId );
                if ( qsDataImportStreamFields != null && !qsDataImportStreamFields.isEmpty() )
                {
                    qsResponse.setStatus( IConstants.EnumResponseStatus.OK );
                    qsResponse.setData( qsDataImportStreamFields );
                }
            }
        }
        catch ( Exception e )
        {
            qsResponse.setStatus( IConstants.EnumResponseStatus.ERROR );
            qsResponse.setMessage( e.getMessage() );
            if ( e instanceof NotAuthenticatedException )
            {
                qsResponse.setCallbackUrl( urlUtils.getBaseUrl() + "auth/login" );
            }
        }

        return qsResponse;

    }

    @ResponseBody
    @RequestMapping( value = "/package/packagename/check" )
    public Response packageAvailabilityCheck( String packageName, Locale locale )
    {
        Response qsResponse = new Response();
        try
        {
            if ( packageName != null && !packageName.isEmpty() )
            {
                Package packageObj = dataImportRepository.findByPackageName( packageName );
                if ( packageObj != null )
                {
                    qsResponse.setStatus( EnumResponseStatus.ERROR );
                    qsResponse.setMessage( messageSource.getMessage( "packageAlreadyExist.MESSAGE",
                            new Object[] { packageName }, locale ) );
                }
                else
                {
                    qsResponse.setStatus( EnumResponseStatus.OK );
                    qsResponse.setMessage( messageSource.getMessage( "createPackageName.MESSAGE",
                            new Object[] { packageName }, locale ) );
                }
            }
            else
            {
                qsResponse.setStatus( EnumResponseStatus.ERROR );
                qsResponse.setMessage( messageSource.getMessage( "createPackageNameEmpty.MESSAGE",
                        new Object[] { packageName }, locale ) );
            }
        }
        catch ( Exception e )
        {
            qsResponse.setStatus( IConstants.EnumResponseStatus.ERROR );
            qsResponse.setMessage( e.getMessage() );
            if ( e instanceof NotAuthenticatedException )
            {
                qsResponse.setCallbackUrl( urlUtils.getBaseUrl() + "auth/login" );
            }
        }
        return qsResponse;
    }
    
    @ResponseBody
    @RequestMapping( value= "/getDataDefinition")
    public List<DataImportDefinition> getDataDefinition()
    {
    	
    	return dataImportRepository.findAll();
    }
}
