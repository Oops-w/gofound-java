package com.github.w.mode;

/**
 * @author wsy
 * @date 2022/11/5 4:53 PM
 * @Description 配置以后，符合条件的关键词将会被preTag和postTag包裹
 *
 */
public class HighlightModel {
    /**
     * 关键词前缀
     */
    private String preTag;

    /**
     * 关键词后缀
     */
    private String postTag;

    public String getPreTag() {
        return preTag;
    }

    public void setPreTag(String preTag) {
        this.preTag = preTag;
    }

    public String getPostTag() {
        return postTag;
    }

    public void setPostTag(String postTag) {
        this.postTag = postTag;
    }
}
