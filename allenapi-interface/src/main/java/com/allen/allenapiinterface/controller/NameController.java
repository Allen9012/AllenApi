package com.allen.allenapiinterface.controller;

import com.allen.allenapiclientsdk.model.User;
import com.allen.allenapiclientsdk.utils.SignUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * Description: 提供几个示例接口没有实际的作用
 * User: Allen
 * Date: 2023-01-03
 * Time: 21:38
 */
@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/")
    public String getNameByGet(String name) {

        return "你的名字是" + name;
    }

    @PostMapping("/")
    public String getNameByPost(@RequestParam String name){
        return "POST 你的名字是" + name;
    }

    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody User user, HttpServletRequest request){
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        String body = request.getHeader("body");
        //todo 单纯模拟一下,实际需要使用数据库查是否已经分配给用户
        if(!accessKey.equals("allen")){
            throw new RuntimeException("无权限");
        }
        //随机数
        if(Long.parseLong(nonce) > 10000){
            throw new RuntimeException("无权限");
        }
        //时间戳
        long currentTimestamp = System.currentTimeMillis() / 1000;
        if(( Long.parseLong(timestamp) + 5 * 60) < currentTimestamp){
            throw  new RuntimeException("无权限");
        }
        //todo 实际情况中是从数据库查出secretKey
        String serverSign = SignUtils.getSign(body, "12345678");
        if(!sign.equals(serverSign)){
            throw new RuntimeException("无权限");
        }
        //todo 请求头中文有问题没有解决
        return  "POST 你的名字是" + user.getUsername();
    }
}
