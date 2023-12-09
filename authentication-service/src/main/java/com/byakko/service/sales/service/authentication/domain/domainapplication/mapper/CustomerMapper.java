package com.byakko.service.sales.service.authentication.domain.domainapplication.mapper;

import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer.CustomerSignUpCommand;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.customer.CustomerSignUpResponse;
import com.byakko.service.sales.service.authentication.domain.domaincore.entity.Customer;

public class CustomerMapper {

    public static Customer toCustomer(CustomerSignUpCommand command) {
        return Customer.CustomerBuilder.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .phone(command.getPhone())
                .email(command.getEmail())
                .password(command.getPassword())
                .build();
    }

    public static CustomerSignUpResponse toCustomerSignUpResponse(Customer customer) {
        return CustomerSignUpResponse.Builder.builder()
                .userId(customer.getId().toString())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .createdAt(customer.getCreatedAt().toEpochSecond())
                .build();
    }

}
