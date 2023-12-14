package com.byakko.service.sales.models;

import com.byakko.common.DomainConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "order_payment_status_histories")
public class OrderPaymentStatusHistory {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderPaymentStatus status;

    @Column(nullable = false)
    private Long timestamp;

    private String note;

    public OrderPaymentStatusHistory() {
        id = UUID.randomUUID().toString().replace("-", "");
        timestamp = ZonedDateTime.now(ZoneId.of(DomainConstants.ZONE_ID)).toEpochSecond();
    }

}
