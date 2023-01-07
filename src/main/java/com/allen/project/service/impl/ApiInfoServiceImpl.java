package com.allen.project.service.impl;

import com.allen.project.common.ErrorCode;
import com.allen.project.exception.BusinessException;
import com.allen.project.mapper.ApiInfoMapper;
import com.allen.project.model.entity.ApiInfo;
import com.allen.project.service.ApiInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author Allen
* @description 针对表【api_info(接口信息)】的数据库操作Service实现
* @createDate 2023-01-01 20:11:32
*/
@Service
public class ApiInfoServiceImpl extends ServiceImpl<ApiInfoMapper, ApiInfo>
    implements ApiInfoService {

    @Override
    public void validApiInfo(ApiInfo apiInfo, boolean add) {
        if (apiInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String description = apiInfo.getDescription();
        String name = apiInfo.getName();
        String url = apiInfo.getUrl();
        String method = apiInfo.getMethod();
        String requestHeader = apiInfo.getRequestHeader();
        String responseHeader = apiInfo.getResponseHeader();
        // 创建时，所有参数必须非空,下面这个add表示这次请求是创建时
        if (add) {
            if (StringUtils.isAnyBlank(name, description, url, method, requestHeader, responseHeader) ) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名字过长");
        }
        if (StringUtils.isNotBlank(url) && name.length() > 512) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "url过长");
        }  if (StringUtils.isNotBlank(description) && description.length() > 256) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "描述过长");
        }  if (StringUtils.isNotBlank(method) && method.length() > 256) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "方法长度有误");
        }
    }
}




