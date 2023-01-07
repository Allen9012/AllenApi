package com.allen.project.model.dto.apiinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 * 创建请求的封装对象
 * @TableName product
 */
@Data
public class ApiInfoAddRequest implements Serializable {
    /**
     * 接口名称
     */
    private String name;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 请求类型
     */
    private String method;

    /**
     * 描述
     */
    private String description;
    /**
     * 请求参数
     */
    private String requestParams;
    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 响应头
     */
    private String responseHeader;

}