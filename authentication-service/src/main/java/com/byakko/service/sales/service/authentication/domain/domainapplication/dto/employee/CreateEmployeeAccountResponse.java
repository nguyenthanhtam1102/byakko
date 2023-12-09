package com.byakko.service.sales.service.authentication.domain.domainapplication.dto.employee;

public class CreateEmployeeAccountResponse {

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

        public CreateEmployeeAccountResponse build() {
            CreateEmployeeAccountResponse createEmployeeAccountResponse = new CreateEmployeeAccountResponse();
            createEmployeeAccountResponse.userId = this.userId;
            createEmployeeAccountResponse.createdAt = this.createdAt;
            createEmployeeAccountResponse.employeeId = this.employeeId;
            return createEmployeeAccountResponse;
        }
    }
}
