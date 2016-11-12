package com.quadratic.rap.country.controller;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

//import javax.enterprise.inject.Model;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.quadratic.rap.aspects.Loggable;
import com.quadratic.rap.country.model.Country;
import com.quadratic.rap.country.repository.CountryRepository;
import com.quadratic.rap.util.UrlUtils;
import com.quadratic.rap.util.Utils;
import com.quadratic.user.model.User;

@Controller
public class StaticCountryController {
	@Autowired
	CountryRepository staticcountry;
	@Autowired
	UrlUtils urlUtils;
	@Autowired
	Utils utils;
	@Loggable
    @RequestMapping( value = "/staticdata/countries", method = RequestMethod.GET )
    public String showCountries( Model model, Locale locale )
    {
     //logger.debug("Entering showCountries");
     
		
		User user = utils.getAuthenticatedUser();
		 
        if ( user == null )
        {
            return "redirect:" + urlUtils.getBaseUrl() + "auth/login";
        }
        try
        {
            List<Country> countries = staticcountry.findAllCountries();
            model.addAttribute( "countries", countries );

        }
        catch ( final Exception e )
        {
        	//StaticCountryController.logger.error( "Exception: " + e );
             model.addAttribute( "error", e.getMessage() );
        }

        //logger.debug("Leaving");
        return "staticdata/countrieslist :: #fragmentCountriesListing";
    }
}
