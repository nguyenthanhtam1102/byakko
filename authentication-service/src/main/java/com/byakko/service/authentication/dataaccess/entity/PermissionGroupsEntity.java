package com.byakko.service.authentication.dataaccess.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "permission_groups")
public class PermissionGroupsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_groups_id")
    private int id;
    @NotNull
    @Column(name = "permission_groups_name")
    private String name;
    @OneToMany(mappedBy = "permissionGroups")
    @JsonIgnore
    private Set<PermissionsEntity> permissionsSet;
}
