package com.quadratic.rap.dataimport.parser;

import java.io.InputStream;
import java.util.List;
import java.util.Locale;

import com.quadratic.rap.common.Response;
import com.quadratic.rap.dataimport.model.DataImportDefinition;
import com.quadratic.rap.dataimport.model.DataImportMapping;
import com.quadratic.rap.dataimport.model.LoadImportStatistics;
import com.quadratic.rap.dataimport.parser.util.Field;

public interface FileValidatorService 
{

	/**
	 * Check it is a valid file and is contains a valid data.
	 * @param file
	 * @throws Exception
	 */
	public Response validateCsvFile( LoadImportStatistics importStats, List<DataImportMapping> importMappings, 
			DataImportDefinition importDefinition, Locale locale,InputStream is)throws Exception;
	
	public List<Field> importFields( List<DataImportMapping> importMappings, DataImportDefinition importDef);
}
