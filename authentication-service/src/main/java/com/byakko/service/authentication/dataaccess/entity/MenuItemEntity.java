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
@Table(name = "menu_item")
public class MenuItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private int id;
    @NotNull
    @Column(name = "menu_item_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private MenuEntity menu;
    @ManyToOne
    @JoinColumn(name = "page_id")
    private PageEntity page;
    @OneToMany(mappedBy = "menuItem")
    @JsonIgnore
    private Set<MenusToPermissionsEntity> menusToPermissionsSet;
    @ManyToOne
    @JoinColumn(name = "parent_id",referencedColumnName = "menu_item_id")
    private MenuItemEntity parentMenu;
    @OneToMany(mappedBy = "parentMenu")
    @JsonIgnore
    private Set<MenuItemEntity> menuItemSet;
}
