package org.github.scalabletaskmanager.task.service.impl;

import org.github.scalabletaskmanager.task.gen.model.UserDTO;
import org.github.scalabletaskmanager.task.service.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserClientImpl implements UserClient {

    private final RestTemplate restTemplate;
    @Value( "${user.service.url}")
    private String userUrl;

    @Autowired
    public UserClientImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        String url = userUrl + "/v1/users/" + username;
        ResponseEntity<UserDTO> response = restTemplate.getForEntity(url, UserDTO.class);

        return response.getBody();
    }
}
