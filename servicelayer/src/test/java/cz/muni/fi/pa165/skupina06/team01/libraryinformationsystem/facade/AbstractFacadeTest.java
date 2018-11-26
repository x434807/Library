/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade;

import javax.inject.Inject;

import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.config.ServiceConfig;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service.BeanMappingService;


/**
 *
 * @author Andrej Sokolík
 */

@ContextConfiguration(classes = ServiceConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractFacadeTest {

    @Mock
    @Inject
    protected BeanMappingService beanMappingService;

    public static final Object unwrapProxy(Object bean) throws Exception {
        // If the given object is a proxy, set the return value as the object
        // being proxied, otherwise return the given object.
        if (AopUtils.isAopProxy(bean) && bean instanceof Advised) {
            Advised advised = (Advised) bean;
            bean = advised.getTargetSource().getTarget();
        }
        return bean;
    }
    
}