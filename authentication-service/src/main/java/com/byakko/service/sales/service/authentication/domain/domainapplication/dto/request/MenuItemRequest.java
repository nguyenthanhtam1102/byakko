package com.byakko.service.sales.service.authentication.domain.domainapplication.dto.request;

import java.util.List;

public class MenuItemRequest {
    private String menu_item_name;
    private int menu_id;
    private int page_id;
    private int parent_id;
    private List<PermissionRequest> permissionRequestList;

    public List<PermissionRequest> getPermissionRequestList() {
        return permissionRequestList;
    }

    public void setPermissionRequestList(List<PermissionRequest> permissionRequestList) {
        this.permissionRequestList = permissionRequestList;
    }

    public String getMenu_item_name() {
        return menu_item_name;
    }
    public void setMenu_item_name(String menu_item_name) {
        this.menu_item_name = menu_item_name;
    }
    public int getMenu_id() {
        return menu_id;
    }
    public void setMenu_id(int menu_id) {
        this.menu_id = menu_id;
    }
    public int getPage_id() {
        return page_id;
    }
    public void setPage_id(int page_id) {
        this.page_id = page_id;
    }
    public int getParent_id() {
        return parent_id;
    }
    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }
}
