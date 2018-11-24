/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.skupina06.team01.servicelayer.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Andrej Sokolík
 */
@Service
public class BeanMappingServiceImpl implements BeanMappingService{
    @Autowired
    private Mapper mapper;

    @Override
    public <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass) {
        return objects.stream().map(o -> mapper.map(o, mapToClass)).collect(Collectors.toList());
    }

    @Override
    public <T> T mapTo(Object u, Class<T> mapToClass) {
        return mapper.map(u, mapToClass);
    }

    @Override
    public Mapper getMapper() {
        return mapper;
    }
}
