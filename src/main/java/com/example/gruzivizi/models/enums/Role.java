package com.example.gruzivizi.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN, ROLE_CARRIER;

    @Override
    public String getAuthority() {
        return name();
    }
}
