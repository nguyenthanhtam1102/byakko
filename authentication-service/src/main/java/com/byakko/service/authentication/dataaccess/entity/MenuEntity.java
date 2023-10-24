package com.byakko.service.authentication.dataaccess.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "menus")
public class MenuEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int id;
    @NotNull
    @Column(name = "menu_name")
    private String name;
    @OneToMany(mappedBy = "menu")
    @JsonIgnore
    private Set<MenuItemEntity> menuItemSet;
    @OneToMany(mappedBy = "menu")
    @JsonIgnore
    private Set<RoleEntity> employeeEntities;

    public MenuEntity(int id) {
        this.id = id;
    }
}
