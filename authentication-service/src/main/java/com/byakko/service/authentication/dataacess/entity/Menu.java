package com.byakko.service.authentication.dataacess.entity;

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
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private int id;
    @NotNull
    @Column(name = "menu_name")
    private String name;
    @OneToMany(mappedBy = "menu")
    @JsonIgnore
    private Set<MenuItem> menuItemSet;
}
