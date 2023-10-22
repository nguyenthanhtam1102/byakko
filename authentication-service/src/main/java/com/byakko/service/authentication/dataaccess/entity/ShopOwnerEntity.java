package com.byakko.service.authentication.dataaccess.entity;

import com.byakko.service.authentication.domain.domaincore.valueobject.EmployeeStatus;
import com.byakko.service.authentication.domain.domaincore.valueobject.ShopOwnerStatus;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "shop_owners")
public class ShopOwnerEntity extends UserEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;



    @Column(length = 255, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private ShopOwnerStatus status;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

}
