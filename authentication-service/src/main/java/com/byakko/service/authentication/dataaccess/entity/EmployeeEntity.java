package com.byakko.service.authentication.dataaccess.entity;

import com.byakko.service.authentication.domain.domaincore.valueobject.EmployeeStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity
@Table(name = "employees")
public class EmployeeEntity extends UserEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(length = 255, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private EmployeeStatus status;

    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private RoleEntity role;

}
