package com.xiezhenyu.utils;

import java.lang.reflect.Field;

/**
 * @author Tim
 */
public class DoToVo<D,V> {
    private Class<D> doClass;
    private Class<V> voClass;
    public V getVo(D d){
        Field[] voFields = voClass.getDeclaredFields();
        for(Field f : voFields){

        }
        return null;
    }
}
