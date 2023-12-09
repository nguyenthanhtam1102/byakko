package com.byakko.service.sales.service.production.dataaccess.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "global_options")
public class GlobalOptionEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(length = 60, nullable = false)
    private String name;

    @OneToMany(mappedBy = "option", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<GlobalOptionValueEntity> values;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

}
