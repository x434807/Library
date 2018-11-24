package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dao.LoanDAO;
import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Loan;
import org.dozer.inject.Inject;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    @Inject
    private LoanDAO loanDAO;


    @Override
    public Loan findById(Long id) throws DataAccessException, IllegalArgumentException {
        if(id == null){
            throw new IllegalArgumentException("Id cannot be null");
        }

        return loanDAO.findById(id);
    }

    @Override
    public List<Loan> findAll() throws DataAccessException {
        return loanDAO.findAll();
    }

    @Override
    public void create(Loan loan) throws DataAccessException, IllegalArgumentException {
        if(loan == null){
            throw new IllegalArgumentException("Loan cannot be null");
        }

        loanDAO.create(loan);
    }

    @Override
    public void remove(Loan loan) throws DataAccessException, IllegalArgumentException {
        if(loan == null){
            throw new IllegalArgumentException("Loan cannot be null");
        }

        loanDAO.remove(loan);
    }

    @Override
    public void update(Loan loan) throws DataAccessException, IllegalArgumentException {
        if(loan == null){
            throw new IllegalArgumentException("Loan cannot be null");
        }

        loanDAO.update(loan);
    }
}
