package com.byakko.service.sales.service.product.repositories;

import com.byakko.service.sales.service.product.models.Asset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, String> {

    @Query("select a from Asset a where (lower(a.id) like :idOrName or lower(a.filename) like :idOrName)")
    Page<Asset> findAllByIdOrName(@Param("idOrName") String idOrName, Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "delete from products_to_assets where asset_id = :assetId", nativeQuery = true)
    void deleteAssetFromProduct(@Param("assetId") String assetId);

}
