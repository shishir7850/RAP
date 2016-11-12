/**
 * 
 * DataImportRepository.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.dataimport.repository;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quadratic.rap.common.IConstants.EnumSourceType;
import com.quadratic.rap.dataimport.model.DataImportDefinition;
import com.quadratic.rap.dataimport.model.DataImportStream;
import com.quadratic.rap.dataimport.model.DataImportStreamField;
import com.quadratic.rap.dataimport.model.DateFormat;
import com.quadratic.rap.dataimport.model.LoadImportSchedule;
import com.quadratic.rap.dataimport.model.LoadImportStatistics;
import com.quadratic.rap.dataimport.model.Package;

/**
 * @author Suneel Ayyaparaju
 *
 */

public interface DataImportRepository extends JpaRepository<DataImportDefinition, Long>, DataImportRepositoryExtension
{

    /**
     * @param objectUUID
     * @return
     */
    DataImportDefinition findByObjectUUID( String objectUUID );

    DataImportDefinition findByImportId( Integer importId );

    @Query( "FROM DataImportStream WHERE (streamId = :streamId)" )
    DataImportStream findByStreamId( @Param( "streamId" ) String streamId );

    /**
     * @param packageName
     * @return
     */
    @Query( "FROM Package WHERE UPPER(packageName) = UPPER(:packageName)" )
    Package findByPackageName( @Param( "packageName" ) String packageName );

    /**
     * @return
     */
    @Query( "FROM Package" )
    List<Package> findAllPackages();

    /**
     * @return
     */
    @Query( "FROM DataImportDefinition" )
    List<DataImportDefinition> findAllDataImportDefinitions();

    /**
     * @return
     */
    @Query( "FROM DataImportStream" )
    List<DataImportStream> findAllDataImportStreams();

    /**
     * @return
     */
    @Query( "FROM DataImportStreamField" )
    List<DataImportStreamField> findAllDataImportStreamFields();

    /**
     * @return
     */
    @Query( "FROM DataImportStreamField WHERE (streamId = :streamId )" )
    List<DataImportStreamField> findDataImportStreamFieldsByStreamId( @Param( "streamId" ) String streamId );

    /**
     * @param streamFieldId
     * @return
     */
    @Query( "FROM DataImportStreamField WHERE (streamFieldId = :streamFieldId)" )
    DataImportStreamField findByStreamFieldId( @Param( "streamFieldId" ) Integer streamFieldId );

    @Query( "FROM DateFormat WHERE ( sourceType = :sourceType )" )
    List<DateFormat> findAllDateFormatsBySourceType( @Param( "sourceType" ) EnumSourceType sourceType );

    @Query( "FROM DateFormat" )
    List<DateFormat> findAllDateFormats();

    @Query( "FROM DateFormat WHERE ( dateFormatId = :dateFormatId )" )
    DateFormat findByDateFormatId( @Param( "dateFormatId" ) Integer dateFormatId );
   
    @Query( "FROM LoadImportStatistics WHERE importId = :importId and id = :loadId" )
    LoadImportStatistics findLoadImportStatisticsById( @Param( "importId" ) Integer importId, @Param( "loadId" ) Integer loadId);
    
    /**
     * @param packageId
     * @return
     */
    @Query( "FROM Package WHERE packageId = :packageId" )
    Package findByPackageId( @Param( "packageId" ) Integer packageId );
    
    /**
     * load a schedule of importId
     * @param importId
     * @return
     */
    @Query( "FROM LoadImportSchedule WHERE dataImportDefinition.importId = :importId" )
    LoadImportSchedule findScheduleByImportId( @Param("importId") Integer importId );
    
    
}
