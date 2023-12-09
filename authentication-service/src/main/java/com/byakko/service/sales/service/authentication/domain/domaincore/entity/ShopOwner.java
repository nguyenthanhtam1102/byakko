package com.byakko.service.sales.service.authentication.domain.domaincore.entity;

import com.byakko.service.sales.common.DomainConstants;
import com.byakko.service.sales.common.domain.exception.ValidationException;
import com.byakko.service.sales.service.authentication.domain.domaincore.valueobject.ShopOwnerStatus;
import com.byakko.service.sales.service.authentication.domain.domaincore.valueobject.UserId;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

public class ShopOwner extends User {
    private String username;
    private String phone;
    private String email;
    private String password;
    private ShopOwnerStatus status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private boolean verified;
    private Menu menu;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void initialize() {
        setId(new UserId(String.valueOf(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)).toEpochSecond())));
        setStatus(ShopOwnerStatus.ACTIVE);
        setVerified(false);
        setCreatedAt(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)));
    }

    public void validate() {
        validateUsername();
        validatePassword();
        validateStatus();
        validateCreatedAt();
        validatePhone();
        validateEmail();
    }

    private void validateUsername() {
        if(username == null || username.isBlank())
            throw new ValidationException(Map.of("username", "username must be not blank"));
    }
    private void validatePhone() {
        if(this.phone == null || this.phone.isBlank())
            throw new ValidationException(Map.of("phone", "phone must be not blank"));
        if(!this.phone.matches("^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$"))
            throw new ValidationException(Map.of("phone", "phone is not in the correct format"));
    }

    private void validateEmail() {
        if(this.email == null || this.email.isBlank())
            throw new ValidationException(Map.of("email", "email must be not blank"));
        if(!this.email.matches("^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$"))
            throw new ValidationException(Map.of("email", "email is not in the correct format"));
    }
    private void validatePassword() {
        System.out.println(password);
        if(this.password == null || this.password.isBlank())
            throw new ValidationException(Map.of("password", "password must be not blank"));
//        if(!this.password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$"))
//            throw new ValidationException(Map.of("password", "password phải gồm tối thiểu 8 kí tự, bao gồm ít nhất 1 chữ cái, ít nhất 1 chữ cái viết hoa, ít nhất 1 chữ số"));
    }
    public void validatePassword(String password) {
        if(password == null || password.isBlank())
            throw new ValidationException(Map.of("password", "password must be not blank"));
        if(!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"))
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

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Menu getMenuId() {
        return menu;
    }

    public void setMenuId(Menu menu) {
        this.menu = menu;
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
        private String email;
        private String phone;
        private boolean verified;
        private ShopOwnerStatus status;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;
        private UserId id;
        private Menu menu;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }
        public Builder verified(boolean verify){
            this.verified = verify;
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
        public Builder email(String email){
            this.email = email;
            return this;
        }
        public Builder phone(String phone){
            this.phone = phone;
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
        public Builder menu(Menu menu){
            this.menu = menu;
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
            shopOwner.setVerified(verified);
            shopOwner.setMenuId(menu);
            shopOwner.setPhone(phone);
            shopOwner.setEmail(email);
            return shopOwner;
        }
    }
}
