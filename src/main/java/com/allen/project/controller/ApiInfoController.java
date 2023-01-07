package com.allen.project.controller;

import com.allen.allenapiclientsdk.client.AllenApiClient;
import com.allen.project.annotation.AuthCheck;
import com.allen.project.common.*;
import com.allen.project.constant.CommonConstant;
import com.allen.project.exception.BusinessException;
import com.allen.project.model.dto.apiinfo.ApiInfoAddRequest;
import com.allen.project.model.dto.apiinfo.ApiInfoInvokeRequest;
import com.allen.project.model.dto.apiinfo.ApiInfoQueryRequest;
import com.allen.project.model.dto.apiinfo.ApiInfoUpdateRequest;
import com.allen.project.model.entity.ApiInfo;
import com.allen.project.model.entity.User;
import com.allen.project.model.enums.ApiInfoStatusEnum;
import com.allen.project.service.ApiInfoService;
import com.allen.project.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 帖子接口
 *
 * @author allen
 */
@RestController
@RequestMapping("/apiInfo")
@Slf4j
public class ApiInfoController {

    @Resource
    private ApiInfoService apiInfoService;

    @Resource
    private UserService userService;

    @Resource
    private AllenApiClient allenApiClient;

    // region 增删改查

    /**
     * 创建
     *
     * @param apiInfoAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addApiInfo(@RequestBody ApiInfoAddRequest apiInfoAddRequest, HttpServletRequest request) {
        if (apiInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ApiInfo apiInfo = new ApiInfo();
        BeanUtils.copyProperties(apiInfoAddRequest, apiInfo);
        // 校验
        apiInfoService.validApiInfo(apiInfo, true);
        User loginUser = userService.getLoginUser(request);
        apiInfo.setUserId(loginUser.getId());
        boolean result = apiInfoService.save(apiInfo);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newApiInfoId = apiInfo.getId();
        return ResultUtils.success(newApiInfoId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteApiInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        ApiInfo oldApiInfo = apiInfoService.getById(id);
        if (oldApiInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可删除
        if (!oldApiInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = apiInfoService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新
     *
     * @param apiInfoUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateApiInfo(@RequestBody ApiInfoUpdateRequest apiInfoUpdateRequest,
                                            HttpServletRequest request) {
        if (apiInfoUpdateRequest == null || apiInfoUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ApiInfo apiInfo = new ApiInfo();
        BeanUtils.copyProperties(apiInfoUpdateRequest, apiInfo);
        // 参数校验
        apiInfoService.validApiInfo(apiInfo, false);
        User user = userService.getLoginUser(request);
        long id = apiInfoUpdateRequest.getId();
        // 判断是否存在
        ApiInfo oldApiInfo = apiInfoService.getById(id);
        if (oldApiInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可修改
        if (!oldApiInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = apiInfoService.updateById(apiInfo);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<ApiInfo> getApiInfoById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ApiInfo apiInfo = apiInfoService.getById(id);
        return ResultUtils.success(apiInfo);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param apiInfoQueryRequest
     * @return
     */
    @AuthCheck(mustRole = "admin")
    @GetMapping("/list")
    public BaseResponse<List<ApiInfo>> listApiInfo(ApiInfoQueryRequest apiInfoQueryRequest) {
        ApiInfo apiInfoQuery = new ApiInfo();
        if (apiInfoQueryRequest != null) {
            BeanUtils.copyProperties(apiInfoQueryRequest, apiInfoQuery);
        }
        QueryWrapper<ApiInfo> queryWrapper = new QueryWrapper<>(apiInfoQuery);
        List<ApiInfo> apiInfoList = apiInfoService.list(queryWrapper);
        return ResultUtils.success(apiInfoList);
    }

