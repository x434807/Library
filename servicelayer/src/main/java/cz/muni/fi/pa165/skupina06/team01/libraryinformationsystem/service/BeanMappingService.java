/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.libraryinformationsystem.service;

import java.util.Collection;
import java.util.List;
import org.dozer.Mapper;
/**
 *
 * @author Andrej Sokolík
 */
public interface BeanMappingService {
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
    public <T> T mapTo(Object u, Class<T> mapToClass);
    public Mapper getMapper();
}
