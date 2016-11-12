package com.quadratic.rap.dataimport.bean;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.quadratic.rap.common.IConstants.EnumLoadStatus;
import com.quadratic.rap.common.IConstants.EnumResponseStatus;
import com.quadratic.rap.common.IConstants.EnumSourceType;
import com.quadratic.rap.common.IConstants.EnumUploadLocation;
import com.quadratic.rap.common.Response;
import com.quadratic.rap.dataimport.model.DataImportDefinition;
import com.quadratic.rap.dataimport.model.DataImportMapping;
import com.quadratic.rap.dataimport.model.DataImportStream;
import com.quadratic.rap.dataimport.model.DataImportStreamField;
import com.quadratic.rap.dataimport.model.LoadImportSchedule;
import com.quadratic.rap.dataimport.model.LoadImportStatistics;
import com.quadratic.rap.dataimport.parser.CSVLoader;
import com.quadratic.rap.dataimport.parser.FileValidatorService;
import com.quadratic.rap.dataimport.parser.util.Field;
import com.quadratic.rap.dataimport.repository.DataImportRepository;
import com.quadratic.rap.dataimport.repository.impl.DataImportRepositoryImpl;
import com.quadratic.rap.dto.DataImportLoadDTO;
import com.quadratic.rap.exceptions.QSException;
import com.quadratic.rap.util.UrlUtils;
import com.quadratic.rap.util.Utils;
import com.quadratic.user.model.User;
import com.quadratic.user.repository.UserRepository;

public class LoadDataImport 
{

	/**
     * Reference to the logger
     */
    private static final Logger logger = Logger.getLogger( LoadDataImport.class );
    
	/**
     * Reference to utils
     */
    @Autowired
    Utils utils;
    
    /**
     * Reference to messageSource
     */
    @Autowired
    MessageSource messageSource;
    
    /**
     * Reference to the dataImportRepository
     */
    @Autowired
    DataImportRepository dataImportRepository;
    
    /**
     * Reference to the FileValidatorService
     */
    @Autowired
    FileValidatorService fileValidatorService;
    
    /**
     * Reference to the userRepository
     */
    @Autowired
    UserRepository userRepository;
    
    /**
     * Reference to jdbcTemplate
     */
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    /**
     * Reference to urlUtils
     */
    @Autowired
    UrlUtils urlUtils;
        
