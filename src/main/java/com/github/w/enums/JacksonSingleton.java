package com.github.w.enums;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author wsy
 * @date 2022/11/5 2:41 PM
 * @Description
 */
public enum JacksonSingleton {
    JACKSON(new ObjectMapper()),
    ;
    private final ObjectMapper instance;

    JacksonSingleton(ObjectMapper instance) {
        this.instance = instance;
    }

    public ObjectMapper getInstance() {
        return instance;
    }
}
