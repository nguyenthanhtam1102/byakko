package com.byakko.service.product.repositories;

import com.byakko.service.product.models.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    @Query("select c from Category c where (lower(c.id) like :idOrName or lower(c.name) like :idOrName) and c.deleted = false ")
    Page<Category> findAllByIdOrName(@Param("idOrName") String idOrName, Pageable pageable);

    @Query("select c from Category c where c.id = :id and c.deleted = false ")
    Optional<Category> findById(@Param("id") String id);

    @Query("select c from Category c where c.parent is null and c.deleted = false")
    Page<Category> findByParentIsNull(Pageable pageable);

    List<Category> findAllByParentAndDeletedIsFalse(Category parent);

}
