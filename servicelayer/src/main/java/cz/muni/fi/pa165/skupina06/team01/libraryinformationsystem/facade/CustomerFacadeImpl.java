/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Loan;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.Role;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade.CustomerFacade;
import java.util.List;

/**
 *
 * @author Anry
 */
public class CustomerFacadeImpl implements CustomerFacade{

    @Override
    public cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO registerCustomer(cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeCustomer(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateCustomer(cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO> getAllPeople() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean authenticate(String login, String password) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAllowed(cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO person, List<Role> accessConstraints) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO findCustomerById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO> findCustomerByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO findCustomerByLogin(String login) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Loan> findCustomersLoans(cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.CustomerDTO customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
