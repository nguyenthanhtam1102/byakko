package com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer;

public class CustomerSignUpResponse {

    private String userId;
    private String phone;
    private String email;
    private Long createdAt;

    public String getUserId() {
        return userId;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public static final class Builder {
        private String userId;
        private String phone;
        private String email;
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

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder createdAt(Long createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CustomerSignUpResponse build() {
            CustomerSignUpResponse customerSignUpResponse = new CustomerSignUpResponse();
            customerSignUpResponse.createdAt = this.createdAt;
            customerSignUpResponse.phone = this.phone;
            customerSignUpResponse.email = this.email;
            customerSignUpResponse.userId = this.userId;
            return customerSignUpResponse;
        }
    }
}
