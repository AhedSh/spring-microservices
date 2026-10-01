package com.hellowspring.course_service.Security;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    @LoadBalanced
    public RestClient userServiceRestClient() {
        return RestClient.builder()
                .baseUrl("http://user-service")
                .build();
    }
}

