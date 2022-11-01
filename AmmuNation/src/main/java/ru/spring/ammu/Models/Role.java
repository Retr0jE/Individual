package ru.spring.ammu.Models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, SELLER, STOREKEEPER;

    @Override
    public String getAuthority() {
        return name();
    }
}
