package com.suman.springsecurity.jpt.dto;

import lombok.Data;

@Data
@SuppressWarnings("unused")
public class Policies {
    private String name;
    private String description;
    private String type;
    private String logic;
    private String decisionStrategy;
    private Config config;
}
