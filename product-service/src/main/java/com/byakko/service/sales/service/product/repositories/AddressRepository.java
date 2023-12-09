package com.byakko.service.sales.service.product.repositories;

import com.byakko.service.sales.service.product.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
}
