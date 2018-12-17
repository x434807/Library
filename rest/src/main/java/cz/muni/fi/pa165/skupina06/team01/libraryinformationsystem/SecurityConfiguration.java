/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem;

import javax.inject.Inject;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;

/**
 *
 * @author Anry
 */
@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = {CustomAuthenticationProvider.class})
@Import(RestConfiguration.class)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Inject
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Inject
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

   // @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint).and()
                .authorizeRequests()

                .antMatchers(HttpMethod.GET, "/rest/house").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/rest/house").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/rest/house").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.DELETE, "/rest/house").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/rest/house/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/rest/house/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/rest/house/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/rest/house/*").hasRole("ADMIN")


                .antMatchers(HttpMethod.GET, "/rest/ghost").hasAnyRole("ADMIN", "USER")
                .antMatchers(HttpMethod.PUT, "/rest/ghost").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/rest/ghost").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/rest/ghost").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/rest/ability").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/rest/ability").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/rest/ability").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/rest/ability").hasRole("ADMIN")

                .antMatchers(HttpMethod.GET, "/rest/person").hasAnyRole("ADMIN", "USER")

                .antMatchers("/js/**").permitAll()
                .antMatchers("/partials/**").permitAll()
                .antMatchers("/index.html").permitAll()

                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login.html").permitAll().and()
                .logout().logoutUrl("/logout.html").logoutSuccessUrl("/index.html?logout").permitAll().and().csrf().disable();
    }

}
