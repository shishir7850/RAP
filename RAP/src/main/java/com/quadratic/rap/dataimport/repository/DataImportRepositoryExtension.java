/**
 * 
 * DataImportRepositoryExtension.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic Software Systems Pvt Ltd. 
 * 
 **/
package com.quadratic.rap.dataimport.repository;

import java.util.List;
import java.util.Locale;

import org.springframework.transaction.annotation.Transactional;

import com.quadratic.rap.common.Response;
import com.quadratic.rap.dataimport.model.DataImportDefinition;
import com.quadratic.rap.dataimport.model.LoadImportSchedule;
import com.quadratic.rap.dataimport.model.LoadImportStatistics;
import com.quadratic.rap.dataimport.model.Package;
import com.quadratic.rap.dto.DataImportLoadDTO;
import com.quadratic.user.model.User;

/**
 * @author Suneel Ayyaparaju
 *
 */

public interface DataImportRepositoryExtension 
{

    /**
     * @param qsPackage
     * @return
     * @throws Exception
     */
	@Transactional
    public Package createPackage( Package qsPackage) throws Exception;

    /**
     * @param qsDataImportDefinition
     * @param selectedColumns
     * @return
     * @throws Exception
     */
    @Transactional
    public DataImportDefinition createDataImportDefinition( DataImportDefinition qsDataImportDefinition, List<String> selectedColumns) throws Exception;
    
    /**
     * @param qsDataImportDefinition
     * @param selectedColumns
     * @return
     * @throws Exception
     */
    @Transactional
    public DataImportDefinition updateDataImportDefinition( DataImportDefinition qsDataImportDefinition, List<String> selectedColumns) throws Exception;
      
    /**
     * @param dataImportLoadDTO
     * @return
     * @throws Exception
     */
    public Response loadDataImport(DataImportLoadDTO dataImportLoadDTO, Locale locale, User currentUser ) throws Exception;
    
    /**
     * 
     * @param loadImportStats
     * @throws Exception
     */
    @Transactional
    public void saveLoadStatistics( LoadImportStatistics loadImportStats ) throws Exception;
    
    /**
     * 
     * @param importDefinition
     * @throws Exception
     */
    public void deleteDataImport( DataImportDefinition importDefinition)throws Exception;
    
    /**
     * 
     * @param qsPackage
     * @throws Exception
     */
    public Package updatePackage( Package qsPackage) throws Exception;
    
    /**
     * 
     * @param qsPackage
     * @throws Exception
     */
    public int deletePackage( Integer qsPackage )throws Exception;

    /**
     * 
     * @param importStatistics
     * @throws Exception
     */
    /*public LoadImportStatistics saveOrUpdateImportStatics( LoadImportStatistics importStatistics )throws Exception;*/
    
    /**
     * Save import schedule information
     * @param importSchedule
     * @return
     * @throws Exception
     */
    public LoadImportSchedule save(LoadImportSchedule importSchedule)throws Exception;
    
    /**
     * returns the {@link DataImportLoadDTO}
     * @param importId
     * @return
     * @throws Exception
     */
    public DataImportLoadDTO createDataImport(Integer importId)throws Exception;
    
    /**
     * If already exist from db find and update details, if not save new object  
     * @param importSchedule
     * @return
     * @throws Exception
     */
    public LoadImportSchedule saveOrUpdate(LoadImportSchedule importSchedule )throws Exception;
        
}
