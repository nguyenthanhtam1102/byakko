package com.byakko.service.sales.service.product.repositories;

import com.byakko.service.sales.service.product.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String> {

    @Query("select i from Inventory i where i.product = :productId and i.variant = :productVariantId and i.endDate = null ")
    Optional<Inventory> getLatestInventory(@Param("productId") String productId, @Param("productVariantId") String productVariantId);

}
