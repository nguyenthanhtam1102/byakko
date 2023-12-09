package com.byakko.service.sales.service.production.dataaccess.repository;

import com.byakko.service.sales.service.production.dataaccess.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryJpaRepository extends JpaRepository<ProductCategoryEntity, String> {

    List<ProductCategoryEntity> findByParent(ProductCategoryEntity parent);

}
