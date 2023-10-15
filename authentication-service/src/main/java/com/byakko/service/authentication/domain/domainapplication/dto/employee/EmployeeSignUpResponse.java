package com.byakko.service.authentication.domain.domainapplication.dto.employee;

public class EmployeeSignUpResponse {

    private String userId;
    private String employeeId;
    private Long createdAt;


    public static final class Builder {
        private String userId;
        private String employeeId;
        private Long createdAt;

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

        public Builder createdAt(Long createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public EmployeeSignUpResponse build() {
            EmployeeSignUpResponse employeeSignUpResponse = new EmployeeSignUpResponse();
            employeeSignUpResponse.userId = this.userId;
            employeeSignUpResponse.createdAt = this.createdAt;
            employeeSignUpResponse.employeeId = this.employeeId;
            return employeeSignUpResponse;
        }
    }
}
