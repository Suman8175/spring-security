package com.suman.springsecurity.jpt.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class ImportResourceJSON {

    @Value("${keycloak.url}")
    private String authServerUrl;

    @Value("${keycloak.username}")
    private String username;

    @Value("${keycloak.password}")
    private String password;



    private String getAdminAccessToken() throws Exception {
        String url = authServerUrl + "/realms/master/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "password");
        body.add("client_id", "admin-cli");
        body.add("username", username);
        body.add("password", password);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return (String) Objects.requireNonNull(response.getBody()).get("access_token");
        } else {
            log.error("Failed to retrieve access token:{} ", response.getStatusCode() + ", Response Body: " + response.getBody());
            throw new RuntimeException("ErrorCodes.TOKEN_FAILED_TO_GENERATE");
        }
    }
}
