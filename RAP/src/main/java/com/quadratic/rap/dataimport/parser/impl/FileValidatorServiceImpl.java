package com.quadratic.rap.dataimport.parser.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.quadratic.rap.common.IConstants.EnumRequiredStatus;
import com.quadratic.rap.common.IConstants.EnumResponseStatus;
import com.quadratic.rap.common.Response;
import com.quadratic.rap.dataimport.model.DataImportDefinition;
import com.quadratic.rap.dataimport.model.DataImportMapping;
import com.quadratic.rap.dataimport.model.DataImportStreamField;
import com.quadratic.rap.dataimport.model.LoadImportStatistics;
import com.quadratic.rap.dataimport.parser.FileValidator;
import com.quadratic.rap.dataimport.parser.FileValidatorService;
import com.quadratic.rap.dataimport.parser.util.EnumFieldType;
import com.quadratic.rap.dataimport.parser.util.Field;

@Service
public class FileValidatorServiceImpl implements FileValidatorService
{

	@Autowired
	private MessageSource messageSource;

	/**
	 * check passed file is a valid CSV or not
	 */
	@Override
	public Response validateCsvFile(LoadImportStatistics importStats,
			List<DataImportMapping> importMappings,
			DataImportDefinition importDefinition, Locale locale, InputStream is)
			throws Exception 
	{
		Response response = new Response();
		
		// Third parity Bia validator plugin filed's list.
		List<Field> importFields = importFields(importMappings, importDefinition);
		FileValidator csvValidator = new CsvValidatorImpl(
				is,importFields, ",",importDefinition.getNumberOfHeaderLines(),
				importDefinition.getNumberOfFooterLines());
		
		if (csvValidator.isValid()) 
		{
			response.setStatus(EnumResponseStatus.OK);
		}
		else
		{
			List<String> errors = Arrays.asList(csvValidator
					.getValidationDetails().split("\n"));
			int size = errors.size();
			int nooferrors = size - 3;
			int noofLodedRecords = 0;
			String key=errors.get(size - 1);
			try
			{
				if( key.indexOf("#") != -1)
				{
					String value = key.substring(key.indexOf("#")+1);
					noofLodedRecords =  (value == "" ? 0 : Integer.parseInt(value.trim()));
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			response.setData(errors);
			response.setStatus(EnumResponseStatus.ERROR);
			response.setMessage(messageSource.getMessage(
					"lodingFileDataError.MESSAGE",
					new Object[] { importStats.getFileName(),
							noofLodedRecords, nooferrors, "#" }, locale));
			
		}
		return response;
	}
	
	/**
	 * Returns the uploading file com.bia.csvvalidator.Field List. 
	 * It is Converting List of DataImportMapping to com.bia.csvvalidator.Field List for 
	 * using Bia CsvValidator for validating csv file.   
	 * @param importMappings
	 * @param importDef
	 * @return
	 */
	public List<Field> importFields( List<DataImportMapping> importMappings, DataImportDefinition importDef)
	{
		List<Field> importFields = new ArrayList<Field>();
		int index = 0;
		for (DataImportMapping importMapping : importMappings) {
			DataImportStreamField streamField = importMapping.getStreamField();
			index = index + 1;
			boolean isOptional = !EnumRequiredStatus.Y.equals(streamField
					.getIsRequired());

			if (EnumFieldType.DATE.equals(streamField.getJavaFieldType())) {
				Field field = new Field(index, streamField.getFieldName(),
						EnumFieldType.DATE, isOptional, importDef.getDateFormat()
								.getDateFormat());
				importFields.add(field);
			} else {
				Field field = new Field(index, streamField.getFieldName(),
						streamField.getJavaFieldType(), isOptional);
				importFields.add(field);
			}
		}
		return importFields;
	}
}
