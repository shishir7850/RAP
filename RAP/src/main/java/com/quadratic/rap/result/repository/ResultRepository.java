/**
 * 
 * ResultRepository.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quadratic.rap.dataimport.model.DataImportDefinition;

/**
 * @author Suneel Ayyaparaju.
 *
 */
public interface ResultRepository extends JpaRepository<DataImportDefinition, Long>, ResultRepositoryExtension
{

}
