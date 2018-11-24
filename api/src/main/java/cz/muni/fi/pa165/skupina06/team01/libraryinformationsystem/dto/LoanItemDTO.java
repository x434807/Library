package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto;

import cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.enums.BookCondition;

/**
 *
 * @author Martin Piatka
 */

public class LoanItemDTO {
    private long id;

    private LoanDTO loan;

    private BookDTO book;

    private BookCondition borrowCondition;

    private BookCondition returnCondition;
}
