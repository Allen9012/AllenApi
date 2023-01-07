package com.allen.allenapiclientsdk;

import com.allen.allenapiclientsdk.client.AllenApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created with IntelliJ IDEA.
 * Description: 自动生成apiClient的简易starter
 * User: Allen
 * Date: 2023-01-05
 * Time: 14:40
 */

@Configuration
@ConfigurationProperties("allenapi.client")
@Data
@ComponentScan
public class AllenApiClientConfig {
    private String accessKey;
    private String secretKey;

    @Bean
    public AllenApiClient allenApiClient(){
        return new AllenApiClient(accessKey, secretKey);
    }
}
