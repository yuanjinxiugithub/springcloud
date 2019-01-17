package com.springcloud.serverauth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: YuanJinXiu
 * @Description: 公共的返回结果
 * @Date: 2019/1/9 17:12
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 使用构建者模式来使用bean
public class JsonResult  implements java.io.Serializable  {

    private static final long serialVersionUID = -6798134478062697315L;
    /**
     * 状态码
     */
    private String status;
    /**
     * 内容
     */
    private String message;
    /**
     * 数据
     */
    private Object data;

}