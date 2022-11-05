package com.github.w.mode;

/**
 * @author wsy
 * @date 2022/11/5 4:52 PM
 * @Description
 */
public class QueryModel {
    /**
     * 查询的关键词，都是or匹配
     */
    private String query;

    /**
     * 页码，默认为1
     */
    private Integer page;

    /**
     * 返回的文档数量，默认为100，没有最大限制，最好不要超过1000，超过之后速度会比较慢，内存占用会比较多
     */
    private Integer limit;

    /**
     * {@link  com.github.w.enums.OrderEnum}
     * 排序方式，取值asc和desc，默认为desc，按id排序，然后根据结果得分排序
     */
    private String order;

    /**
     * 关键字高亮，相对text字段中的文本
     */
    private HighlightModel highlight;

    /**
     * 根据文档的字段计算分数，然后再进行排序，例如：score+[document.hot]*10，表达式中score为关键字的分数,document.hot为document中的hot字段
     */
    private String scoreExp;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public HighlightModel getHighlight() {
        return highlight;
    }

    public void setHighlight(HighlightModel highlight) {
        this.highlight = highlight;
    }

    public String getScoreExp() {
        return scoreExp;
    }

    public void setScoreExp(String scoreExp) {
        this.scoreExp = scoreExp;
    }
}
