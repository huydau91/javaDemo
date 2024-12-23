package com.postgresql.huydau.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class UserPrincipalAuthenToken extends AbstractAuthenticationToken {
    private final UserPrincipal principal;

    public UserPrincipalAuthenToken(UserPrincipal principal) {
        super(principal.getAuthorities());
        this.principal = principal;
        setAuthenticated(true);
    };

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public UserPrincipal getPrincipal() {
        return principal;
    }

}
