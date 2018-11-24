/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.dto;

import java.time.ZonedDateTime;
import java.util.List;

/**
 *
 * @author Martin Piatka
 */

public class LoanDTO {

    private long id;
    private CustomerDTO customer;
    private ZonedDateTime timestamp;
    private List<LoanItemDTO> items;

}
