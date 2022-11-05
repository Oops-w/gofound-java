package com.github.w.enums;

/**
 * @author wsy
 * @date 2022/11/5 4:55 PM
 * @Description
 */
public enum OrderEnum {
    ASC("asc"),
    DESC("desc"),
    ;
    private final String desc;

    OrderEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
