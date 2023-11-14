package com.byakko.service.product.repositories;

import com.byakko.service.product.models.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, String> {

    @Query("select pv from ProductVariant pv where pv.product.id = :productId and pv.id = :productVariantId and pv.deleted = false")
    Optional<ProductVariant> findByIdAndProductId(@Param("productId") String productId, @Param("productVariantId") String productVariantId);

}
