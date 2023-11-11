package com.byakko.service.product.repositories;

import com.byakko.service.product.models.GlobalOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalOptionRepository extends JpaRepository<GlobalOption, String> {

    @Query("select o from GlobalOption o where lower(o.id) like :idOrName or lower(o.name) like :idOrName")
    Page<GlobalOption> findAllByIdOrName(String idOrName, Pageable pageable);

}
