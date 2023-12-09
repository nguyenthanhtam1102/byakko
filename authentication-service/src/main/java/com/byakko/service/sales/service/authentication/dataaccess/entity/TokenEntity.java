package com.byakko.service.sales.service.authentication.dataaccess.entity;

import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "tokens")
public class TokenEntity {

    @Id
    @Column(name = "hashed_data", nullable = false)
    private String hashedData;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "expired_time", nullable = false)
    private ZonedDateTime expiredTime;

    @Column(name = "random_data", nullable = false)
    private String randomData;

    @Column(name = "used")
    private Boolean used;

}
