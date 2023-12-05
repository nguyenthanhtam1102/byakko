package com.byakko.service.product.repositories;

import com.byakko.service.product.models.PurchaseOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, String> {

    @Query("select po from PurchaseOrder po " +
            "where (" +
            "lower(po.id) like :query " +
            "or lower(po.supplier.id) like :query " +
            "or lower(po.supplier.name) like :query " +
//            "or lower(po.employee.id) like :query " +
//            "or lower(po.employee.firstName) like :query " +
//            "or lower(po.employee.lastName) like :query " +
            ")and po.deleted = false")
    Page<PurchaseOrder> findAllByIdOrSupplierOrEmployee(@Param("query") String query, Pageable pageable);

    @Query("select po from PurchaseOrder po where po.id = :id and po.deleted = false")
    Optional<PurchaseOrder> findById(@Param("id") String id);

}
