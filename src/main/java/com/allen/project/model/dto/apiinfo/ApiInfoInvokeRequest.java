package com.allen.project.model.dto.apiinfo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description: 封装Invoke接口调用请求
 * User: Allen
 * Date: 2023-01-07
 * Time: 15:12
 */
@Data
public class ApiInfoInvokeRequest  implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户请求参数,区别于api的字段
     */
    private String userRequestParams;

    private static final long serialVersionUID = 1L;

}
