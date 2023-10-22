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
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_item_id")
    private int id;
    @NotNull
    @Column(name = "menu_item_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;
    @OneToMany(mappedBy = "menuItem")
    @JsonIgnore
    private Set<MenusToPermissions> menusToPermissionsSet;
    @ManyToOne
    @JoinColumn(name = "parent_id",referencedColumnName = "menu_item_id")
    private MenuItem parentMenu;
    @OneToMany(mappedBy = "parentMenu")
    @JsonIgnore
    private Set<MenuItem> menuItemSet;
}
