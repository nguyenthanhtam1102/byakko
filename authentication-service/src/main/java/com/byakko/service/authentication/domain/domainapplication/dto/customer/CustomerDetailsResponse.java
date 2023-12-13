package com.byakko.service.authentication.domain.domainapplication.dto.customer;

import javax.validation.constraints.NotBlank;

public class CustomerDetailsResponse {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

    public CustomerDetailsResponse() {
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

    public static final class Builder {
        private String firstName;
        private String lastName;
        private String phone;
        private String email;
        private Builder() {
        }
        public static Builder builder(){
            return new Builder();
        }
        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }
        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }
        public Builder phone(String phone){
            this.phone = phone;
            return this;
        }
        public Builder email(String email){
            this.email = email;
            return this;
        }
        public CustomerDetailsResponse build(){
            CustomerDetailsResponse customerDetailsResponse = new CustomerDetailsResponse();
            customerDetailsResponse.setEmail(this.email);
            customerDetailsResponse.setPhone(this.phone);
            customerDetailsResponse.setFirstName(this.firstName);
            customerDetailsResponse.setLastName(this.lastName);
            return customerDetailsResponse;
        }
    }
}
