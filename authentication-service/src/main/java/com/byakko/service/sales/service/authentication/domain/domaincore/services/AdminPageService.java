package com.byakko.service.sales.service.authentication.domain.domaincore.services;

import com.byakko.service.sales.common.application.dto.BaseResponse;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.request.CreateMenuToPermissionGroupsRequest;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.request.MenuItemRequest;

public interface AdminPageService {
    BaseResponse getAllMenu();
    BaseResponse deleteMenuItemById(int MenuItemId);
    BaseResponse getAllMenuItem();
    BaseResponse getMenuItemById(int id);
    BaseResponse getAllPage();
    BaseResponse getMenuById(int id);
    BaseResponse getAllPermission();
    BaseResponse createMenu(String name);
    BaseResponse createMenuToPermissionGroups(CreateMenuToPermissionGroupsRequest createMenuToPermissionGroupsRequest);
    BaseResponse createPermissionGroups(String name);
    BaseResponse createMenuItem(MenuItemRequest menuItemRequest);
    BaseResponse deletePermissionGroups(int id);
    BaseResponse deleteMenuById(int id);
    BaseResponse updateMenu(String name,int id);
}
