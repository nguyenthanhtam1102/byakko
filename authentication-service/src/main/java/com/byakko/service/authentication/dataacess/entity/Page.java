package com.byakko.service.authentication.dataacess.entity;

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
@Table(name = "page")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_id")
    private int id;
    @NotNull
    @Column(name = "page_name")
    private String name;
    @Column(name = "description")
    private String description;
    @NotNull
    @Column(name = "path")
    private String path;
    @OneToMany(mappedBy = "page")
    @JsonIgnore
    private Set<MenuItem> menuItemSet;
}
