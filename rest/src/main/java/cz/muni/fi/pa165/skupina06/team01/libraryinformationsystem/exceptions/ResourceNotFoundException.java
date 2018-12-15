/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Andrej Sokolík
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason="The requested resource was not found")
public class ResourceNotFoundException extends RuntimeException {
    
} 
