package com.byakko.service.authentication.domain.domaincore.entity;

import com.byakko.common.domain.entity.BaseEntity;
import com.byakko.common.domain.exception.ValidationException;
import com.byakko.service.authentication.domain.domaincore.valueobject.EmployeeId;
import com.byakko.service.authentication.domain.domaincore.valueobject.EmployeeStatus;

import java.time.ZonedDateTime;
import java.util.Map;

public class Employee extends BaseEntity<EmployeeId> {

    private String employeeId;
    private String password;
    private EmployeeStatus status;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;

    public void validate() {
        validateEmployeeId();
        validatePassword();
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
        if(!this.password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$"))
            throw new ValidationException(Map.of("password", "password is not in the correct format"));
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


    public static final class EmployeeBuilder {
        private String employeeId;
        private String password;
        private EmployeeStatus status;
        private ZonedDateTime createdAt;
        private ZonedDateTime updatedAt;
        private EmployeeId id;

        private EmployeeBuilder() {
        }

        public static EmployeeBuilder builder() {
            return new EmployeeBuilder();
        }

        public EmployeeBuilder employeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public EmployeeBuilder password(String password) {
            this.password = password;
            return this;
        }

        public EmployeeBuilder status(EmployeeStatus status) {
            this.status = status;
            return this;
        }

        public EmployeeBuilder createdAt(ZonedDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public EmployeeBuilder updatedAt(ZonedDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public EmployeeBuilder id(EmployeeId id) {
            this.id = id;
            return this;
        }

        public Employee build() {
            Employee employee = new Employee();
            employee.setEmployeeId(employeeId);
            employee.setPassword(password);
            employee.setStatus(status);
            employee.setCreatedAt(createdAt);
            employee.setUpdatedAt(updatedAt);
            employee.setId(id);
            return employee;
        }
    }
}
