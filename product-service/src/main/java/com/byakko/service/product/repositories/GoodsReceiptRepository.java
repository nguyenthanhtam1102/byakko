package com.byakko.service.product.repositories;

import com.byakko.service.product.models.GoodsReceipt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsReceiptRepository extends JpaRepository<GoodsReceipt, String> {

    @Query("select gr from GoodsReceipt gr where gr.deleted = false")
    Page<GoodsReceipt> findAll(Pageable pageable);

}
