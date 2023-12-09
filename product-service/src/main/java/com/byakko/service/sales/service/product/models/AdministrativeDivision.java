package com.byakko.service.sales.service.product.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "administrative_divisions")
public class AdministrativeDivision {

    @Id
    private String code;

    @Enumerated(EnumType.STRING)
    private AdministrativeDivisionType etype;

    private String type;
    private String slug;
    private String name;

    @Column(name = "name_with_type")
    @JsonProperty("name_with_type")
    private String nameWithType;

    private String path;

    @Column(name = "path_with_type")
    @JsonProperty("path_with_type")
    private String pathWithType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_code")
    private AdministrativeDivision parent;

    @Transient
    @JsonProperty("parent_code")
    private String parentCode;

}
