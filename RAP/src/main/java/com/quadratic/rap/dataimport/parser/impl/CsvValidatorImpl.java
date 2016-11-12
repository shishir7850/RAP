package com.quadratic.rap.dataimport.parser.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.quadratic.rap.dataimport.parser.CsvValidator;
import com.quadratic.rap.dataimport.parser.util.EnumFieldType;
import com.quadratic.rap.dataimport.parser.util.Field;

/**
 *
 * @author Intesar Mohammed
 */
public class CsvValidatorImpl implements CsvValidator
{

    private String filename;
    
    private InputStream stream;
    
    private int noOfFields;
    
    private List<Field> list;
    
    private Map<Integer, Field> map = new HashMap<Integer, Field>();
    
    private String delimiter;
    
    private StringBuilder errors = new StringBuilder();
    
    private int count = 1;
    
    private int errorsInitialSize;
    
    private int totalErrors = 0;
    
    private boolean jobDone = false;
    
    private boolean success = false;
    
    private int headerLines = 0;

    private int footerLines = 0;

    private boolean headerLinesSkiped = false;
    
    private Integer firstLineErrors = 0;
    
    public CsvValidatorImpl(InputStream stream, List<Field> list, String delimiter,int headerLines,int footerLines) {
        this.stream = stream;
        this.noOfFields = list.size();
        this.list = list;
        this.delimiter = delimiter;
        errorsInitialSize = this.errors.length();
        this.headerLines = headerLines;
        this.footerLines = footerLines;
    }
    
    public CsvValidatorImpl(String filename, List<Field> list, String delimiter,int headerLines,int footerLines) {
        this.filename = filename;
        this.noOfFields = list.size();
        this.list = list;
        this.delimiter = delimiter;
        errorsInitialSize = this.errors.length();
        this.headerLines = headerLines;
        this.footerLines = footerLines;
    }

    @Override
    public boolean isValid() {
        if (!jobDone) {
            getValidationDetails();
        }
        return this.success;
    }
    
    
    @Override
    public String getValidationDetails() {
        if (jobDone) {
            return this.errors.toString();
        }
        try {
            listToMap();
            
            BufferedReader br = null;
            DataInputStream in =null;
            if( stream != null)
            {
            	br = new BufferedReader(new InputStreamReader(stream));
            }
            else if( filename != null)
            {
            	FileInputStream fstream = new FileInputStream(filename);
	        	in = new DataInputStream(fstream);
	            br = new BufferedReader(new InputStreamReader(in));
            }
            else
            {
            	            	
            	throw new FileNotFoundException("Must specifiy path of the validate File or Pass the File Object");
            }
             
           
            String strLine;
            while ((strLine = br.readLine()) != null) {
            	if( !(count <= headerLines) )
                validateLine(strLine);
                count++;
            }
            
        } catch (Exception e) {
            this.errors.append("Error: ");
            this.errors.append(e.getMessage());
            this.errors.append("\n");
        }

        jobDone = true;

        if (this.errors.length() == errorsInitialSize) 
        {
        	
            this.success = true;

        }
        /*else if( firstLineErrors == this.errors.length() )
        {
        	this.errors.append("This Import Definition does not expect a Header <b> Probably </b>,");
        	this.errors.append(" This Error is due to existence of header in the File.");
        	this.errors.append(" \n");
        }	*/
                
        this.errors.append("Filename : ");
        this.errors.append(filename);
        this.errors.append("\n");
        this.errors.append("Total Lines # ");
        this.errors.append(count-1);
        this.errors.append("\n");
        this.errors.append("Total Errors # ");
        this.errors.append(totalErrors);
        return this.errors.toString();

    }

