package com.github.w.mode;

import java.util.Map;

/**
 * @author wsy
 * @date 2022/11/5 3:31 PM
 * @Description
 */
public class IndexModel {
    private Integer id;
    private String text;
    private Map<String, Object> document;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Object> getDocument() {
        return document;
    }

    public void setDocument(Map<String, Object> document) {
        this.document = document;
    }
}
