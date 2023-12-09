package com.byakko.service.sales.service.product.repositories;

import com.byakko.service.sales.service.product.models.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, String> {

    @Query("select s from Supplier s where (lower(s.id) like :idOrName or lower(s.name) like :idOrName) and s.deleted = false")
    Page<Supplier> search(@Param("idOrName") String idOrName, Pageable pageable);

    @Query("select s from Supplier s where s.id = :id and s.deleted = false")
    Optional<Supplier> findById(@Param("id") String id);

}
