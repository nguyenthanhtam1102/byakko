package com.byakko.service.product.repositories;

import com.byakko.service.product.models.InventoryCountSheet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryCountSheetRepository extends JpaRepository<InventoryCountSheet, String> {

    @Query("select ics from InventoryCountSheet ics " +
            "where (" +
            "lower(ics.id) like :query " +
            "or lower(ics.creator.id) like :query " +
            "or lower(ics.creator.firstName) like :query " +
            "or lower(ics.creator.lastName) like :query " +
            "or lower(ics.implementer.id) like :query " +
            "or lower(ics.implementer.firstName) like :query " +
            "or lower(ics.implementer.lastName) like :query) " +
            "and ics.deleted = false")
    Page<InventoryCountSheet> search(@Param("query") String query, Pageable pageable);

    @Query("select ics from InventoryCountSheet ics where ics.id = :id and ics.deleted = false")
    Optional<InventoryCountSheet> findById(@Param("id") String id);

}
