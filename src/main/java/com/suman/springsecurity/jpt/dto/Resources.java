package com.suman.springsecurity.jpt.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@SuppressWarnings("unused")
public class Resources {
    private String name;
    private boolean ownerManagedAccess;
    private String displayName;
    private String attributes;
    private Set<String> uris = new HashSet<>();
    private List<Scopes> scopes;
    @JsonProperty("icon_uri")
    private String iconUri;
}

