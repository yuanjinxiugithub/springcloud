package com.springcloud.serverauth.util;

import com.springcloud.serverauth.entity.JsonResult;
import com.springcloud.serverauth.entity.ValidateEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: lp
 * @Date: 2018/7/2 13:49
 * @Description: 构建返回信息
 */
public class ResponseMessageUtil {
    /**
     * 返回错误信息
     *
     * @param fieldErrors
     * @return
     */
    public static Map<String, ValidateEntity> make(List<FieldError> fieldErrors) {
        Map<String, ValidateEntity> errors = new HashMap<>();
        fieldErrors.forEach(errField ->
                errors.put(errField.getField(),
                        ValidateEntity.create(
                                errField.getField(),
                                errField.getObjectName(),
                                errField.getCode(),
                                errField.getDefaultMessage())));
        return errors;
    }

    /**
     * 返回公共失败信息
     *
     * @param code
     * @param content
     * @param data
     * @return
     * @author lp
     */
    public static ResponseEntity<Object> fail(String code, String content, Object data) {
        return ResponseEntity.badRequest().body(JsonResult.builder().status(code).message(content).data(data).build());
    }

    /**
     * 绑定参数错误时，返回错误信息
     *
     * @param fieldErrors
     * @return
     * @author lp
     */
    public static ResponseEntity<Object> fail(List<FieldError> fieldErrors) {
        return ResponseEntity.badRequest().body(make(fieldErrors));
    }

    /**
     * 返回错误信息
     *
     * @param jsonResult
     * @return
     * @author lp
     */
    public static ResponseEntity<JsonResult> fail(JsonResult jsonResult) {
        return ResponseEntity.badRequest().body(jsonResult);
    }

    /**
     * 返回成功信息
     *
     * @param code
     * @param content
     * @param data
     * @return
     * @author lp
     */
    public static ResponseEntity<JsonResult> success(String code, String content, Object data) {
        return ResponseEntity.ok().body(JsonResult.builder().status(code).message(content).data(data).build());
    }

    /**
     * 接口返回成功信息
     * @param code 状态码
     * @param message 返回信息
     * @return
     */
    public static ResponseEntity<JsonResult> success(String code, String message) {
        return ResponseEntity.ok().body(JsonResult.builder().status(code).message(message).build());
    }

}
