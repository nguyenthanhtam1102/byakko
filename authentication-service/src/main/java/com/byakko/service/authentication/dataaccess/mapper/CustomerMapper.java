package com.byakko.service.authentication.dataaccess.mapper;

import com.byakko.service.authentication.dataaccess.entity.CustomerEntity;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import com.byakko.service.authentication.domain.domaincore.valueobject.CustomerId;

public class CustomerMapper {

    public static CustomerEntity toCustomerEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId().getValue())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .password(customer.getPassword())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .status(customer.getStatus())
                .verified(customer.getVerified())
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .build();
    }

    public static Customer toCustomer(CustomerEntity customerEntity) {
        return Customer.CustomerBuilder.builder()
                .id(new CustomerId(customerEntity.getId()))
                .firstName(customerEntity.getFirstName())
                .lastName(customerEntity.getLastName())
                .phone(customerEntity.getPhone())
                .email(customerEntity.getEmail())
                .password(customerEntity.getPassword())
                .status(customerEntity.getStatus())
                .verified(customerEntity.getVerified())
                .createdAt(customerEntity.getCreatedAt())
                .updatedAt(customerEntity.getUpdatedAt())
                .build();
    }

}
