package com.byakko.service.product.repositories;

import com.byakko.service.product.models.GlobalOptionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalOptionValueRepository extends JpaRepository<GlobalOptionValue, String> {
}
