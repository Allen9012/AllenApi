package com.allen.allenapiinterface;

import com.allen.allenapiclientsdk.client.AllenApiClient;
import com.allen.allenapiclientsdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class AllenapiInterfaceApplicationTests {

    @Resource
    private AllenApiClient allenApiClient;

    @Test
    void contextLoads() {
        String allen = allenApiClient.getNameByGet("allen");
        User user = new User();
        user.setUsername("allen is restful");
        String usernameByPost = allenApiClient.getUsernameByPost(user);
        System.out.println(allen);
        System.out.println(usernameByPost);
    }

}
