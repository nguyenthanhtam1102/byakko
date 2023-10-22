package com.byakko.service.authentication.domain.domaincore.entity;

import com.byakko.common.DomainConstants;
import com.byakko.common.domain.exception.ValidationException;
import com.byakko.service.authentication.domain.domaincore.valueobject.EmployeeStatus;
import com.byakko.service.authentication.domain.domaincore.valueobject.UserId;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

public class Employee extends User {

    private String employeeId;
    private String password;
    private String phone;
    private String email;
    private EmployeeStatus status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private Role role;

    public void initialize() {
        setId(new UserId(String.valueOf(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)).toEpochSecond())));
        setStatus(EmployeeStatus.ACTIVE);
        setCreatedAt(ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)));
    }

    public void validate() {
        validateEmployeeId();
        validatePassword();
        validatePhone();
        validateEmail();
        validateStatus();
        validateCreatedAt();
    }

    private void validateEmployeeId() {
        if(employeeId == null || employeeId.isBlank())
            throw new ValidationException(Map.of("employeeId", "employee id must be not blank"));
    }

    private void validatePassword() {
        if(this.password == null || this.password.isBlank())
            throw new ValidationException(Map.of("password", "password must be not blank"));
//        if(!this.password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"))
//            throw new ValidationException(Map.of("password", "password is not in the correct format"));
    }

    public void validatePassword(String password) {
        if(password == null || password.isBlank())
            throw new ValidationException(Map.of("password", "password must be not blank"));
        if(password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"))
            throw new ValidationException(Map.of("password", "password is not in the correct format"));
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

    private void validateStatus() {
        if(this.status == null)
            throw new ValidationException(Map.of("status", "status must be not null"));
    }

    private void validateCreatedAt() {
        if(this.createdAt == null)
            throw new ValidationException(Map.of("created_at", "created_at must be not null"));
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }


    public static final class Builder {
        private String employeeId;
        private String password;
        private String phone;
        private String email;
        private EmployeeStatus status;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;
        private Role role;
        private UserId id;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder employeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder status(EmployeeStatus status) {
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

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public Builder id(UserId id) {
            this.id = id;
            return this;
        }

        public Employee build() {
            Employee employee = new Employee();
            employee.setEmployeeId(employeeId);
            employee.setPassword(password);
            employee.setPhone(phone);
            employee.setEmail(email);
            employee.setStatus(status);
            employee.setCreatedAt(createdAt);
            employee.setUpdatedAt(updatedAt);
            employee.setRole(role);
            employee.setId(id);
            return employee;
        }
    }
}
