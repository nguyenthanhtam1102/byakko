package com.byakko.service.production.dataaccess.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "global_option_values")
public class GlobalOptionValueEntity {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Column(length = 60, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", nullable = false)
    private GlobalOptionEntity option;

}
