package com.byakko.service.authentication.domain.domaincore.entity;

import com.byakko.common.DomainConstants;
import com.byakko.common.domain.exception.ValidationException;
import com.byakko.service.authentication.domain.domaincore.valueobject.ShopOwnerStatus;
import com.byakko.service.authentication.domain.domaincore.valueobject.UserId;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

public class ShopOwner extends User {

    private String username;
    private String password;
    private ShopOwnerStatus status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public void initialize() {
        setId(new UserId(String.valueOf(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)))));
        setStatus(ShopOwnerStatus.ACTIVE);
        setCreatedAt(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)));
    }

    public void validate() {
        validateUsername();
        validatePassword();
        validateStatus();
        validateStatus();
        validateCreatedAt();
    }

    private void validateUsername() {
        if(username == null || username.isBlank())
            throw new ValidationException(Map.of("username", "username must be not blank"));
    }

    private void validatePassword() {
        if(this.password == null || this.password.isBlank())
            throw new ValidationException(Map.of("password", "password must be not blank"));
        if(!this.password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"))
            throw new ValidationException(Map.of("password", "password is not in the correct format"));
    }

    private void validateStatus() {
        if(status == null)
            throw new ValidationException(Map.of("status", "status must be not null"));
    }

    private void validateCreatedAt() {
        if(createdAt == null)
            throw new ValidationException(Map.of("createdAt", "createdAt must be not null"));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ShopOwnerStatus getStatus() {
        return status;
    }

    public void setStatus(ShopOwnerStatus status) {
        this.status = status;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    public static final class Builder {
        private String username;
        private String password;
        private ShopOwnerStatus status;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;
        private UserId id;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder status(ShopOwnerStatus status) {
            this.status = status;
            return this;
        }

        public Builder createdAt(ZonedDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(ZonedDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder id(UserId id) {
            this.id = id;
            return this;
        }

        public ShopOwner build() {
            ShopOwner shopOwner = new ShopOwner();
            shopOwner.setUsername(username);
            shopOwner.setPassword(password);
            shopOwner.setStatus(status);
            shopOwner.setCreatedAt(createdAt);
            shopOwner.setUpdatedAt(updatedAt);
            shopOwner.setId(id);
            return shopOwner;
        }
    }
}
