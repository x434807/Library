package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.Config;
import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.ApiContract;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer{


    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        servletContext.addListener(RequestContextListener.class);
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootWebContext.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{ApiContract.REST + "/*"};
    }
}
