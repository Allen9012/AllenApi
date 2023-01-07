package com.allen.project.model.dto.apiinfo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 更新请求
 * 不允许修改创建人
 * @TableName product
 */
@Data
public class ApiInfoUpdateRequest implements Serializable {


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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
     * 接口状态（0 - 关闭 1 - 开启）
     */
    private Integer status;
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