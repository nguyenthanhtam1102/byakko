package com.byakko.service.authentication.dataaccess.adapter;

import com.byakko.service.authentication.dataaccess.entity.CustomerEntity;
import com.byakko.service.authentication.dataaccess.mapper.CustomerMapper;
import com.byakko.service.authentication.dataaccess.repository.CustomerJpaRepository;
import com.byakko.service.authentication.domain.domainapplication.port.output.repository.CustomerRepository;
import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;

    @Override
    public Optional<Customer> findById(String id) {
        return customerJpaRepository.findById(id).map(CustomerMapper::toCustomer);
    }

    @Override
    public Optional<Customer> findByPhoneOrEmail(String phone, String email) {
        return customerJpaRepository.findByPhoneAndEmail(phone, email).map(CustomerMapper::toCustomer);
    }

    @Override
    public Optional<Customer> findByPhoneAndEmail(String phone, String email) {
        return customerJpaRepository.findByPhoneAndEmail(phone, email).map(CustomerMapper::toCustomer);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = CustomerMapper.toCustomerEntity(customer);
        customerJpaRepository.save(customerEntity);
        return CustomerMapper.toCustomer(customerEntity);
    }

}
