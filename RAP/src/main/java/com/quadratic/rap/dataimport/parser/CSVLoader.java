package com.quadratic.rap.dataimport.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.quadratic.rap.dataimport.parser.util.EnumFieldType;
import com.quadratic.rap.dataimport.parser.util.Field;

/**
 * 
 * @author Suneel Ayyaparaju.
 * 
 */
public class CSVLoader
{

    private static final String SQL_INSERT = "INSERT INTO ${table}(${keys}) VALUES(${values})";
    private static final String TABLE_REGEX = "\\$\\{table\\}";
    private static final String KEYS_REGEX = "\\$\\{keys\\}";
    private static final String VALUES_REGEX = "\\$\\{values\\}";


    private String separator;

    private int headerLines;

    private int footerLines;

    private boolean headerLinesSkiped;

    private String loadedBy;
    
    private List<Field> fields;
    
    private JdbcTemplate jdbcTemplate;
    
    

    /**
     * Public constructor to build CSVLoader object with Connection details. The
     * connection is closed on success or failure.
     * 
     * @param connection
     */
    public CSVLoader( JdbcTemplate jdbcTemplate )
    {
        this.jdbcTemplate = jdbcTemplate;
        // Set default separator
        this.separator = ",";
        // Set default headerLines
        this.headerLines = 0;
        // Set default footerLines
        this.footerLines = 0;
        // Set default loadedBy is to empty
        this.loadedBy = "";
    }

    /**
     * Parse CSV file using OpenCSV library and load in given database table.
     * 
     * @param csvFile
     *            Input CSV file
     * @param tableName
     *            Database table name to import data
     * @param truncateBeforeLoad
     *            Truncate the table before inserting new records.
     * @throws Exception
     */
    public int loadCSV( BufferedReader br, String tableName, String columns, boolean truncateBeforeLoad, final String dateFormat)
            throws Exception
    {

        // User currentUser = utils.getAuthenticatedUserEx();
        if ( this.jdbcTemplate == null )
        {
            throw new Exception( "Not a valid database connection." );
        }
        if ( br == null )
        {
            throw new Exception( "Invalid file, please check the file format." );
        }

        String[] cols = org.apache.commons.lang.StringUtils.split( columns, "," );

        if ( cols == null || columns == null )
        {
            throw new FileNotFoundException( "Invalid file, please check the Flat file format." );
        }

        String questionmarks = StringUtils.repeat( "?,", cols.length );
        questionmarks = (String) questionmarks.subSequence( 0, questionmarks.length() - 1 );
        String query = SQL_INSERT.replaceFirst( TABLE_REGEX, tableName );
        query = query.replaceFirst( KEYS_REGEX, columns );
        query = query.replaceFirst( VALUES_REGEX, questionmarks );
        
        final int count;
        try
        {
            
            if ( truncateBeforeLoad )
            {
                // delete data from table before loading csv
               jdbcTemplate.execute( "DELETE FROM " + tableName );
            }

            // final int batchSize = 1000;

            // Get the total no. of lines in a flat file
            

            // Header Lines Skipping
            if ( !this.headerLinesSkiped )
            {
                for ( int i = 0; i < headerLines; i++ )
                {
                    br.readLine();
                    
                }
                this.headerLinesSkiped = true;
            }
            
            // Reading a flat file
            
            String currentLine = null;
            final List<String[]> strings= new ArrayList<String[]>();
            while ( ( currentLine = br.readLine() ) != null )
            {
                String dataRow[] = currentLine.split( "," );	
                strings.add(dataRow);
            }
            count = strings.size();
            
            //dateFormat=importDef.getDateFormat().getDateFormat();
            
            jdbcTemplate.batchUpdate(query,new BatchPreparedStatementSetter(){
            	
				@Override
				public void setValues(PreparedStatement ps, int i)
						throws SQLException {
					String dataRow[] = strings.get(i);
					if ( dataRow != null )
	                {
	                    int index = 0;
	                    DateTimeFormatter destFormat = DateTimeFormat.forPattern( dateFormat );
	                    
	                    for ( String columnData : dataRow )
	                    {	
	                		Field field = fields.get(index);
	                		if( EnumFieldType.DATE.equals(field.getType()))
	                		{                    			
	                    		 columnData = destFormat.parseDateTime( columnData ).toString();
	                		}	
	                		index+=1;
	                        ps.setString(index , columnData );
	                        
	                    }
	                    
	                    ps.setString( ++index, "mahesh" );
	                    ps.setString( ++index, DateTime.now().toString() );
	                   
	                    //logger.info(ps.toString());
	                }
					
				}

				@Override
				public int getBatchSize() {
					// TODO Auto-generated method stub
					return count;
				}
            	
            });
            
        }
        catch ( Exception e )
        {           
            e.printStackTrace();
            throw new Exception(
            "Error occurred while loading data from file :"
            + e.getMessage() );
        }
        finally
        {
        	if(br!=null)
             br.close();
        }
        return count;
    }

    public String getSeparator()
    {
        return separator;
    }

    public void setSeparator( String separator )
    {
        this.separator = separator;
    }

    /**
     * @return the headerLines
     */
    public int getHeaderLines()
    {
        return headerLines;
    }

    /**
     * @param headerLines
     *            the headerLines to set
     */
    public void setHeaderLines( int headerLines )
    {
        this.headerLines = headerLines;
    }

    /**
     * @return the footerLines
     */
    public int getFooterLines()
    {
        return footerLines;
    }

    /**
     * @param footerLines
     *            the footerLines to set
     */
    public void setFooterLines( int footerLines )
    {
        this.footerLines = footerLines;
    }

    /**
     * @return the loadedBy
     */
    public String getLoadedBy()
    {
        return loadedBy;
    }

    /**
     * @param loadedBy
     *            the loadedBy to set
     */
    public void setLoadedBy( String loadedBy )
    {
        this.loadedBy = loadedBy;
    }

	/**
	 * @return the fields
	 */
	public List<Field> getFields() {
		return fields;
	}

	/**
	 * @param fields the fields to set
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	/**
	 * @return the jdbcTemplate
	 */
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

}
