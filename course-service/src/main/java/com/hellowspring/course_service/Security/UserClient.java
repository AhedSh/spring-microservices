package com.hellowspring.course_service.Security;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserClient {

    private final RestClient restClient;

    public UserClient(RestClient userServiceRestClient) {
        this.restClient = userServiceRestClient;
    }

    public UserDto getUserById(Long userId) {

        return restClient.get()
                .uri(
                        "/api/users/internal/{id}",
                        userId
                )
                .retrieve()
                .body(UserDto.class);
    }

    public UserDto getUserByUsername(String username) {

        return restClient.get()
                .uri(
                        "/api/users/internal/username/{username}",
                        username
                )
                .retrieve()
                .body(UserDto.class);
    }
}

