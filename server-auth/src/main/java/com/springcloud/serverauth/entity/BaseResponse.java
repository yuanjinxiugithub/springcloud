package com.springcloud.serverauth.entity;

/**
 * @Author: HanLong
 * @Date: Create in 2018/3/18 14:59
 * @Description:
 */
public class BaseResponse {

    public BaseResponse(Object content) {
        this.content = content;
    }

    private Object content;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
