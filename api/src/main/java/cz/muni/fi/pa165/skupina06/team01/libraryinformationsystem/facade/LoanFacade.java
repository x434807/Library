package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.facade;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto.LoanDTO;

import java.util.List;

/**
 *
 * @autor Martin Piatka
 */

public interface LoanFacade {
    LoanDTO findById(Long id);
    List<LoanDTO> findAll();
    void create(LoanDTO loan);
    void remove(LoanDTO loan);
    void update(LoanDTO loan);
}
