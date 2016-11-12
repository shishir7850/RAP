/**
 * 
 * SecurityConfiguration.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.quadratic.user.repository.impl.UserRepositoryImpl;

/**
 * Configuration class containing the security related aspects for portal.
 * 
 * @author Suneel Ayyaparaju
 * 
 */
@Configuration
public class SecurityConfiguration
{

    @Bean
    public UserRepositoryImpl qsUserRepository()
    {
        return new UserRepositoryImpl();
    }
}
