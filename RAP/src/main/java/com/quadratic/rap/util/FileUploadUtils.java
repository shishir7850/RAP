/**
 * 
 * FileUploadHelper.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.util.StringUtils;

import com.quadratic.rap.aspects.Loggable;

/**
 * @author Suneel Ayyaparaju
 * 
 */
public class FileUploadUtils
{
    /**
     * Reference to the logger
     */
    private static final Logger logger = Logger.getLogger( FileUploadUtils.class );

    private static final String IMAGES_FOLDER_NAME = "/images";

    private static final String IMAGES_LOCATION = System.getenv( "OPENSHIFT_DATA_DIR" )
            + FileUploadUtils.IMAGES_FOLDER_NAME;

    private static final String IMAGE_BASE64_ENCODE_PREFIX = "data:image";

    private static final String JPEG_FILE_EXTENSION = ".jpeg";

    /**
     * @param base64EncodedDataURL
     * @param uuid
     * @return
     */
    @Loggable
    public static String uploadFile( String base64EncodedDataURL, String uuid )
    {
        return FileUploadUtils.uploadFile( base64EncodedDataURL, uuid, null );
    }

    /**
     * @param base64EncodedDataURL
     * @param uuid
     * @param origFileName
     * @return
     */
    @Loggable
    public static String uploadFile( String base64EncodedDataURL, String uuid, String origFileName )
    {
        String uploadedFileName = null;
        // Is there any data in the encoded data url
        StringUtils.hasLength( base64EncodedDataURL );
        // Does this begin with the valid header?
        if ( base64EncodedDataURL.startsWith( FileUploadUtils.IMAGE_BASE64_ENCODE_PREFIX ) )
        {
            final int metadataEndIndex = base64EncodedDataURL.indexOf( "," );
            final String trueBase64EncodedDataURL = base64EncodedDataURL.substring( metadataEndIndex + 1 );
            final byte[] decodedImage = Base64.decode( trueBase64EncodedDataURL.getBytes() );

            final String fileNameToUpload = FileUploadUtils.getFileNameForUpload( FileUploadUtils.IMAGES_LOCATION,
                    uuid, origFileName );
            if ( fileNameToUpload != null )
            {
                FileOutputStream fileOutputStream = null;
                try
                {
                    fileOutputStream = new FileOutputStream( fileNameToUpload );
                    fileOutputStream.write( decodedImage );
                    fileOutputStream.flush();
                    final int imagesFolderNameIndex = fileNameToUpload.indexOf( FileUploadUtils.IMAGES_FOLDER_NAME );
                    uploadedFileName = fileNameToUpload.substring( imagesFolderNameIndex );
                }
                catch ( final Exception e )
                {
                    FileUploadUtils.logger.error( e );
                }
                finally
                {
                    if ( fileOutputStream != null )
                    {
                        try
                        {
                            fileOutputStream.close();
                        }
                        catch ( final IOException e )
                        {
                            FileUploadUtils.logger.error( e );
                        }
                    }
                }
            }
        }
        return uploadedFileName;
    }

    /**
     * @param dirName
     * @param uuid
     * @param origFileName
     * @return
     */
    private static String getFileNameForUpload( String dirName, String uuid, String origFileName )
    {
        String uploadFileName = null;
        String fileName = null;
        int ind = origFileName != null ? origFileName.indexOf( FileUploadUtils.IMAGES_FOLDER_NAME ) : -1;
        if ( StringUtils.hasLength( origFileName ) && ind >= 0 )
        {
            fileName = origFileName.substring( ind + FileUploadUtils.IMAGES_FOLDER_NAME.length() + 1 );
        }
        if ( fileName == null )
        {
            fileName = uuid + FileUploadUtils.JPEG_FILE_EXTENSION;
        }
        final String absoluteFilePath = dirName + File.separator + fileName;
        final boolean noFileWithSameName = FileUploadUtils.deleteFileIfExists( new File( absoluteFilePath ) );
        if ( noFileWithSameName )
        {
            uploadFileName = dirName + File.separator + uuid + "_" + new Random().nextInt()
                    + FileUploadUtils.JPEG_FILE_EXTENSION;
        }
        else
        {
            final String revisedUUID = uuid + "_" + new Random().nextInt();
            uploadFileName = FileUploadUtils.getFileNameForUpload( dirName, revisedUUID, origFileName );
        }
        return uploadFileName;
    }

    /**
     * @param fileToCheck
     * @return
     */
    private static boolean deleteFileIfExists( File fileToCheck )
    {
        boolean verdict = true;
        if ( fileToCheck.isFile() && fileToCheck.exists() )
        {
            try
            {
                verdict = fileToCheck.delete();
            }
            catch ( final Exception e )
            {
                verdict = false;
                FileUploadUtils.logger.error( e );
            }
        }
        return verdict;
    }
}
