package com.byakko.service.sales.common.utils;


import com.byakko.service.sales.common.domain.valueobject.Authority;

import java.util.Set;

public class JwtPayload {

    private String userId;
    private Set<Authority> authorities;

    public String getUserId() {
        return userId;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }


    public static final class Builder {
        private String userId;
        private Set<Authority> authorities;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder authorities(Set<Authority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public JwtPayload build() {
            JwtPayload jwtPayload = new JwtPayload();
            jwtPayload.userId = this.userId;
            jwtPayload.authorities = this.authorities;
            return jwtPayload;
        }
    }
}
