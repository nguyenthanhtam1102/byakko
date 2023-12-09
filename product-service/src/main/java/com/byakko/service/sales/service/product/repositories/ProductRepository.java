package com.byakko.service.sales.service.product.repositories;

import com.byakko.service.sales.service.product.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("select p from Product p where (lower(p.id) like :idOrName or lower(p.name) like :idOrName) and p.deleted = false")
    Page<Product> search(@Param("idOrName") String idOrName, Pageable pageable);

    @Query("select p from Product p where p.id = :id and p.deleted = false")
    Optional<Product> findById(@Param("id") String id);

}
