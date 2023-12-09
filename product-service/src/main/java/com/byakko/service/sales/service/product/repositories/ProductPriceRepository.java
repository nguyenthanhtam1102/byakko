package com.byakko.service.sales.service.product.repositories;

import com.byakko.service.sales.service.product.models.Product;
import com.byakko.service.sales.service.product.models.ProductPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, String> {

    @Query("select pp from ProductPrice pp " +
            "where pp.product = :product " +
            "and pp.startDate <= :startDate " +
            "and (pp.endDate is null or pp.endDate >= :endDate) " +
            "and pp.deleted = false")
    List<ProductPrice> findOverlappingPrices(@Param("product") Product product, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("select pr from ProductPrice pr where pr.product.id = :productId and pr.deleted = false")
    Page<ProductPrice> findAllByProduct(@Param("productId") String productId, Pageable pageable);

    @Query("select pp from ProductPrice pp " +
            "where pp.product = :product " +
            "and pp.startDate <= :currentDate " +
            "and (pp.endDate is null or pp.endDate >= :currentDate)" +
            "and pp.active = true " +
            "and pp.deleted = false " +
            "order by pp.startDate desc ")
    List<ProductPrice> findCurrentPriceByProduct(@Param("product") Product product, @Param("currentDate") LocalDate currentDate);

    @Query("select pp from ProductPrice pp " +
            "where pp.product = :product " +
            "and pp.startDate <= :currentDate " +
            "and pp.active = true " +
            "and pp.deleted = false " +
            "order by pp.startDate desc ")
    List<ProductPrice> findNearestPricesBeforeCurrentDate(@Param("product") Product product, @Param("currentDate") LocalDate currentDate);

}
