package com.byakko.service.sales.service.authentication.domain.domaincore.entity;

import com.byakko.service.sales.service.authentication.domain.domaincore.valueobject.UserId;

public class SystemAdmin extends User {

    private String username;
    private String password;

    public SystemAdmin(UserId id, String username, String password) {
        super(id);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
