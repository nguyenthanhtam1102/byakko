package com.byakko.service.authentication.dataaccess.entity;

import com.byakko.service.authentication.domain.domaincore.valueobject.CustomerStatus;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Table(name = "customers")
public class CustomerEntity extends UserEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(name = "firstname", length = 30, nullable = false)
    private String firstName;

    @Column(name = "lastname", length = 30, nullable = false)
    private String lastName;

    @Column(length = 10)
    private String phone;

    @Column(length = 60)
    private String email;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean verified;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private CustomerStatus status;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

}
