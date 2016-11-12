package com.quadratic.rap.dataimport.parser;


/**
 * Justify is a valid file and is it contains valid data   
 * @author mtoluchuri
 *
 */
public interface FileValidator 
{
	 /**
     * isValid() or getValidationDetails() can be executed in any sequence.
     */
	
	 /**
     * @return  true if valid else false
     */
    boolean isValid();

    /**
     * You want to call this only when isValid return's false and this can be used to 
     *  convey error report back to the third party
     * @return Detail Report of all failures
     */
    String getValidationDetails();
	
}
