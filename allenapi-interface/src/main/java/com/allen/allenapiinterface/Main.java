package com.allen.allenapiinterface;

import com.allen.allenapiclientsdk.client.AllenApiClient;
import com.allen.allenapiclientsdk.model.User;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Allen
 * Date: 2023-01-04
 * Time: 13:16
 */
public class Main {
    public static void main(String[] args) {
        String accessKey = "allen";
        String secretKey = "12345678";
        AllenApiClient allenApiClient = new AllenApiClient(accessKey, secretKey);
        String allen_get = allenApiClient.getNameByGet("allen Get");
        String allen_post = allenApiClient.getNameByPost("allen Post");
        User user = new User();
        user.setUsername("yanzhimingzhi");  //中文有问题没有解决
        String usernameByPost = allenApiClient.getUsernameByPost(user);
        System.out.println(allen_get);
        System.out.println(allen_post);
        System.out.println(usernameByPost);
    }
}
