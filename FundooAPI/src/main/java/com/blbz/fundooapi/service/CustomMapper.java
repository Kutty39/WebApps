package com.blbz.fundooapi.service;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomMapper<T, E,R> {
    List<E> mapper(List<T> list,E entity,R repo);
}
