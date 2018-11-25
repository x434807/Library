/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.config.ServiceConfig;
import org.dozer.inject.Inject;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;


/**
 *
 * @author Andrej Sokolík
 */
@ContextConfiguration(classes = ServiceConfig.class)
public class AbstractServiceTest extends AbstractTestNGSpringContextTests {

    @Inject
    protected BeanMappingService beanMappingService;
    
}