/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.config.SampleDataConfiguration;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.config.ServiceConfig;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.controllers.LoansController;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import javax.validation.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author Anry
 */

@Configuration
@EnableWebMvc
@Import({ServiceConfig.class, SampleDataConfiguration.class})
@ComponentScan(basePackageClasses = {LoansController.class})
public class RestConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJackson2HttpMessageConverter());
    }

    private MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH));

        /*objectMapper.addMixIn(AbilityDTO.class, AbilityDTOMixin.class);
        objectMapper.addMixIn(GhostDTO.class, GhostDTOMixin.class);
        objectMapper.addMixIn(HouseDTO.class, HouseDTOMixin.class);
        objectMapper.addMixIn(PersonDTO.class, PersonDTOMixin.class);*/

        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }

}