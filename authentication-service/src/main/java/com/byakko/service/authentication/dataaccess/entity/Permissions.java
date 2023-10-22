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
@Table(name = "permissions")
public class Permissions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permissions_id")
    private int id;
    @NotNull
    @Column(name = "code")
    private String code;
    @NotNull
    @Column(name = "permissions_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "permission_groups_id")
    private PermissionGroups permissionGroups;
    @OneToMany(mappedBy = "permissions")
    @JsonIgnore
    private Set<MenusToPermissions> menusToPermissionsSet;
}