    private void validateLine(String line)
    {
        if (line == null || line.isEmpty()) 
        {
            return;
        }
        String[] tokens = line.split(delimiter);
        
        if (tokens.length < noOfFields) 
        {
            this.errors.append("Line ");
            this.errors.append(this.count);
            this.errors.append(" is invalid, contains ");
            this.errors.append(tokens.length);
            this.errors.append(" required ");
            this.errors.append(this.noOfFields);
            this.errors.append(" fields ");
            this.errors.append("\n");
            totalErrors++;
        } 
        
        else if(tokens.length > noOfFields)
        {
        	this.errors.append("Line Number ");
            this.errors.append(this.count);
            this.errors.append(" is invalid, contains ");
            this.errors.append(tokens.length);
            this.errors.append(" required ");
            this.errors.append(this.noOfFields);
            this.errors.append(" fields ");
            this.errors.append("\n");
            totalErrors++;
        } 
        else 
        {
            // each field should be of given type
            Integer index = 1;
            for (String token : tokens) {
                Field f = map.get(index);
                handle(token, f);
                index++;
            }
            if ( tokens.length != noOfFields) {
                Field f = map.get(index);
                handle("", f);
            }
        } 
    }

    private boolean isFirstLineHeader(String[] tokens) {
        Integer index = 1;
        for (String t : tokens) {
            Field f = map.get(index);
            index++;
            if (t.equalsIgnoreCase(f.getName())) {
                return true;
            }
        }
        return false;
    }

    private void handle(String token, Field f)
    {
        try {
            // handle optional
            if (f.isIsOptional() && (token == null || token.trim().length() == 0)) {
                return;
            }

            if (token == null || token.trim().length() == 0) {
            	
            	this.errors.append("Missing Data for Line: ");
                
                this.errors.append(this.count);
                
                this.errors.append(" for Column: ");
                this.errors.append(f.getName() != null ? "'"+f.getName()+"'" : "" + f.getIndex());
                this.errors.append(" indexed at: ");
                this.errors.append(f.getIndex());
                this.errors.append(" \n");
                totalErrors++;
                return;
            }

            if (f.getType().equals(EnumFieldType.NUMBER)) {
                checkRegex(f, token);
                Double.parseDouble(token);
            } else if (f.getType().equals(EnumFieldType.TEXT)) {
                checkRegex(f, token);
            } else if (f.getType().equals(EnumFieldType.DATE)) {
                if (f.getRegex() != null && f.getRegex().trim().length() > 0) {
                    
                	DateTimeFormatter destFormat = DateTimeFormat.forPattern( f.getRegex() );
                	destFormat.parseDateTime(token);
                }
            }

        } catch (Exception ex) {
        	
        	
        		this.errors.append("Invalid data ");
                this.errors.append("Line: ");
                this.errors.append(this.count);
                this.errors.append(" Required ");
                this.errors.append(f.getRegex() != null ?  f.getRegex() : f.getType());
                this.errors.append(" found ' ");
                this.errors.append(token);
                this.errors.append(" '");
                this.errors.append(" for Column: ");
                this.errors.append(f.getName() != null ? "'"+f.getName()+"'" : "" + f.getIndex());
                this.errors.append(" indexed at ");
                this.errors.append(f.getIndex());
                this.errors.append(" \n");
                totalErrors++;
        	
        }
    }

    private void checkRegex(Field f, String token) throws RuntimeException {
        if (f.getRegex() != null && f.getRegex().trim().length() > 0) {
            Pattern p = Pattern.compile(f.getRegex());
            Matcher m = p.matcher(token);
            if (!m.matches()) {
                throw new RuntimeException();
            }
        }
    }

    private void listToMap() {
        Collections.sort(list);
        Set<Field> set = new HashSet<Field>(list);

        if (list.get(0).getIndex() != 1 || list.get(list.size() - 1).getIndex() != list.size()
                || set.size() < list.size()) {
            this.errors.append("Invalid indexes found if you have 10 fields your index starts from 0 to 9\n");
            this.errors.append("You need to correct these indexes before we can proceed. \n");
            this.errors.append("You supplied Field indexes - ");
            this.errors.append(list);
            totalErrors++;
        }

        for (Field f : list) {
            map.put(f.getIndex(), f);
        }
    }
}
