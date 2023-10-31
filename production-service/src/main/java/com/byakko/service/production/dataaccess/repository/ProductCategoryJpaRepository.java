package com.byakko.service.production.dataaccess.repository;

import com.byakko.service.production.dataaccess.entity.ProductCategoryEntity;
import com.byakko.service.production.domain.domaincore.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryJpaRepository extends JpaRepository<ProductCategoryEntity, String> {

    List<ProductCategoryEntity> findByParent(ProductCategoryEntity parent);

}
