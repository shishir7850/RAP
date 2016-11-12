package com.quadratic.rap.country.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.quadratic.user.model.AccessRight;
import com.quadratic.user.model.User;
import com.quadratic.user.repository.AccessRightRepositoryExtension;
import com.quadratic.rap.country.model.Country;

public interface CountryRepository  extends JpaRepository<Country, Long>, CountryRepositoryExtension {

	@Query( "FROM Country" )
    List<Country> findAllCountries();	
}