    @Transactional
    public Response loadDataImport(DataImportLoadDTO importDTO, Locale locale) throws Exception
    {
    	   	
    	User currentUser = utils.getAuthenticatedUserEx();
        Response response = new Response();
        
        LoadImportStatistics importStats = new LoadImportStatistics();
        
        if ( importDTO == null || importDTO.getObjectUUID() == null )
        {
        	logger.error( DataImportRepositoryImpl.class+ ": called a Data Import loadDataImport() it contains null import Data" );
        	
            throw new QSException( messageSource.getMessage( "Invalid_DataImport_Found", null, null ) );
        }
        
        logger.info(DataImportRepositoryImpl.class+ ": called a data import loadDataImport with Import Name:"+importDTO.getImportName() );
        
        String message = null;
        int count = 0;
        DataImportDefinition importDef = dataImportRepository.findByObjectUUID( importDTO.getObjectUUID() );
        String localFilename = null;
        String serverFileAddress = null;
        String serverInProcessFileAddress = null;
        String serverFilePath = importDTO.getFilePath();
        String serverFileName = importDTO.getFileName();
        BufferedReader br = null;
        InputStream is = null;
        InputStream isCopy = null;
        
        try
        {
            if ( importDef == null )
            {
                throw new QSException( messageSource.getMessage( "Invalid_DataImport_Found", null, null ) );
            }
            
            List<DataImportMapping> importMappings = importDef.getMappingFieldDetails();
            
            DataImportStream importStream = dataImportRepository.findByStreamId( importDef.getStreamId() );
            String tableName = null;
            if ( importStream != null )
            {
                tableName = importStream.getDatabaseTableName();
            }

            StringBuilder dbColumns = new StringBuilder();

            List<String> dbCols = new ArrayList<>();

            StringBuilder dbColumnsForUpdate = new StringBuilder();

            // sorting dataImportMappings
            Collections.sort( importMappings, new Comparator<DataImportMapping>()
            {
                public int compare( DataImportMapping m1, DataImportMapping m2 )
                {
                    return m1.getSequence().compareTo( m2.getSequence() );
                }
            } );
            
            
            CommonsMultipartFile fileData = importDTO.getFileData();

            if ( fileData != null )
            {
                localFilename = fileData.getOriginalFilename();
            }

            // Store Load Statistics
            importStats.setImportId( importDef.getImportId() );
            importStats.setUploadLocation( importDTO.getUploadLocation() );
            importStats.setLoadStartTime( DateTime.now() );
            importStats.setLoadStatus( EnumLoadStatus.INIT );
            importStats.setCreatedBy( currentUser.getUserName() );

            if ( localFilename != null && StringUtils.hasLength( localFilename )
                    && importDTO.getUploadLocation().equals( EnumUploadLocation.FROM_LOCAL ) )
            {
                is = fileData.getInputStream();
                isCopy = fileData.getInputStream();
                serverFileName = localFilename;
                
                br = new BufferedReader( new InputStreamReader( isCopy, "UTF-8" ) );
                importStats.setFileName( localFilename );
                response = fileValidatorService.validateCsvFile(importStats, importMappings, importDef, locale, is);
                
            }
            else if ( serverFilePath != null && StringUtils.hasLength( serverFilePath ) && serverFileName != null
                    && StringUtils.hasLength( serverFileName )
                    && importDTO.getUploadLocation().equals( EnumUploadLocation.FROM_SERVER ) )
            {
                serverFileAddress = serverFilePath.endsWith( "/" ) ? serverFilePath + serverFileName : serverFilePath
                        + "/" + serverFileName;
                serverInProcessFileAddress = serverFilePath.endsWith( "/" ) ? serverFilePath + "inprocess/"
                        + serverFileName : serverFilePath + "/inprocess/" + serverFileName;
                File file = new File( serverInProcessFileAddress );
                file.getParentFile().mkdir();
                file.createNewFile();
                OutputStream outputStream = new FileOutputStream( file );
                FileUtils.copyFile( new File( serverFileAddress ), outputStream );
                outputStream.close();
                is = new FileInputStream(new File(serverInProcessFileAddress));
                isCopy = new FileInputStream(new File(serverInProcessFileAddress));
                br = new BufferedReader(new InputStreamReader(isCopy));
                importStats.setFilePath( serverFilePath );
                importStats.setFileName( serverFileName );
                response = fileValidatorService.validateCsvFile(importStats, importMappings, importDef, locale, is);
                is.close();
            }
            
            
           
            
            if( EnumResponseStatus.ERROR.equals(response.getStatus()))
            {
            	// File data contain with errors. So Return Errors
	        	return response;
            }
            
            //save Load Data Import Statistics info
            importStats = userRepository.saveOrUpdateImportStatics(importStats);
            
            String queryForPrimaryKey = "SELECT column_name as primarykeycolumn "
                    + "FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS AS TC INNER JOIN "
                    + "INFORMATION_SCHEMA.KEY_COLUMN_USAGE AS KU " + "ON TC.CONSTRAINT_TYPE = 'PRIMARY KEY' AND "
                    + "TC.CONSTRAINT_NAME = KU.CONSTRAINT_NAME and KU.table_name= '" + tableName + "'";

            List<String> pkColumns = jdbcTemplate.queryForList( queryForPrimaryKey, String.class );

            Map<String, Object> primaryKeys = new LinkedHashMap<>();

            if ( importMappings != null && !importMappings.isEmpty() )
            {
                for ( int i = 0; i < importMappings.size(); i++ )
                {
                    DataImportMapping importMapping = importMappings.get( i );
                    DataImportStreamField streamField = dataImportRepository.findByStreamFieldId( importMapping
                            .getStreamFieldId() );
                    String dbFieldName = streamField.getDatabaseFieldName();
                    dbColumns.append( dbFieldName );
                    dbCols.add( dbFieldName );

                    if ( !pkColumns.isEmpty() )
                    {
                        for ( String pkColumn : pkColumns )
                        {
                            if ( dbFieldName.equals( pkColumn ) )
                            {
                                primaryKeys.put( pkColumn, i );
                            }
                        }

                    }
                    dbColumns.append( "," );
                }
                dbColumns.append( "CR_BY," );
                dbColumns.append( "DT_CR" );

            }

            for ( String dbCol : dbCols )
            {
                if ( !primaryKeys.containsKey( dbCol ) )
                {
                    dbColumnsForUpdate.append( " target." + dbCol + " = src." + dbCol + "," );
                }
            }
            dbColumnsForUpdate.append( " target.MOD_BY = '" + currentUser.getUserName() + "'," );
            dbColumnsForUpdate.append( " target.DT_MOD = '" + DateTime.now().toString() + "' ;" );

            StringBuilder insertQuery = new StringBuilder();
            StringBuilder deleteQuery = new StringBuilder();
            StringBuilder updateQuery = new StringBuilder();

            String[] dbColumnsArray = null;
            if ( dbColumns != null )
            {
                dbColumnsArray = org.apache.commons.lang.StringUtils.split( dbColumns.toString(), "," );
            }

            // insert query construction
            if ( dbColumnsArray != null && dbColumnsArray.length > 0 )
            {
                insertQuery.append( " insert (" + dbColumns + ") values (" );
                for ( int i = 0; i < dbColumnsArray.length; i++ )
                {
                    if ( dbColumnsArray.length > 1 && ( dbColumnsArray.length - 1 ) != i )
                    {
                        insertQuery.append( " src." + dbColumnsArray[i] + "," );
                    }
                    else
                    {
                        insertQuery.append( " src." + dbColumnsArray[i] );
                    }
                }
                insertQuery.append( ")" );
            }

            // update query construction
            updateQuery.append( " update set " + dbColumnsForUpdate );

            String queryCondition = "";

            // select and delete query construction
            if ( primaryKeys != null && !primaryKeys.isEmpty() )
            {

                deleteQuery.append( "delete where " );
                int index = 0;
                for ( Map.Entry<String, Object> primaryKey : primaryKeys.entrySet() )
                {
                    deleteQuery.append( " target." + primaryKey.getKey() + "= src." + primaryKey.getKey() );
                    queryCondition += " target." + primaryKey.getKey() + " = src." + primaryKey.getKey();
                    if ( ( primaryKeys.size() - 1 ) != index )
                    {
                        deleteQuery.append( " and " );
                        queryCondition += " and ";
                    }
                    index++;
                }
            }

            if ( importDef.getSourceType().equals( EnumSourceType.FLAT_FILES_CSV ) && serverFileName.endsWith( ".csv" ) )
            {
                String delimStr = importDef.getColumnSeparator().name();
                String delim = ",";
                switch ( delimStr )
                {
                    case "COMMA":
                        delim = ",";
                        break;
                    case "SEMICOLON":
                        delim = ";";
                        break;
                    case "PIPE":
                        delim = "|";
                        break;
                }
                
                // Create CSVLoader object
                CSVLoader loader = new CSVLoader( jdbcTemplate );
                // set delimiter which is getting from import definition
                loader.setSeparator( delim );
                // set no. of header lines which is getting from import
                // definition
                loader.setHeaderLines( importDef.getNumberOfHeaderLines() );
                // set no. of footer lines which is getting from import
                // definition
                loader.setFooterLines( importDef.getNumberOfFooterLines() );
                // set loadedBy as current loggedin user
                loader.setLoadedBy( currentUser.getUserName() );
                
                List<Field> fields = fileValidatorService.importFields(importMappings, importDef);
                loader.setFields(fields);
                
                count = loader.loadCSV( br, tableName + "_STAG", dbColumns.toString(), true,importDef.getDateFormat().getDateFormat() );
                this.jdbcTemplate = loader.getJdbcTemplate();
                
                // checking whether the import stream type is STANDARD or
                // TRANSACTONAL
                if ( importStream.getStreamType().equals( "STANDARD" ) )
                {
                    // update if exists else insert (merge statement)
                    String updateInsertQuery = " MERGE " + tableName + " AS target " + " USING " + tableName
                            + "_STAG AS src " + " ON (" + queryCondition + " ) " + " WHEN NOT MATCHED BY TARGET THEN "
                            + insertQuery + " WHEN MATCHED THEN " + updateQuery;
                    jdbcTemplate.execute( updateInsertQuery );
                }
                else
                {
                    // delete if exists else insert (merge statement)
                    String deleteMergeQuery = " MERGE " + tableName + " AS target " + " USING " + tableName
                            + "_STAG AS src " + " ON (" + queryCondition + " ) " + " WHEN MATCHED THEN DELETE ;";
                    String insertMergeQuery = " MERGE " + tableName + " AS target " + " USING " + tableName
                            + "_STAG AS src " + " ON (" + queryCondition + " ) " + " WHEN NOT MATCHED BY TARGET THEN "
                            + insertQuery + ";";

                    jdbcTemplate.execute( deleteMergeQuery );
                    jdbcTemplate.execute( insertMergeQuery );
                }

                jdbcTemplate.execute( "DELETE from " + tableName + "_STAG" );
                
            }

            else if ( importDef.getSourceType().equals( EnumSourceType.EXCEL )
                    && ( serverFileName.endsWith( ".xls" ) || serverFileName.endsWith( ".xlsx" ) ) )
            {

            }

            // Load Import Statistics status update to 'LOAD_SUCCESS'
            importStats.setLoadStatus( EnumLoadStatus.LOAD_SUCCESS );
            importStats.setLoadEndTime( DateTime.now() );
            importStats.setTotalRecordCount( count );
            importStats.setLoadRecordCount( count );
            importStats.setStatusMessage( "SUCCESS" );
            importStats = userRepository.saveOrUpdateImportStatics(importStats);

            message = messageSource.getMessage( "fileLoaded.MESSAGE",
                    new Object[] { serverFileName, count,
                            urlUtils.getBaseUrl() + "import/" + importDef.getImportId() + "/result/load/"+ importStats.getId()+"/view" }, null );
            response.setStatus( EnumResponseStatus.OK );
            response.setMessage( message );

        }
        catch( FileNotFoundException f)
        {
        	response.setStatus(EnumResponseStatus.ERROR);
        	response.setMessage(messageSource.getMessage("filePathNotFound.MESSAGE", new Object[]{serverFileName}, locale));
        	f.printStackTrace();
        }
        catch (IOException ioe) 
        {
        	response.setStatus(EnumResponseStatus.ERROR);
        	response.setMessage( messageSource.getMessage("filePathNotFound.MESSAGE", new Object[]{serverFileAddress}, locale));
        	ioe.printStackTrace();
		}
        catch ( Exception e )
        {
            response.setStatus( EnumResponseStatus.ERROR );
            message = messageSource.getMessage( "fileNotLoaded.MESSAGE",
                    new Object[] { serverFileName, e.getMessage() }, null );
            response.setMessage( message );
            // Load Import Statistics status update to 'LOAD_FAILURE'
            importStats.setLoadStatus( EnumLoadStatus.LOAD_FAILURE );
            importStats.setLoadEndTime( DateTime.now() );
            importStats.setStatusMessage( e.getMessage() );
            importStats = userRepository.saveOrUpdateImportStatics(importStats);
            e.printStackTrace();
        }
        finally
        {
        	
        	if(is!=null)
        		is.close();
        	if(isCopy!=null)
        		isCopy.close();
        	if(br!=null)
        		br.close();
        	
        }

        return response;
    	
    	
    }
}
