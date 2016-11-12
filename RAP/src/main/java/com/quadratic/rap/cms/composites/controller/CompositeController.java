/**
 * 
 * CompositeController.java
 * 
 * Copyright (c) 2014 Quadratic Software Systems Pvt Ltd, India. All rights
 * reserved.
 * 
 * This software is the confidential and proprietary information of Quadratic
 * Software Systems Pvt Ltd.
 * 
 **/
package com.quadratic.rap.cms.composites.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quadratic.rap.aspects.Loggable;
import com.quadratic.rap.cms.composites.model.Account;
import com.quadratic.rap.cms.composites.model.AccountAudit;
import com.quadratic.rap.cms.composites.model.AccountDTO;
import com.quadratic.rap.cms.composites.model.AccountLKUP;
import com.quadratic.rap.cms.composites.model.AccountNote;
import com.quadratic.rap.cms.composites.model.AssociationAudit;
import com.quadratic.rap.cms.composites.model.Benchmark;
import com.quadratic.rap.cms.composites.model.BenchmarkDTO;
import com.quadratic.rap.cms.composites.model.BenchmarkLKUP;
import com.quadratic.rap.cms.composites.model.Category;
import com.quadratic.rap.cms.composites.model.CategoryLKUP;
import com.quadratic.rap.cms.composites.model.Composite;
import com.quadratic.rap.cms.composites.model.CompositeAudit;
import com.quadratic.rap.cms.composites.model.CompositeFee;
import com.quadratic.rap.cms.composites.model.CompositeNote;
import com.quadratic.rap.cms.composites.model.CompositeTypes;
import com.quadratic.rap.cms.composites.model.CompositeYears;
import com.quadratic.rap.cms.composites.model.Currency;
import com.quadratic.rap.cms.composites.model.Description;
import com.quadratic.rap.cms.composites.model.Disclosure;
import com.quadratic.rap.cms.composites.model.DisclosureAssociation;
import com.quadratic.rap.cms.composites.model.DisclosureAssociationDTO;
import com.quadratic.rap.cms.composites.model.DisclosureAudit;
import com.quadratic.rap.cms.composites.model.DisclosureDTO;
import com.quadratic.rap.cms.composites.model.DisclosureLKUP;
import com.quadratic.rap.cms.composites.model.FeeSchedule;
import com.quadratic.rap.cms.composites.model.Firm;
import com.quadratic.rap.cms.composites.model.FirmAudit;
import com.quadratic.rap.cms.composites.model.FirmDTO;
import com.quadratic.rap.cms.composites.model.Location;
import com.quadratic.rap.cms.composites.model.ReasonCodeLKUP;
import com.quadratic.rap.cms.composites.model.RiskFreeIndex;
import com.quadratic.rap.cms.composites.repository.CompositeRepository;
import com.quadratic.rap.common.IConstants;
import com.quadratic.rap.common.IConstants.EnumResponseStatus;
import com.quadratic.rap.common.Response;
import com.quadratic.rap.dataimport.repository.DataImportRepository;
import com.quadratic.rap.exceptions.NotAuthenticatedException;
import com.quadratic.rap.exceptions.QSException;
import com.quadratic.rap.util.UrlUtils;
import com.quadratic.rap.util.Utils;
import com.quadratic.user.model.User;
import com.quadratic.user.repository.UserRepository;

/**
 * @author Suneel Ayyaparaju
 *
 */
@Controller
public class CompositeController
{
    /**
     * Reference to the logger
     */
    private static final Logger logger = Logger.getLogger( CompositeController.class );

    /**
     * Reference to the userRepository
     */
    @Autowired
    UserRepository userRepository;

    /**
     * Reference to the dataImportRepository
     */
    @Autowired
    DataImportRepository dataImportRepository;

    /**
     * Reference to the dataImportRepository
     */
    @Autowired
    CompositeRepository compositeRepository;

    /**
     * Reference to messageSource
     */
    @Autowired
    MessageSource messageSource;

    /**
     * Reference to utils
     */
    @Autowired
    Utils utils;

    /**
     * Reference to urlUtils
     */
    @Autowired
    UrlUtils urlUtils;

    @PersistenceContext
    EntityManager entityManager;

    @Loggable
    @RequestMapping( value = "/analytics/composites/dashboard", method = RequestMethod.GET )
    public String showCompositesDashboard( HttpServletRequest request, Model model, Locale locale )
    {

        User currentUser = utils.getAuthenticatedUser();
        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }

        try
        {
            // List<Composite> composites = entityManager.createQuery(
            // "SELECT c FROM Composite c" ).getResultList();
            List<Composite> composites = compositeRepository.findAllComposites();

            model.addAttribute( "composites", composites );
        }
        catch ( Exception e )
        {
            CompositeController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
        }

