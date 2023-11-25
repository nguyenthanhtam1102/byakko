package com.byakko.service.sales.repositories;

import com.byakko.service.sales.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    @Query("select o from Order o where lower(o.id) like :id")
    Page<Order> search(@Param("id") String id, Pageable pageable);

}
