package com.suman.springsecurity.jpt.dto;

import lombok.Data;

import java.util.List;

@Data
@SuppressWarnings("unused")
public class KeycloakAuthorizationConfig {
    private boolean allowRemoteResourceManagement;
    private String policyEnforcementMode;
    private List<Resources> resources;
    private List<Policies> policies;
    private List<Scopes> scopes;
    private String decisionStrategy;
}