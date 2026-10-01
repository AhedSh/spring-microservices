package com.hellowspring.enrollment_service.Security;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserClient {

    private final RestClient restClient;

    public UserClient(@LoadBalanced RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("http://user-service")
                .build();
    }

    public UserDto getUserById(Long userId) {
        return restClient.get()
                .uri("/api/users/internal/{id}", userId)
                .retrieve()
                .body(UserDto.class);
    }

    public UserDto getUserByUsername(String username) {
        return restClient.get()
                .uri("/api/users/internal/username/{username}", username)
                .retrieve()
                .body(UserDto.class);
    }
}

