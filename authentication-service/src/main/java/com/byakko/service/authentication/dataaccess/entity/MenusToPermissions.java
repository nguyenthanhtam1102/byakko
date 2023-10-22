package com.byakko.service.authentication.dataaccess.entity;

import com.byakko.service.authentication.dataaccess.entity.ids.MenusToPermissionsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(MenusToPermissionsId.class)
@Entity
@Table(name = "menu_to_permissions")
public class MenusToPermissions implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "menu_item_id",insertable = false,updatable = false)
    private MenuItem menuItem;
    @Id
    @ManyToOne
    @JoinColumn(name = "permissions_id",updatable = false,insertable = false)
    private Permissions permissions;
    @Column(name = "active")
    private boolean active;
}
