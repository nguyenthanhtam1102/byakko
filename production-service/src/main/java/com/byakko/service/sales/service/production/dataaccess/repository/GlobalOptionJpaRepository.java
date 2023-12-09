package com.byakko.service.sales.service.production.dataaccess.repository;

import com.byakko.service.sales.service.production.dataaccess.entity.GlobalOptionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalOptionJpaRepository extends JpaRepository<GlobalOptionEntity, String> {

    @Query("select distinct op " +
            "from GlobalOptionEntity op join GlobalOptionValueEntity opv " +
            "on op.id = opv.option.id " +
            "where op.name like :optionName or opv.name like :optionValueName")
    Page<GlobalOptionEntity> findByOptionNameOrOptionValueName(@Param("optionName") String optionName, @Param("optionValueName") String optionValueName, Pageable pageable);

}
