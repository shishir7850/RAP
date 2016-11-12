/**
 * 
 * CompositeRepository.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.cms.composites.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.quadratic.rap.cms.composites.model.AccountLKUP;
import com.quadratic.rap.cms.composites.model.Composite;

/**
 * @author Suneel Ayyaparaju
 *
 */
public interface CompositeRepository extends JpaRepository<Composite, Long>, CompositeRepositoryExtension
{

    Composite findByCompositeId( Integer compositeId );
    
    @Query( "FROM Composite" )
    List<Composite> findAllComposites();
    
    @Query( "FROM AccountLKUP WHERE (accountId = :accountId)" )
    AccountLKUP findByAccountId( @Param( "accountId" ) String accountId );
    
    @Query( "SELECT composite FROM Composite composite, Account acc WHERE (composite.compositeId = acc.composite.compositeId AND acc.account.accountId = :accountId)" )
    List<Composite> findCompositesByAccountId( @Param( "accountId" ) String accountId );
    
    
}
