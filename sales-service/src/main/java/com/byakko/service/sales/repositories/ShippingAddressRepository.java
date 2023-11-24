package com.byakko.service.sales.repositories;

import com.byakko.service.sales.models.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingAddressRepository extends JpaRepository<ShippingAddress, String> {

    List<ShippingAddress> findAllByCustomerOrderByCreatedAtDesc(String customer);

    @Query("select sad from ShippingAddress sad where sad.customer = :customer and sad.isDefault = true")
    List<ShippingAddress> findAllByCustomerAndDefaultIsTrue(@Param("customer") String customer);

}
