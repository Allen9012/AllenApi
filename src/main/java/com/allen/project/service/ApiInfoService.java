package com.allen.project.service;

import com.allen.project.model.entity.ApiInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author Allen
* @description 针对表【api_info(接口信息)】的数据库操作Service
* @createDate 2023-01-01 20:11:32
*/
public interface ApiInfoService extends IService<ApiInfo> {
    /**
     * 校验参数是否合法
     * @param apiInfo
     * @param add
     */
    void validApiInfo(ApiInfo apiInfo, boolean add);
}
