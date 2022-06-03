package com.androsov.itmo_web_lab4.configuration.security;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_REGULAR;

    @Override
    public String getAuthority() {
        return name();
    }
}
