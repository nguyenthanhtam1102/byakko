package com.byakko.service.authentication.domain.domainapplication.dto.role;

public class RoleResponse {

    private String id;
    private String name;


    public static final class Builder {
        private String id;
        private String name;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public RoleResponse build() {
            RoleResponse roleResponse = new RoleResponse();
            roleResponse.id = this.id;
            roleResponse.name = this.name;
            return roleResponse;
        }
    }
}
