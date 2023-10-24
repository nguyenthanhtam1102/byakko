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
public class MenusToPermissionsEntity implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "menu_item_id",insertable = false,updatable = false)
    private MenuItemEntity menuItem;
    @Id
    @ManyToOne
    @JoinColumn(name = "permissions_id",updatable = false,insertable = false)
    private PermissionsEntity permissions;
    @Column(name = "active")
    private boolean active;
}