        return "cms/Composites-Dashboard";

    }

    @SuppressWarnings( "unchecked" )
    @Loggable
    @RequestMapping( value = "/analytics/composites/newcomposite", method = RequestMethod.GET )
    public String newCompositeForm( HttpServletRequest request, Model model, Locale locale )
    {
        User currentUser = utils.getAuthenticatedUser();
        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }
        try
        {
            List<Firm> firms = entityManager.createQuery( "SELECT f FROM Firm f" ).getResultList();
            List<Currency> currencies = entityManager.createQuery( "SELECT c FROM Currency c" ).getResultList();
            List<CompositeYears> years = entityManager.createQuery( "SELECT y FROM CompositeYears y" ).getResultList();
            List<CategoryLKUP> categoryLKUP = entityManager.createQuery( "SELECT catg FROM CategoryLKUP catg" )
                    .getResultList();
            List<ReasonCodeLKUP> reasonCodeLKUP = entityManager.createQuery( "SELECT r FROM ReasonCodeLKUP r" )
                    .getResultList();
            List<CompositeTypes> compositeTypes = entityManager.createQuery( "SELECT ct FROM CompositeTypes ct" )
                    .getResultList();
            List<RiskFreeIndex> riskFreeIndexs = entityManager.createQuery( "SELECT ri FROM RiskFreeIndex ri" )
                    .getResultList();
            List<AccountLKUP> accounts = entityManager.createQuery( "SELECT comp FROM AccountLKUP comp" )
                    .getResultList();
            List<BenchmarkLKUP> benchmarkLKUP = entityManager.createQuery( "SELECT bench FROM BenchmarkLKUP bench" )
                    .getResultList();

            List<DisclosureLKUP> disclosureLKUP = entityManager.createQuery( "SELECT d FROM DisclosureLKUP d" )
                    .getResultList();

            List<FeeSchedule> fees = entityManager.createQuery( "SELECT f FROM FeeSchedule f" ).getResultList();

            model.addAttribute( "firms", firms );
            model.addAttribute( "currencies", currencies );
            model.addAttribute( "years", years );
            model.addAttribute( "categories", categoryLKUP );
            model.addAttribute( "reasonCodes", reasonCodeLKUP );
            model.addAttribute( "compositeTypes", compositeTypes );
            model.addAttribute( "riskFreeIndexs", riskFreeIndexs );
            model.addAttribute( "accounts", accounts );
            model.addAttribute( "benchmarks", benchmarkLKUP );
            model.addAttribute( "disclosures", disclosureLKUP );
            model.addAttribute( "composite", new Composite() );
            model.addAttribute( "fees", fees );

        }
        catch ( Exception e )
        {
            CompositeController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
        }

        return "cms/NewComposite";

    }

    @ResponseBody
    @Loggable
    @Transactional
    @RequestMapping( value = "/analytics/composites/newcomposite/create", method = RequestMethod.POST, produces = "application/json" )
    public Response saveNewComposite( Composite composite, Model model, Locale locale )
    {
        User currentUser = utils.getAuthenticatedUserEx();
        Response response = new Response();

        try
        {
            if ( composite == null || composite.getCategories() == null || composite.getAccounts() == null
                    || composite.getBenchmarks() == null || composite.getDisclosures() == null
                    || composite.getDescriptions() == null || composite.getFees() == null
                    || composite.getNotes() == null || composite.getReasonCodes() == null )
            {
                throw new QSException( messageSource.getMessage( "Invalid_Input_Found", null, null ) );
            }
            if ( composite != null )
            {
                /* setting compositeId to the objects */
                List<Category> categories = composite.getCategories();
                for ( Category category : categories )
                {
                    if ( category != null && category.getCategory() != null
                            && category.getCategory().getCategoryId() != null )
                    {
                        category.setComposite( composite );
                    }
                }
                List<Account> accounts = composite.getAccounts();
                for ( Account account : accounts )
                {
                    if ( account != null && account.getAccount() != null && account.getAccount().getAccountId() != null )
                    {
                        account.setComposite( composite );
                    }

                }
                List<Benchmark> benchmarks = composite.getBenchmarks();
                for ( Benchmark benchmark : benchmarks )
                {
                    if ( benchmark != null && benchmark.getBenchmark() != null
                            && benchmark.getBenchmark().getBenchmarkId() != null )
                    {
                        benchmark.setComposite( composite );
                    }
                }
                List<Disclosure> disclosures = composite.getDisclosures();
                for ( Disclosure disclosure : disclosures )
                {
                    if ( disclosure != null && disclosure.getDisclosure() != null
                            && disclosure.getDisclosure().getDisclosureId() != null )
                    {
                        disclosure.setComposite( composite );
                    }

                }
                List<Description> descriptions = composite.getDescriptions();
                for ( Description description : descriptions )
                {
                    if ( description != null && description.getCompositeShortDesc() != null )
                    {
                        description.setComposite( composite );
                    }
                }
                List<CompositeFee> fees = composite.getFees();
                for ( CompositeFee fee : fees )
                {
                    if ( fee != null && fee.getFeeSchedule().getFeeScheduleId() != null )
                    {
                        fee.setComposite( composite );
                    }
                }
                List<CompositeNote> notes = composite.getNotes();
                for ( CompositeNote note : notes )
                {
                    if ( note != null && note.getCompositeNote() != null )
                    {
                        note.setComposite( composite );
                    }
                }
                List<CompositeAudit> compositeAudits = composite.getReasonCodes();
                for ( CompositeAudit compositeAudit : compositeAudits )
                {
                    if ( compositeAudit != null && compositeAudit.getReasonId() != null
                            && compositeAudit.getReasonDesc() != null )
                    {
                        compositeAudit.setComposite( composite );
                        compositeAudit.setUserId( currentUser.getUserName() );
                        // compositeAudit.setCreateDate( new Date().toString()
                        // );
                    }
                }

                /* Removing null objects from the list */
                List<Category> categories1 = composite.getCategories();
                for ( Category category : categories1 )
                {
                    if ( category == null || category.getCategory() == null
                            || category.getCategory().getCategoryId() == null )
                    {
                        categories1.remove( category );
                    }

                }
                List<Account> accounts1 = composite.getAccounts();
                for ( Account account : accounts1 )
                {
                    if ( account == null || account.getAccount() == null || account.getAccount().getAccountId() == null )
                    {
                        accounts1.remove( account );
                    }
                }
                List<Benchmark> benchmarks1 = composite.getBenchmarks();
                for ( Benchmark benchmark : benchmarks1 )
                {
                    if ( benchmark == null || benchmark.getBenchmark() == null
                            || benchmark.getBenchmark().getBenchmarkId() == null )
                    {
                        benchmarks1.remove( benchmark );
                    }
                }
                List<Disclosure> disclosures1 = composite.getDisclosures();
                for ( Disclosure disclosure : disclosures1 )
                {
                    if ( disclosure == null || disclosure.getDisclosure() == null
                            || disclosure.getDisclosure().getDisclosureId() == null )
                    {
                        disclosures1.remove( disclosure );
                    }
                }
                List<Description> descriptions1 = composite.getDescriptions();
                for ( Description description : descriptions1 )
                {
                    if ( description == null || description.getCompositeShortDesc() == null )
                    {
                        descriptions1.remove( description );
                    }
                }

                List<CompositeFee> fees1 = composite.getFees();
                for ( CompositeFee fee : fees1 )
                {
                    if ( fee == null || fee.getFeeSchedule().getFeeScheduleId() == null )
                    {
                        fees1.remove( fee );
                    }
                }

                List<CompositeNote> notes1 = composite.getNotes();
                for ( CompositeNote note : notes1 )
                {
                    if ( note == null || note.getCompositeNote() == null )
                    {
                        notes1.remove( note );
                    }
                }

                List<CompositeAudit> reasonCodes1 = composite.getReasonCodes();
                for ( CompositeAudit compositeAudit : reasonCodes1 )
                {
                    if ( compositeAudit == null || compositeAudit.getReasonId() == null
                            || compositeAudit.getReasonDesc() == null )
                    {
                        reasonCodes1.remove( compositeAudit );
                    }
                }

                composite = compositeRepository.saveAndFlush( composite );
                response.setStatus( EnumResponseStatus.OK );
                response.setMessage( messageSource.getMessage( "NewCompositeCreated.MESSAGE", null, locale ) );
            }
        }
        catch ( Exception e )
        {
            CompositeController.logger.error( "Exception: " + e );
            response.setStatus( IConstants.EnumResponseStatus.ERROR );
            response.setMessage( e.getMessage() );
            if ( e instanceof NotAuthenticatedException )
            {
                response.setCallbackUrl( urlUtils.getBaseUrl() + "auth/login" );
            }
        }

        return response;
    }

    @SuppressWarnings( "unchecked" )
    @Loggable
    @RequestMapping( value = "/analytics/composites/list", method = RequestMethod.GET )
    public String showCompositeList( HttpServletRequest request, Model model, Locale locale )
    {

        User currentUser = utils.getAuthenticatedUser();
        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }

        try
        {
            List<Composite> composites = entityManager.createQuery( "SELECT c FROM Composite c" ).getResultList();
            model.addAttribute( "composites", composites );
        }
        catch ( Exception e )
        {
            CompositeController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
        }

        return "cms/Composite-List";

    }

    @SuppressWarnings( "unchecked" )
    @Loggable
    @RequestMapping( value = "/analytics/composite/{compositeId}/edit", method = RequestMethod.GET )
    public String editCompositeForm( @PathVariable Integer compositeId, Model model, Locale locale )
    {
        User currentUser = utils.getAuthenticatedUser();
        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }
        try
        {
            Composite composite = compositeRepository.findByCompositeId( compositeId );
            if ( composite != null )
            {
                List<Firm> firms = entityManager.createQuery( "SELECT f FROM Firm f" ).getResultList();
                List<Currency> currencies = entityManager.createQuery( "SELECT c FROM Currency c" ).getResultList();
                List<CompositeYears> years = entityManager.createQuery( "SELECT y FROM CompositeYears y" )
                        .getResultList();
                List<CategoryLKUP> categoryLKUP = entityManager.createQuery( "SELECT catg FROM CategoryLKUP catg" )
                        .getResultList();
                List<ReasonCodeLKUP> reasonCodeLKUP = entityManager.createQuery( "SELECT r FROM ReasonCodeLKUP r" )
                        .getResultList();
                List<CompositeTypes> compositeTypes = entityManager.createQuery( "SELECT ct FROM CompositeTypes ct" )
                        .getResultList();
                List<RiskFreeIndex> riskFreeIndexs = entityManager.createQuery( "SELECT ri FROM RiskFreeIndex ri" )
                        .getResultList();
                List<AccountLKUP> accounts = entityManager.createQuery( "SELECT comp FROM AccountLKUP comp" )
                        .getResultList();
                List<BenchmarkLKUP> benchmarkLKUP = entityManager.createQuery( "SELECT bench FROM BenchmarkLKUP bench" )
                        .getResultList();
                List<DisclosureLKUP> disclosureLKUP = entityManager.createQuery( "SELECT d FROM DisclosureLKUP d" )
                        .getResultList();

                model.addAttribute( "firms", firms );
                model.addAttribute( "currencies", currencies );
                model.addAttribute( "years", years );
                model.addAttribute( "categories", categoryLKUP );
                model.addAttribute( "reasonCodes", reasonCodeLKUP );
                model.addAttribute( "compositeTypes", compositeTypes );
                model.addAttribute( "riskFreeIndexs", riskFreeIndexs );
                model.addAttribute( "accounts", accounts );
                model.addAttribute( "benchmarks", benchmarkLKUP );
                model.addAttribute( "disclosures", disclosureLKUP );
                model.addAttribute( "composite", composite );
            }
        }
        catch ( Exception e )
        {
            CompositeController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
        }
        return "cms/EditComposite";
    }

    @ResponseBody
    @Loggable
    @Transactional
    @RequestMapping( value = "/analytics/composite/{compositeId}/edit", method = RequestMethod.POST, produces = "application/json" )
    public Response updateComposite( @PathVariable Integer compositeId, Composite composite, Model model, Locale locale )
    {
        User currentUser = utils.getAuthenticatedUserEx();
        Response response = new Response();

        try
        {
            if ( composite == null || composite.getCategories() == null || composite.getAccounts() == null
                    || composite.getBenchmarks() == null || composite.getDisclosures() == null
                    || composite.getDescriptions() == null || composite.getFees() == null
                    || composite.getNotes() == null || composite.getReasonCodes() == null )
            {
                throw new QSException( messageSource.getMessage( "Invalid_Input_Found", null, null ) );
            }
            if ( composite != null && compositeId != null )
            {
                /* setting compositeId to the objects */
                List<Category> categories = composite.getCategories();
                for ( Category category : categories )
                {
                    if ( category != null && category.getCategory() != null
                            && category.getCategory().getCategoryId() != null )
                    {
                        category.setComposite( composite );
                    }
                }
                List<Account> accounts = composite.getAccounts();
                for ( Account account : accounts )
                {
                    if ( account != null && account.getAccount() != null && account.getAccount().getAccountId() != null )
                    {
                        account.setComposite( composite );
                    }
                }
                List<Benchmark> benchmarks = composite.getBenchmarks();
                for ( Benchmark benchmark : benchmarks )
                {
                    if ( benchmark != null && benchmark.getBenchmark() != null
                            && benchmark.getBenchmark().getBenchmarkId() != null )
                    {
                        benchmark.setComposite( composite );
                    }
                }
                List<Disclosure> disclosures = composite.getDisclosures();
                for ( Disclosure disclosure : disclosures )
                {
                    if ( disclosure != null && disclosure.getDisclosure() != null
                            && disclosure.getDisclosure().getDisclosureId() != null )
                    {
                        disclosure.setComposite( composite );
                    }
                }
                List<Description> descriptions = composite.getDescriptions();
                for ( Description description : descriptions )
                {
                    if ( description != null && description.getCompositeShortDesc() != null )
                    {
                        description.setComposite( composite );
                    }
                }
                List<CompositeFee> fees = composite.getFees();
                for ( CompositeFee fee : fees )
                {
                    if ( fee != null && fee.getFeeSchedule().getFeeScheduleId() != null )
                    {
                        fee.setComposite( composite );
                    }
                }
                List<CompositeNote> notes = composite.getNotes();
                for ( CompositeNote note : notes )
                {
                    if ( note != null && note.getCompositeNote() != null )
                    {
                        note.setComposite( composite );
                    }
                }
                List<CompositeAudit> compositeAudits = composite.getReasonCodes();
                for ( CompositeAudit compositeAudit : compositeAudits )
                {
                    if ( compositeAudit != null && compositeAudit.getReasonId() != null
                            && compositeAudit.getReasonDesc() != null )
                    {
                        compositeAudit.setComposite( composite );
                        compositeAudit.setUserId( currentUser.getUserName() );
                        // compositeAudit.setModifiedDate( new Date().toString()
                        // );
                    }
                }

                /* Removing null objects from the list */
                List<Category> categories1 = composite.getCategories();
                for ( Category category : categories1 )
                {
                    if ( category == null || category.getCategory() == null
                            || category.getCategory().getCategoryId() == null )
                    {
                        categories1.remove( category );
                    }
                }
                List<Account> accounts1 = composite.getAccounts();
                for ( Account account : accounts1 )
                {
                    if ( account == null || account.getAccount() == null || account.getAccount().getAccountId() == null )
                    {
                        accounts1.remove( account );
                    }
                }
                List<Benchmark> benchmarks1 = composite.getBenchmarks();
                for ( Benchmark benchmark : benchmarks1 )
                {
                    if ( benchmark == null || benchmark.getBenchmark() == null
                            || benchmark.getBenchmark().getBenchmarkId() == null )
                    {
                        benchmarks1.remove( benchmark );
                    }
                }
                List<Disclosure> disclosures1 = composite.getDisclosures();
                for ( Disclosure disclosure : disclosures1 )
                {
                    if ( disclosure == null || disclosure.getDisclosure() == null
                            || disclosure.getDisclosure().getDisclosureId() == null )
                    {
                        disclosures1.remove( disclosure );
                    }
                }
                List<Description> descriptions1 = composite.getDescriptions();
                for ( Description description : descriptions1 )
                {
                    if ( description == null || description.getCompositeShortDesc() == null )
                    {
                        descriptions1.remove( description );
                    }
                }

                List<CompositeFee> fees1 = composite.getFees();
                for ( CompositeFee fee : fees1 )
                {
                    if ( fee == null || fee.getFeeSchedule().getFeeScheduleId() == null )
                    {
                        fees1.remove( fee );
                    }
                }

                List<CompositeNote> notes1 = composite.getNotes();
                for ( CompositeNote note : notes1 )
                {
                    if ( note == null || note.getCompositeNote() == null )
                    {
                        notes1.remove( note );
                    }
                }

                List<CompositeAudit> reasonCodes1 = composite.getReasonCodes();
                for ( CompositeAudit compositeAudit : reasonCodes1 )
                {
                    if ( compositeAudit == null || compositeAudit.getReasonId() == null
                            || compositeAudit.getReasonDesc() == null )
                    {
                        reasonCodes1.remove( compositeAudit );
                    }
                }
                composite = compositeRepository.saveAndFlush( composite );
                response.setStatus( EnumResponseStatus.OK );
                response.setMessage( messageSource.getMessage( "UpdateComposite.MESSAGE",
                        new Object[] { composite.getCompositeName() }, locale ) );
            }
        }
        catch ( Exception e )
        {
            CompositeController.logger.error( "Exception: " + e );
            response.setStatus( IConstants.EnumResponseStatus.ERROR );
            response.setMessage( e.getMessage() );
            if ( e instanceof NotAuthenticatedException )
            {
                response.setCallbackUrl( urlUtils.getBaseUrl() + "auth/login" );
            }
        }
        return response;
    }

    @SuppressWarnings( "unchecked" )
    @Loggable
    @RequestMapping( value = "/analytics/composites/accounts", method = RequestMethod.GET )
    public String cmsAccountsForm( HttpServletRequest request, Model model, Locale locale )
    {
        User currentUser = utils.getAuthenticatedUser();
        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }
        try
        {
            List<AccountLKUP> accounts = entityManager.createQuery( "SELECT comp FROM AccountLKUP comp" )
                    .getResultList();
            model.addAttribute( "accounts", accounts );
        }
        catch ( Exception e )
        {
            CompositeController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
        }
        return "cms/CMS-Accounts";
    }

    @SuppressWarnings( "unchecked" )
    @Loggable
    @RequestMapping( value = "/account/{accountId}/edit", method = RequestMethod.GET )
    public String editAccount( @PathVariable String accountId, Model model, Locale locale )
    {
        User currentUser = utils.getAuthenticatedUser();
        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }
        try
        {
            if ( accountId != null )
            {
                List<Firm> firms = entityManager.createQuery( "SELECT f FROM Firm f" ).getResultList();
                List<CategoryLKUP> categoryLKUPs = entityManager.createQuery( "SELECT catg FROM CategoryLKUP catg" )
                        .getResultList();
                List<Currency> currencies = entityManager.createQuery( "SELECT c FROM Currency c" ).getResultList();

                List<ReasonCodeLKUP> reasonCodes = entityManager.createQuery( "SELECT r FROM ReasonCodeLKUP r" )
                        .getResultList();
                AccountLKUP account = compositeRepository.findByAccountId( accountId );
                List<Composite> composites = compositeRepository.findCompositesByAccountId( accountId );

                List<AccountDTO> accountDTOs = new ArrayList<>();
                List<BenchmarkDTO> benchmarkDTOs = new ArrayList<>();

                for ( Composite composite : composites )
                {
                    AccountDTO accountDTO = new AccountDTO();
                    BenchmarkDTO benchmarkDTO = new BenchmarkDTO();

                    accountDTO.setCompositeCode( composite.getCompositeCode() );
                    accountDTO.setCompositeName( composite.getCompositeName() );

                    for ( Account acc : composite.getAccounts() )
                    {
                        accountDTO.setStartDate( acc.getAccountStartDate() );
                        accountDTO.setEndDate( acc.getAccountEndDate() );
                    }

                    for ( Benchmark bench : composite.getBenchmarks() )
                    {
                        benchmarkDTO.setBenchmarkId( bench.getBenchmark().getBenchmarkId() );
                        benchmarkDTO.setBenchmarkName( bench.getBenchmark().getBenchmarkName() );
                        benchmarkDTO.setBenchmarkType( bench.getBenchmark().getBenchmarkType() );
                        benchmarkDTO.setStartDate( bench.getBenchmarkStartDate() );
                        benchmarkDTO.setEndDate( bench.getBenchmarkEndDate() );
                        benchmarkDTO.setCountry( bench.getCurrency().getCurrencyLocation() );
                    }
                    accountDTOs.add( accountDTO );
                    benchmarkDTOs.add( benchmarkDTO );
                }

                model.addAttribute( "account", account );
                model.addAttribute( "firms", firms );
                model.addAttribute( "currencies", currencies );
                model.addAttribute( "reasonCodes", reasonCodes );
                model.addAttribute( "categories", categoryLKUPs );
                model.addAttribute( "accounts", accountDTOs );
                model.addAttribute( "benchmarks", benchmarkDTOs );

            }
        }
        catch ( Exception e )
        {
            CompositeController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
        }
        return "cms/account";
    }

    @ResponseBody
    @Loggable
    @Transactional
    @RequestMapping( value = "/account/{accountId}/edit", method = RequestMethod.POST )
    public Response updateAccount( @PathVariable String accountId, AccountLKUP account, Model model, Locale locale )
    {
        User currentUser = utils.getAuthenticatedUserEx();
        Response response = new Response();
        try
        {
            if ( account == null || accountId == null )
            {
                throw new QSException( messageSource.getMessage( "Invalid_Input_Found", null, null ) );
            }

            // Setting accountId for an account
            List<AccountNote> notes = account.getNotes();
            for ( AccountNote note : notes )
            {
                if ( note != null && note.getAccountNote() != null )
                {
                    note.setAccount( account );
                }
            }
            AccountAudit accountAudit = account.getAccountAudit();
            if ( accountAudit != null )
            {
                accountAudit.setAccount( account );
                accountAudit.setUserId( currentUser.getUserName() );
                // accountAudit.setCreateDate( DateTime.now().toString() );
            }

            // Removing null account objects
            List<AccountNote> accountNotes = account.getNotes();
            for ( AccountNote accountNote : accountNotes )
            {
                if ( accountNote == null || accountNote.getAccountNote() == null )
                {
                    accountNotes.remove( accountNote );
                }
            }

            account = entityManager.merge( account );
            response.setStatus( EnumResponseStatus.OK );
            response.setMessage( messageSource.getMessage( "UpdateAccount.MESSAGE",
                    new Object[] { account.getAccountName() }, locale ) );

        }
        catch ( Exception e )
        {
            CompositeController.logger.error( "Exception: " + e );
            response.setStatus( IConstants.EnumResponseStatus.ERROR );
            response.setMessage( e.getMessage() );
            if ( e instanceof NotAuthenticatedException )
            {
                response.setCallbackUrl( urlUtils.getBaseUrl() + "auth/login" );
            }
        }

        return response;
    }

    @SuppressWarnings( "unchecked" )
    @Loggable
    @RequestMapping( value = "/analytics/composites/firms", method = RequestMethod.GET )
    public String cmsFirmsForm( HttpServletRequest request, Model model, Locale locale )
    {

        User currentUser = utils.getAuthenticatedUser();
        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }

        try
        {
            List<Firm> firms = entityManager.createQuery( "SELECT f FROM Firm f" ).getResultList();
            List<Currency> currencies = entityManager.createQuery( "SELECT c FROM Currency c" ).getResultList();
            List<ReasonCodeLKUP> reasonCodes = entityManager.createQuery( "SELECT r FROM ReasonCodeLKUP r" )
                    .getResultList();
            List<Location> locations = entityManager.createQuery( "SELECT loc FROM Location loc" ).getResultList();
            List<DisclosureLKUP> disclosures = entityManager.createQuery( "SELECT d FROM DisclosureLKUP d" )
                    .getResultList();

            model.addAttribute( "currencies", currencies );
            model.addAttribute( "reasonCodes", reasonCodes );
            model.addAttribute( "firms", firms );
            model.addAttribute( "locations", locations );
            model.addAttribute( "disclosures", disclosures );
            model.addAttribute( "firmDTO", new FirmDTO() );
            model.addAttribute( "disclosureAssociationDTO", new DisclosureAssociationDTO() );
        }
        catch ( Exception e )
        {
            CompositeController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
        }

        return "cms/CMS-Firms";
    }

    @ResponseBody
    @Transactional
    @Loggable
    @RequestMapping( value = "/analytics/firm/create", method = RequestMethod.POST )
    public Response saveNewFirm( FirmDTO firmDTO, Model model, Locale locale )
    {

        User currentUser = utils.getAuthenticatedUserEx();
        Response response = new Response();
        try
        {
            if ( firmDTO == null || firmDTO.getFirms() == null )
            {
                throw new QSException( messageSource.getMessage( "Invalid_Input_Found", null, null ) );
            }

            List<Firm> firms = firmDTO.getFirms();
            // Setting firmId for a firm
            for ( Firm firm : firms )
            {
                if ( firm != null && firm.getFirmId() != null && firm.getFirmName() != null )
                {
                    FirmAudit firmAudit = new FirmAudit();
                    firmAudit.setReasonId( firmDTO.getReasonId() );
                    firmAudit.setReasonDesc( firmDTO.getReasonDesc() );
                    firmAudit.setFirm( firm );
                    firmAudit.setUserId( currentUser.getUserName() );
                    firm.setFirmAudit( firmAudit );
                    entityManager.persist( firm );
                }
            }
            response.setStatus( EnumResponseStatus.OK );
            response.setMessage( messageSource.getMessage( "NewFirmCreated.MESSAGE", null, locale ) );
        }
        catch ( Exception e )
        {
            CompositeController.logger.error( "Exception: " + e );
            response.setStatus( IConstants.EnumResponseStatus.ERROR );
            response.setMessage( e.getMessage() );
            if ( e instanceof NotAuthenticatedException )
            {
                response.setCallbackUrl( urlUtils.getBaseUrl() + "auth/login" );
            }
        }
        return response;
    }

    @ResponseBody
    @Transactional
    @Loggable
    @RequestMapping( value = "/analytics/disclosure/associate", method = RequestMethod.POST )
    public Response saveDisclosureAssociation( DisclosureAssociationDTO disclosureAssociationDTO, Model model,
            Locale locale )
    {

        User currentUser = utils.getAuthenticatedUserEx();
        Response response = new Response();
        try
        {
            if ( disclosureAssociationDTO == null || disclosureAssociationDTO.getDisclosureAssociations() == null )
            {
                throw new QSException( messageSource.getMessage( "Invalid_Input_Found", null, null ) );
            }

            List<DisclosureAssociation> disclosureAssociations = disclosureAssociationDTO.getDisclosureAssociations();
            // Setting firmId for a firm
            for ( DisclosureAssociation disclosureAssociation : disclosureAssociations )
            {
                if ( disclosureAssociation != null && disclosureAssociation.getFirmId() != null
                        && disclosureAssociation.getDisclosureId() != null )
                {
                    AssociationAudit associationAudit = new AssociationAudit();
                    associationAudit.setReasonId( disclosureAssociationDTO.getReasonId() );
                    associationAudit.setReasonDesc( disclosureAssociationDTO.getReasonDesc() );
                    associationAudit.setDisclosureAssociation( disclosureAssociation );
                    associationAudit.setUserId( currentUser.getUserName() );
                    disclosureAssociation.setAssociationAudit( associationAudit );
                    entityManager.persist( disclosureAssociation );
                }
            }
            response.setStatus( EnumResponseStatus.OK );
            response.setMessage( messageSource.getMessage( "DisclosureAssociated.MESSAGE", null, locale ) );
        }
        catch ( Exception e )
        {
            CompositeController.logger.error( "Exception: " + e );
            response.setStatus( IConstants.EnumResponseStatus.ERROR );
            response.setMessage( e.getMessage() );
            if ( e instanceof NotAuthenticatedException )
            {
                response.setCallbackUrl( urlUtils.getBaseUrl() + "auth/login" );
            }
        }
        return response;
    }

    @Loggable
    @RequestMapping( value = "/analytics/composites/benchmarks", method = RequestMethod.GET )
    public String cmsBenchmarksForm( HttpServletRequest request, Model model, Locale locale )
    {

        User currentUser = utils.getAuthenticatedUser();
        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }

        try
        {
            // model.addAttribute( "dataImportDefinition", dataImportDefinition
            // );
        }
        catch ( Exception e )
        {
            CompositeController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
        }

        return "cms/CMS-Benchmarks";

    }

    @SuppressWarnings( "unchecked" )
    @Loggable
    @RequestMapping( value = "/analytics/composites/disclosure", method = RequestMethod.GET )
    public String cmsDisclousureForm( HttpServletRequest request, Model model, Locale locale )
    {

        User currentUser = utils.getAuthenticatedUser();
        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }

        try
        {
            List<ReasonCodeLKUP> reasonCodes = entityManager.createQuery( "SELECT r FROM ReasonCodeLKUP r" )
                    .getResultList();
            model.addAttribute( "disclosureDTO", new DisclosureDTO() );
            model.addAttribute( "reasonCodes", reasonCodes );
        }
        catch ( Exception e )
        {
            CompositeController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
        }

        return "cms/CMS-Disclosure";

    }

    @ResponseBody
    @Loggable
    @Transactional
    @RequestMapping( value = "/disclosure/{disclosureType}/create", method = RequestMethod.POST, produces = "application/json" )
    public Response saveNewDisclosure( @PathVariable String disclosureType, DisclosureDTO disclosureDTO, Model model,
            Locale locale )
    {
        User currentUser = utils.getAuthenticatedUserEx();
        Response response = new Response();
        try
        {
            if ( disclosureType == null || disclosureDTO == null || disclosureDTO.getDisclosures() == null
                    || disclosureDTO.getReasonId() == null || disclosureDTO.getReasonDesc() == null )
            {
                throw new QSException( messageSource.getMessage( "Invalid_Input_Found", null, locale ) );
            }
            List<DisclosureLKUP> disclosures = disclosureDTO.getDisclosures();
            // Setting disclosureId for a disclosure
            for ( DisclosureLKUP disclosure : disclosures )
            {
                if ( disclosure != null && disclosure.getDisclosureId() != null
                        && disclosure.getDisclosureName() != null )
                {
                    DisclosureAudit disclosureAudit = new DisclosureAudit();
                    disclosureAudit.setReasonId( disclosureDTO.getReasonId() );
                    disclosureAudit.setReasonDesc( disclosureDTO.getReasonDesc() );
                    disclosureAudit.setDisclosure( disclosure );
                    disclosureAudit.setUserId( currentUser.getUserName() );
                    disclosure.setDisclosureType( disclosureType );
                    disclosure.setDisclosureAudit( disclosureAudit );
                    entityManager.persist( disclosure );
                }
            }
            response.setStatus( EnumResponseStatus.OK );
            response.setMessage( messageSource.getMessage( "NewDisclosureCreated.MESSAGE", null, locale ) );

        }
        catch ( Exception e )
        {
            CompositeController.logger.error( "Exception: " + e );
            response.setStatus( IConstants.EnumResponseStatus.ERROR );
            response.setMessage( e.getMessage() );
            if ( e instanceof NotAuthenticatedException )
            {
                response.setCallbackUrl( urlUtils.getBaseUrl() + "auth/login" );
            }
        }

        return response;
    }

    @Loggable
    @RequestMapping( value = "/analytics/composites/preferences", method = RequestMethod.GET )
    public String cmsPreferencesForm( HttpServletRequest request, Model model, Locale locale )
    {
        User currentUser = utils.getAuthenticatedUser();
        if ( currentUser == null )
        {
            return urlUtils.getBaseUrl() + "auth/login";
        }
        try
        {
            // model.addAttribute( "dataImportDefinition", dataImportDefinition
            // );
        }
        catch ( Exception e )
        {
            CompositeController.logger.error( "Exception: " + e );
            model.addAttribute( "error", e.getMessage() );
        }

        return "cms/CMS-Preferences";
    }

}
