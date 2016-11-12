/**
 * 
 * Package.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.dataimport.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.quadratic.rap.common.AbstractEntity;

/**
 * @author Suneel Ayyaparaju
 *
 */
@Entity
@Table( name = "PACKAGE" )
public class Package extends AbstractEntity
{

    /**
     * Reference to serialVersionUID
     */
    private static final long serialVersionUID = -5178262796956202400L;

    /**
     * Reference to objectUUID
     */
    @Column( name = "OBJECT_UUID" )
    private String objectUUID;

    /**
     * Reference to packageId
     */
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "PACKAGE_ID" )
    private Integer packageId;

    /**
     * Reference to packageName
     */

    @Column( name = "PACKAGE_NAME" )
    private String packageName;

    /**
     * Reference to the dataImportDefinitions
     */
    @OneToMany( fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "dataImportPackage" )
    private List<DataImportDefinition> dataImportDefinitions;

    /**
     * @return the objectUUID
     */
    public String getObjectUUID()
    {
        return objectUUID;
    }

    /**
     * @param objectUUID
     *            the objectUUID to set
     */
    public void setObjectUUID( String objectUUID )
    {
        this.objectUUID = objectUUID;
    }

    /**
     * @return the packageId
     */
    public Integer getPackageId()
    {
        return packageId;
    }

    /**
     * @param packageId
     *            the packageId to set
     */
    public void setPackageId( Integer packageId )
    {
        this.packageId = packageId;
    }

    /**
     * @return the packageName
     */
    public String getPackageName()
    {
        return packageName;
    }

    /**
     * @param packageName
     *            the packageName to set
     */
    public void setPackageName( String packageName )
    {
        this.packageName = packageName;
    }

    public List<DataImportDefinition> getDataImportDefinitions()
    {
        return dataImportDefinitions;
    }

    public void setDataImportDefinitions( List<DataImportDefinition> dataImportDefinitions )
    {
        this.dataImportDefinitions = dataImportDefinitions;
    }
}
