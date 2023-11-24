package com.byakko.service.sales.repositories;

import com.byakko.service.sales.models.ShoppingCartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, String> {

    @Query("select sum (item.quantity) FROM ShoppingCartItem item WHERE item.customer = :customerId")
    Integer getTotalQuantityInCart(@Param("customerId") String customerId);

    List<ShoppingCartItem> findAllByCustomerOrderByModifiedDateDesc(String customer);
    Optional<ShoppingCartItem> findShoppingCartItemByProductAndVariant(String product, String variant);

}
