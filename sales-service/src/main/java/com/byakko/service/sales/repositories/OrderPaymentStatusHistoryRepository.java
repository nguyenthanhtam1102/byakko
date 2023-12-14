package com.byakko.service.sales.repositories;

import com.byakko.service.sales.models.OrderPaymentStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPaymentStatusHistoryRepository extends JpaRepository<OrderPaymentStatusHistory, String> {
}
