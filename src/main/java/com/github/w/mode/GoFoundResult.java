package com.github.w.mode;

import java.util.List;

/**
 * @author wsy
 * @date 2022/11/5 6:22 PM
 * @Description
 */
public class GoFoundResult <T> {
    private Double time;
    private Integer total;
    private Integer pageCount;
    private Integer page;
    private Integer limit;
    private List<Document<T>> documents;

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<Document<T>> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document<T>> documents) {
        this.documents = documents;
    }
}
