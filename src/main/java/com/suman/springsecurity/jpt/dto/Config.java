package com.suman.springsecurity.jpt.dto;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Config {
    private String fetchRoles;
    private String roles;
    private String scopes;
    private String applyPolicies;
}