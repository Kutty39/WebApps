package com.blbz.fundooapi.serviceimpl;

import com.blbz.fundooapi.service.CustomMapper;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Getter
@Setter
public class CustomMapperImpl<T,E,R> implements CustomMapper<T, E, R > {
    private R repo;
    private Class<?> c;
    @Override
    public List<E> mapper(List<T> list, E entity, R repo) {
        this.repo=repo;
        c=repo.getClass();
        return list.stream().map(this::getEntity).collect(Collectors.toList());
    }

    public E getEntity(T val){
        Class<?> c=repo.getClass();
        List<Method> methodList=Arrays.stream(c.getDeclaredMethods()).filter(method -> method.getName().contains("findByUniqKey")).collect(Collectors.toList());
        try {
            return (E) methodList.get(0).invoke(repo.getClass().newInstance(),val);
        } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
