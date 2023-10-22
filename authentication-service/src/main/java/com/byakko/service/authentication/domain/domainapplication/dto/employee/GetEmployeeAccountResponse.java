package com.byakko.service.authentication.domain.domainapplication.dto.employee;

public class GetEmployeeAccountResponse {

    private String userId;

    private String employeeId;

    private String roleId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }


    public static final class Builder {
        private String userId;
        private String employeeId;
        private String roleId;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder employeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder roleId(String roleId) {
            this.roleId = roleId;
            return this;
        }

        public GetEmployeeAccountResponse build() {
            GetEmployeeAccountResponse getEmployeeAccountResponse = new GetEmployeeAccountResponse();
            getEmployeeAccountResponse.setUserId(userId);
            getEmployeeAccountResponse.setEmployeeId(employeeId);
            getEmployeeAccountResponse.setRoleId(roleId);
            return getEmployeeAccountResponse;
        }
    }
}
