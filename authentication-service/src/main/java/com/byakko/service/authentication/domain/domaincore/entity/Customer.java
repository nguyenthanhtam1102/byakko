package com.byakko.service.authentication.domain.domaincore.entity;

import com.byakko.common.DomainConstants;
import com.byakko.common.domain.exception.ValidationException;
import com.byakko.common.domain.valueobject.Authority;
import com.byakko.service.authentication.domain.domaincore.valueobject.CustomerStatus;
import com.byakko.common.domain.valueobject.SystemRole;
import com.byakko.service.authentication.domain.domaincore.valueobject.UserId;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

public class Customer extends User {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private Boolean verified;
    private CustomerStatus status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private Authority authority;

    public void initialize() {
        setId(new UserId(String.valueOf(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)).toEpochSecond())));
        setVerified(false);
        setStatus(CustomerStatus.ACTIVE);
        setCreatedAt(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)));
        setAuthority(SystemRole.CUSTOMER);
    }

    public void validate() {
        validateFirstName();
        validateLastName();
        validatePhone();
        validateEmail();
        validatePassword();
        validateVerified();
        validateStatus();
        validateCreatedAt();
    }

    private void validateFirstName() {
        if(this.firstName == null || this.firstName.isBlank())
            throw new ValidationException(Map.of("firstname", "firstname must be not blank"));
    }

    private void validateLastName() {
        if(this.lastName == null || this.lastName.isBlank())
            throw new ValidationException(Map.of("lastname", "lastname must be not blank"));
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
        if(!this.password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$"))
            throw new ValidationException(Map.of("password", "password phải gồm tối thiểu 8 kí tự, bao gồm ít nhất 1 chữ cái, ít nhất 1 chữ cái viết hoa, ít nhất 1 chữ số"));
    }

    private void validateVerified() {
        if(this.verified == null)
            throw new ValidationException(Map.of("verified", "verified must be not null"));
    }

    private void validateStatus() {
        if(this.status == null)
            throw new ValidationException(Map.of("status", "status must be not null"));
    }

    private void validateCreatedAt() {
        if(this.createdAt == null)
            throw new ValidationException(Map.of("created_at", "created_at must be not null"));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public void setStatus(CustomerStatus status) {
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

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public static final class CustomerBuilder {
        private String firstName;
        private String lastName;
        private String phone;
        private String email;
        private String password;
        private Boolean verified;
        private CustomerStatus status;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;
        private UserId id;

        private CustomerBuilder() {
        }

        public static CustomerBuilder builder() {
            return new CustomerBuilder();
        }

        public CustomerBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public CustomerBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public CustomerBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public CustomerBuilder email(String email) {
            this.email = email;
            return this;
        }

        public CustomerBuilder password(String password) {
            this.password = password;
            return this;
        }

        public CustomerBuilder verified(Boolean verified) {
            this.verified = verified;
            return this;
        }

        public CustomerBuilder status(CustomerStatus status) {
            this.status = status;
            return this;
        }

        public CustomerBuilder createdAt(ZonedDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CustomerBuilder updatedAt(ZonedDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public CustomerBuilder id(UserId id) {
            this.id = id;
            return this;
        }

        public Customer build() {
            Customer customer = new Customer();
            customer.setFirstName(firstName);
            customer.setLastName(lastName);
            customer.setPhone(phone);
            customer.setEmail(email);
            customer.setPassword(password);
            customer.setVerified(verified);
            customer.setStatus(status);
            customer.setCreatedAt(createdAt);
            customer.setUpdatedAt(updatedAt);
            customer.setId(id);
            return customer;
        }
    }
}
