package com.byakko.service.product.repositories;

import com.byakko.service.product.models.OptionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionValueRepository extends JpaRepository<OptionValue, String> {
}
