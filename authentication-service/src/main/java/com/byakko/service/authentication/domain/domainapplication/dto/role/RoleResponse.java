package com.byakko.service.authentication.domain.domainapplication.dto.role;

public class RoleResponse {

    private String id;
    private String name;
    private String menuId;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }


    public static final class Builder {
        private String id;
        private String name;
        private String menuId;

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

        public Builder menuId(String menuId) {
            this.menuId = menuId;
            return this;
        }

        public RoleResponse build() {
            RoleResponse roleResponse = new RoleResponse();
            roleResponse.setId(id);
            roleResponse.setName(name);
            roleResponse.setMenuId(menuId);
            return roleResponse;
        }
    }
}
