package com.springcloud.serverauth.entity;

import lombok.Data;

/**
 * @Auther: lp
 * @Date: 2018/7/2 13:51
 * @Description:
 */
@Data
public class ValidateEntity {

    private String field;
    private String errCode;
    private String shortDescribe;
    private String errMessage;

    public static ValidateEntity create(String field, String errCode, String shortDescribe, String errMessage) {
        ValidateEntity validateEntity = new ValidateEntity(field, errCode, shortDescribe, errMessage);
        return validateEntity;
    }

    public ValidateEntity() {
    }

    public ValidateEntity(String field, String errCode, String shortDescribe, String errMessage) {
        this.field = field;
        this.errCode = errCode;
        this.shortDescribe = shortDescribe;
        this.errMessage = errMessage;
    }
}