    /**
     * 分页获取列表
     *
     * @param apiInfoQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<ApiInfo>> listApiInfoByPage(ApiInfoQueryRequest apiInfoQueryRequest, HttpServletRequest request) {
        if (apiInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        ApiInfo apiInfoQuery = new ApiInfo();
        BeanUtils.copyProperties(apiInfoQueryRequest, apiInfoQuery);
        long current = apiInfoQueryRequest.getCurrent();
        long size = apiInfoQueryRequest.getPageSize();
        String sortField = apiInfoQueryRequest.getSortField();
        String sortOrder = apiInfoQueryRequest.getSortOrder();
        String description = apiInfoQuery.getDescription();
        // description 需支持模糊搜索
        apiInfoQuery.setDescription(null);
        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<ApiInfo> queryWrapper = new QueryWrapper<>(apiInfoQuery);
        queryWrapper.like(StringUtils.isNotBlank(description), "description", description);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<ApiInfo> apiInfoPage = apiInfoService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(apiInfoPage);
    }

    /**
     * 上线接口
     *
     * @param idRequest
     * @param request
     * @return
     */
    @PostMapping("/online")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Boolean> onlineApiInfo(@RequestBody IdRequest idRequest,
                                               HttpServletRequest request) {
        if(idRequest == null || idRequest.getId() <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //校验数据库是否存在
        long id = idRequest.getId();
        ApiInfo oldApiInfo = apiInfoService.getById(id);
        if(oldApiInfo == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        //判断该接口是否可以调用
        com.allen.allenapiclientsdk.model.User user = new com.allen.allenapiclientsdk.model.User();
        user.setUsername("test");
        String username = allenApiClient.getUsernameByPost(user);
        if(StringUtils.isBlank(username)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "接口验证失败");
        }
        //只有本人和管理员可以修改
        ApiInfo apiInfo = new ApiInfo();
        apiInfo.setId(id);
        apiInfo.setStatus(ApiInfoStatusEnum.ONLINE.getValue());
        boolean result = apiInfoService.updateById(apiInfo);
        return ResultUtils.success(result);
    }

    /**
     * 下线接口
     *
     * @param idRequest
     * @param request
     * @return
     */
    @PostMapping("/offline")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Boolean> offlineApiInfo(@RequestBody IdRequest idRequest,
                                               HttpServletRequest request) {
        if(idRequest == null || idRequest.getId() <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //校验数据库是否存在
        long id = idRequest.getId();
        ApiInfo oldApiInfo = apiInfoService.getById(id);
        if(oldApiInfo == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        //只有本人和管理员可以修改
        ApiInfo apiInfo = new ApiInfo();
        apiInfo.setId(id);
        apiInfo.setStatus(ApiInfoStatusEnum.OFFLINE.getValue());
        boolean result = apiInfoService.updateById(apiInfo);
        return ResultUtils.success(result);
    }
    /**
     * 调用接口
     *
     * @param apiInfoInvokeRequest
     * @param request
     * @return
     */
    @PostMapping("/invoke")
    public BaseResponse<Object> invokeApiInfo(@RequestBody ApiInfoInvokeRequest apiInfoInvokeRequest,
                                                HttpServletRequest request) {
        if(apiInfoInvokeRequest == null || apiInfoInvokeRequest.getId()<= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //校验数据库是否存在
        long id = apiInfoInvokeRequest.getId();
        String userRequestParams = apiInfoInvokeRequest.getUserRequestParams();
        ApiInfo oldApiInfo = apiInfoService.getById(id);
        if(oldApiInfo == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        //判断api是否关闭
        if(oldApiInfo.getStatus() != ApiInfoStatusEnum.ONLINE.getValue()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口已经关闭");
        }
        User loginUser = userService.getLoginUser(request);
        //获取用户的身份签名
        String accessKey = loginUser.getAccessKey();
        String secretKey = loginUser.getSecretKey();
        //todo 暂时写死假设调用的就是这个接口,后期可以修改成固定的地址去调用
        //生成一个新的client
        AllenApiClient tmpApiClient = new AllenApiClient(accessKey, secretKey);
        com.allen.allenapiclientsdk.model.User user = null;
        //调用获得返回值
        Gson gson = new Gson();
        try {//隐藏服务器端报错
            user = gson.fromJson(userRequestParams, com.allen.allenapiclientsdk.model.User.class);
        }catch (RuntimeException e){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不符合Json格式");
        }
        String usernameByPost = tmpApiClient.getUsernameByPost(user);
        return ResultUtils.success(usernameByPost);
    }

    /**
     * 用户请求修改aksk
     * @param idRequest
     * @param request
     * @return
     */
    @PostMapping("/update/key")
    public BaseResponse<Boolean> updateKey(@RequestBody IdRequest idRequest,
                                                HttpServletRequest request) {
        if(idRequest == null || idRequest.getId() <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //校验数据库是否存在
        long id = idRequest.getId();
        User oldUser = userService.getById(id);
        if(oldUser == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        String userAccount = oldUser.getUserAccount();
        //生成新的ak和sk
        Map<String, String> keys = userService.getNewKeys(userAccount);
        String newAccessKey = keys.get("accessKey");
        String newSecretKey = keys.get("secretKey");
        oldUser.setAccessKey(newAccessKey);
        oldUser.setSecretKey(newSecretKey);
        boolean result = userService.save(oldUser);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return ResultUtils.success(result);
    }
}
