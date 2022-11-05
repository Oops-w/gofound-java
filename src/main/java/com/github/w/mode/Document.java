package com.github.w.mode;

/**
 * @author wsy
 * @date 2022/11/5 6:23 PM
 * @Description
 */
public class Document <T> {
    private Integer id;
    private String text;
    private Double score;
    private T document;

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

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public T getDocument() {
        return document;
    }

    public void setDocument(T document) {
        this.document = document;
    }
}
