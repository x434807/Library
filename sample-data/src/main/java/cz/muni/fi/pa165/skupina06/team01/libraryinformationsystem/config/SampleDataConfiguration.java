package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.config;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.data.Initializer;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.data.InitializerImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * @author Martin Piatka
 *
 */

@Configuration
@Import(ServiceConfig.class)
@ComponentScan(basePackageClasses = {InitializerImpl.class})
public class SampleDataConfiguration {

    @Inject
    private Initializer initializer;

    @PostConstruct
    public void dataLoading() {
        initializer.loadData();
    }

}
