/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade.CustomerFacade;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 *
 * @author Anry
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Inject
    private CustomerFacade customerFacade;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        if (customerFacade.authenticate(username, password)) {
            CustomerDTO user = customerFacade.findCustomerByLogin(username);
            
            List<GrantedAuthority> grantedAuths = new ArrayList<>();
            String str;
            if(user.isIsAdmin()){
                str = "ADMIN";
            }else{
                str = "USER";
            }
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_" + str));

            return new UsernamePasswordAuthenticationToken(username, password, grantedAuths);
        } else {
            throw new BadCredentialsException("Invalid password.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}