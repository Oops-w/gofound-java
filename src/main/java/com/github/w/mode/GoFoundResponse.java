package com.github.w.mode;

/**
 * @author wsy
 * @date 2022/11/5 3:29 PM
 * @Description
 */
public class GoFoundResponse {
    private String state;
    private String message;
    private Object data;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
