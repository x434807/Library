/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.servicelayer.config;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.PersistenceApplicationContext;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.BookDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.LoanDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Book;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Customer;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Loan;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade.CustomerFacade;
import cz.muni.fi.pa165.skupina06.team01.servicelayer.service.CustomerServiceImpl;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author Andrej Sokolík
 */
@Configuration
@Import(PersistenceApplicationContext.class)
@ComponentScan(basePackageClasses = {
    CustomerServiceImpl.class,
    /*
    CustomerFacadeImpl.class,
    BookServiceImpl.class, 
    BookFacadeImpl.class,
    LoanServiceImpl.class,
    LoanFacadeImpl.class,
    */})
public class ServiceConfig {

    @Bean
    public Mapper dozer() {
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }
    
    public class DozerCustomConfig extends BeanMappingBuilder {
		@Override
		protected void configure() {
			mapping(Customer.class, CustomerDTO.class);
			mapping(Book.class, BookDTO.class);
			mapping(Loan.class, LoanDTO.class);
		}
	}
}