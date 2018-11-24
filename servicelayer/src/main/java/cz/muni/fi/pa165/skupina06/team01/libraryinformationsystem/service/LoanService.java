package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.entity.Loan;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author Martin Piatka
 */

@Service
public interface LoanService {
    Loan findById(Long id) throws DataAccessException, IllegalArgumentException;
    List<Loan> findAll() throws DataAccessException;
    void create(Loan loan) throws DataAccessException, IllegalArgumentException;
    void remove(Loan loan) throws DataAccessException, IllegalArgumentException;
    void update(Loan loan) throws DataAccessException, IllegalArgumentException;

}
