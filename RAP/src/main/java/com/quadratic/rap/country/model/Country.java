/**
 * 
 * Country.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/

package com.quadratic.rap.country.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.quadratic.rap.common.AbstractEntity;
import com.quadratic.user.model.AccessRight;

/**
 * @author Manasa Mantena
 * 
 */
@Entity
@Table( name = "country" )
public class Country extends AbstractEntity {
 
  /**
     * Reference to serialVersionUID
     */
    private static final long serialVersionUID = 127022545632043724L;
    
    /**
     * Reference to objectUUID
     */
    @Column(name = "OBJECT_UUID")
    private String objectUUID;
   
    /**
     * Reference to countryID
     */
    @Id
    @Column( name = "COUNTRY_ID" )
    private int countryId;
    
    /**
     * Reference to countryCode
     */
    @Column(name = "COUNTRY_CODE")
    private String countryCode;
    
    /**
     * Reference to countryName
     */
    @Column(name = "COUNTRY_NAME")
    private String countryName;
    
    /**
     * Reference to accessRights
     */
    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable( name = "COUNTRY_ACCESS", joinColumns = @JoinColumn( name = "COUNTRY_ID" ), inverseJoinColumns = @JoinColumn( name = "ACCESS_ID" ) )
    private List<AccessRight> accessRights;

    /**
     * @return the accessRights
     */
    public List<AccessRight> getAccessRights() {
  
     return accessRights;
 }

    /**
     * @param accessRights
     *            the accessRights to set
     */
 public void setAccessRights(List<AccessRight> accessRights) {
  
  this.accessRights = accessRights;
 }

 /**
     * @return the objectUUID
     */
    public String getObjectUUID() {
  
     return objectUUID;
 }

    /**
     * @param objectUUID the objectUUID to set
     */
 public void setObjectUUID(String objectUUID) {
 
  this.objectUUID = objectUUID;
 }
 
  /**
     * @return the countryId
     */
 public int getCountryId() {
 
  return countryId;
 }

  /**
     * @param countryId to set
     */
 public void setCountryId(int countryId) {
  
  this.countryId = countryId;
 }

  /**
     * @return the countryCode
     */
 public String getCountryCode() {
  
  return countryCode;
 }

  /**
     * @param countryCode to set
     */ 
 public void setCountryCode(String countryCode) {
  
  this.countryCode = countryCode;
 }

  /**
     * @return the countryName
     */
 public String getCountryName() {
  
  return countryName;
 }

  /**
     * @param countryName to set
     */ 
 public void setCountryName(String countryName) {
  
  this.countryName = countryName;
 }


}