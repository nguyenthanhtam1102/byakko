package com.byakko.service.sales.service.authentication.domain.domainapplication.dto.request;

import java.util.List;

public class CreateMenuToPermissionGroupsRequest {
    private int menu_item_id;
    private List<PermissionAndPermissionGroups> permissionAndMenus;

    public int getMenu_item_id() {
        return menu_item_id;
    }

    public void setMenu_item_id(int menu_item_id) {
        this.menu_item_id = menu_item_id;
    }

    public List<PermissionAndPermissionGroups> getPermissionAndMenus() {
        return permissionAndMenus;
    }

    public void setPermissionAndMenus(List<PermissionAndPermissionGroups> permissionAndMenus) {
        this.permissionAndMenus = permissionAndMenus;
    }
}
