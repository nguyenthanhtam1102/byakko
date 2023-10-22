package com.byakko.service.authentication.domain.domainapplication.port.output.repository;

import com.byakko.service.authentication.domain.domaincore.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface CustomerRepository {

    Optional<Customer> findById(String id);
    Optional<Customer> findByPhoneOrEmail(String phone, String email);
    Optional<Customer> findByPhoneAndEmail(String phone, String email);
    Customer save(Customer customer);

}
