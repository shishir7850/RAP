/**
 * 
 * ResultController.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.result.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quadratic.rap.aspects.Loggable;
import com.quadratic.rap.dataimport.model.DataImportDefinition;
import com.quadratic.rap.dataimport.model.DataImportStream;
import com.quadratic.rap.dataimport.model.DataImportStreamField;
import com.quadratic.rap.dataimport.model.LoadImportStatistics;
import com.quadratic.rap.dataimport.repository.DataImportRepository;
import com.quadratic.rap.exceptions.QSException;
import com.quadratic.rap.util.UrlUtils;
import com.quadratic.rap.util.Utils;
import com.quadratic.user.model.User;
import com.quadratic.user.repository.UserRepository;

/**
 * @author Suneel Ayyaparaju.
 *
 */
@Controller
public class ResultController
{

    /**
     * Reference to the logger
     */
    private static final Logger logger = Logger.getLogger( ResultController.class );

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
     * Reference to jdbcTemplate
     */
    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * Reference to namedParameterJdbcTemplate
     */
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Loggable
    @RequestMapping( value = "/result/{resultTableStreamId}/view", method = RequestMethod.GET )
    public String getResults( @PathVariable String resultTableStreamId, Model model, Locale locale )
    {
        User currentUser = utils.getAuthenticatedUser();
        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }

        try
        {

            if ( resultTableStreamId != null && StringUtils.hasLength( resultTableStreamId ) )
            {
                // Get the database connection object
                DataSource ds = jdbcTemplate.getDataSource();
                Connection connection = ds.getConnection();
                Statement st = connection.createStatement();
                DataImportStream dataImportStream = dataImportRepository.findByStreamId( resultTableStreamId );
                if ( dataImportStream == null)
                {
                    throw new QSException( messageSource.getMessage( "Invalid_DataStream_Found", null, locale ) );
                }
                ResultSet resultSet = st.executeQuery( "select * from " + dataImportStream.getDatabaseTableName() );
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();

                List<DataImportStreamField> dataImportStreamFields = dataImportRepository
                        .findDataImportStreamFieldsByStreamId( resultTableStreamId );
                List<String> dbColumns = new ArrayList<>();
                List<String> columns = new ArrayList<>();
                List<List<String>> dataRows = new ArrayList<>();

                for ( int i = 1; i <= columnCount; i++ )
                {
                    String columnName = resultSetMetaData.getColumnName( i );
                    for ( DataImportStreamField dataImportStreamField : dataImportStreamFields )
                    {
                        if ( dataImportStreamField.getDatabaseFieldName().equals( columnName ) )
                        {
                            dbColumns.add( columnName );
                            columns.add( dataImportStreamField.getFieldName() );
                        }
                    }
                }

                // Results storing into list
                while ( resultSet.next() )
                {
                    List<String> dataRow = new ArrayList<>();
                    for ( String dbColumn : dbColumns )
                    {
                        dataRow.add( resultSet.getString( dbColumn ) );
                    }
                    dataRows.add( dataRow );
                }
                model.addAttribute( "columns", columns );
                model.addAttribute( "dataRows", dataRows );

            }
        }
        catch ( Exception e )
        {
            ResultController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
        }

        return "result/result :: #resultListing";
    }

    @RequestMapping( value = "/import/{importId}/result/load/{loadId}/view", method = RequestMethod.GET )
    public String getLoadImportResults( @PathVariable("importId") Integer importId, @PathVariable( "loadId" ) Integer loadId, Model model, Locale locale )
    {
        User currentUser = utils.getAuthenticatedUser();
        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }

        try
        {
            if ( importId == null )
            {
                throw new QSException( messageSource.getMessage( "ImportId_Empty", null, locale ) );
            }
            if ( importId != null )
            {
                // Get the database connection object
                DataSource ds = jdbcTemplate.getDataSource();
                Connection connection = ds.getConnection();
                Statement st = connection.createStatement();
                DataImportDefinition dataImportDefinition = dataImportRepository.findByImportId( importId );
                DataImportStream dataImportStream = dataImportRepository.findByStreamId( dataImportDefinition
                        .getStreamId() );
                
                LoadImportStatistics loadImportStatistics = dataImportRepository
                        .findLoadImportStatisticsById( importId, loadId );
                String query = "select * from " + dataImportStream.getDatabaseTableName() + " where DT_CR >='"
                        + loadImportStatistics.getLoadStartTime() + "' OR DT_MOD >='"
                        + loadImportStatistics.getLoadStartTime() + "' ";
                ResultSet resultSet = st.executeQuery( query );
                ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();

                List<DataImportStreamField> dataImportStreamFields = dataImportRepository
                        .findDataImportStreamFieldsByStreamId( dataImportDefinition.getStreamId() );
                List<String> dbColumns = new ArrayList<>();
                List<String> columns = new ArrayList<>();
                List<List<String>> dataRows = new ArrayList<>();

                for ( int i = 1; i <= columnCount; i++ )
                {
                    String columnName = resultSetMetaData.getColumnName( i );
                    for ( DataImportStreamField dataImportStreamField : dataImportStreamFields )
                    {
                        if ( dataImportStreamField.getDatabaseFieldName().equals( columnName ) )
                        {
                            dbColumns.add( columnName );
                            columns.add( dataImportStreamField.getFieldName() );
                        }
                    }
                }

                // Results storing into list
                while ( resultSet.next() )
                {
                    List<String> dataRow = new ArrayList<>();
                    for ( String dbColumn : dbColumns )
                    {
                        dataRow.add( resultSet.getString( dbColumn ) );
                    }
                    dataRows.add( dataRow );
                }
                model.addAttribute( "columns", columns );
                model.addAttribute( "dataRows", dataRows );

            }
        }
        catch ( Exception e )
        {
            ResultController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
        }

        return "result/loadedresults :: #loadedResultsModalDialog";
    }
}
