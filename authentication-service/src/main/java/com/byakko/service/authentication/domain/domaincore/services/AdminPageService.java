package com.byakko.service.authentication.domain.domaincore.services;

import com.byakko.common.application.dto.BaseResponse;
import com.byakko.service.authentication.domain.domainapplication.dto.request.CreateMenuToPermissionGroupsRequest;
import com.byakko.service.authentication.domain.domainapplication.dto.request.DeleteMenuRequest;
import com.byakko.service.authentication.domain.domainapplication.dto.request.MenuItemRequest;

import java.security.NoSuchAlgorithmException;

public interface AdminPageService {
    BaseResponse getAllMenu();
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
}