package com.byakko.service.authentication.dataaccess.adapter;

import com.byakko.common.application.dto.BaseResponse;
import com.byakko.service.authentication.application.utils.JwtHelper;
import com.byakko.service.authentication.domain.domainapplication.dto.request.CreateMenuToPermissionGroupsRequest;
import com.byakko.service.authentication.domain.domainapplication.dto.request.MenuItemRequest;
import com.byakko.service.authentication.domain.domainapplication.dto.request.PermissionAndPermissionGroups;
import com.byakko.service.authentication.domain.domainapplication.dto.request.PermissionRequest;
import com.byakko.service.authentication.domain.domainapplication.dto.response.MenuItemResponse;
import com.byakko.service.authentication.dataaccess.entity.*;
import com.byakko.service.authentication.dataaccess.repository.*;
import com.byakko.service.authentication.domain.domainapplication.dto.response.PermissionGroupsResponse;
import com.byakko.service.authentication.domain.domaincore.services.AdminPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminPageServiceImp implements AdminPageService {
    @Autowired
    private MenuItemRepository menuitemRepository;
    @Autowired
    private MenuJpaRepository menuRepository;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private PermissionGroupsRepository permissionGroupsRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Autowired
    private MenuToPermissionRepository menuToPermissionRepository;
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public BaseResponse getAllPermission() {
        BaseResponse res = new BaseResponse();
        try{
            List<PermissionGroupsEntity> permissionGroupsList = permissionGroupsRepository.findAll();
            List<PermissionGroupsResponse> permissionGroupsResponses = new ArrayList<>();
            for (PermissionGroupsEntity i: permissionGroupsList) {
                PermissionGroupsResponse permissionGroupsResponse = new PermissionGroupsResponse();
                permissionGroupsResponse.setId(i.getId());
                permissionGroupsResponse.setName(i.getName());
                permissionGroupsResponse.setPermissionsList(permissionRepository.findByPermissionGroups_Id(i.getId()));
                permissionGroupsResponses.add(permissionGroupsResponse);
            }
            res.setData(permissionGroupsResponses);
            res.setMessage("Get data successfully");
            res.setStatusCode(200);
        }catch(Exception e){
            System.out.print(e);
            res.setStatusCode(400);
            res.setMessage("Can not find any data");
            res.setData("");
        }
        return res;
    }
//    1 người thì có thể có nhiều cái khả năng ví dụ create read update delete ứng với mỗi menu_item khác nhau
    @Override
    public BaseResponse getAllPage() {
        BaseResponse res = new BaseResponse();
        try{
            List<PageEntity> pageList = pageRepository.findAll();
            res.setStatusCode(200);
            res.setMessage("Get Data Successfully");
            res.setData(pageList);
        }catch(Exception e){
            res.setStatusCode(400);
            res.setMessage("Can not add data");
            res.setData("");
        }
        return res;
    }
    @Override
    public BaseResponse getMenuById(int id) {
        BaseResponse res = new BaseResponse();
        try{
            res.setData(menuRepository.findById(id));
            res.setStatusCode(200);
            res.setMessage("Get Data Successfully");
        }catch(Exception e){
            res.setStatusCode(400);
            res.setMessage("Can not add data");
            res.setData("");
        }
        return res;
    }

    @Override
    public BaseResponse getAllMenu() {
        BaseResponse res = new BaseResponse();
        try{
            res.setStatusCode(200);
            res.setMessage("Get data Successfully");
            res.setData(menuRepository.findAll());
        }catch(Exception e){
            res.setStatusCode(400);
            res.setMessage("Can not add data");
            res.setData("");
        }
        return res;
    }

    @Override
    public BaseResponse deleteMenuItemById(int MenuItemId) {
        BaseResponse res = new BaseResponse();
        try{
            menuToPermissionRepository.deleteByMenuItem(menuitemRepository.findById(MenuItemId).get());
            menuitemRepository.deleteById(MenuItemId);
            res.setStatusCode(200);
            res.setMessage("Get data Successfully");
            res.setData("");
        }catch(Exception e){
            res.setStatusCode(400);
            res.setMessage("Can not add data");
            res.setData("");
        }
        return res;
    }

    @Override
    public BaseResponse getAllMenuItem() {
        BaseResponse res = new BaseResponse();
        try{
            List<MenuItemEntity> menuItemList = menuitemRepository.findAll();
            List<MenuItemResponse> menuItemResponseList = new ArrayList<>();
            for (MenuItemEntity i:menuItemList) {
                MenuItemResponse menuItemResponse = new MenuItemResponse();
                menuItemResponse.setId(i.getId());
                menuItemResponse.setName(i.getName());
                menuItemResponse.setMenu(i.getMenu());
                menuItemResponse.setPage(i.getPage());
                if(i.getParentMenu()==null){
                    menuItemResponse.setParent_id(0);
                }else{
                    menuItemResponse.setParent_id(i.getParentMenu().getId());
                }
                menuItemResponseList.add(menuItemResponse);
            }
            res.setData(menuItemResponseList);
            res.setMessage("Get data successfully");
            res.setStatusCode(200);
        }catch(Exception e){
            res.setStatusCode(400);
            res.setMessage("Can not find any data");
            res.setData("");
        }
        return res;
    }
    @Override
    public BaseResponse getMenuItemById(int id) {
        BaseResponse res = new BaseResponse();
        try{
            List<MenuItemEntity> menuItemList = menuitemRepository.findByMenuId(id);
            List<MenuItemResponse> menuItemResponseList = new ArrayList<>();
            for (MenuItemEntity i:menuItemList) {
                MenuItemResponse menuItemResponse = new MenuItemResponse();
                menuItemResponse.setId(i.getId());
                menuItemResponse.setName(i.getName());
                menuItemResponse.setMenu(i.getMenu());
                menuItemResponse.setPage(i.getPage());
                if(i.getParentMenu()==null){
                    menuItemResponse.setParent_id(0);
                }else{
                    menuItemResponse.setParent_id(i.getParentMenu().getId());
                }
                menuItemResponseList.add(menuItemResponse);
            }
            res.setData(menuItemResponseList);
            res.setMessage("Get data successfully");
            res.setStatusCode(200);
        }catch(Exception e){
            res.setStatusCode(400);
            res.setMessage("Can not find any data");
            res.setData("");
        }
        return res;
    }
    @Override
    public BaseResponse createMenuItem(MenuItemRequest menuItemRequest) {
        BaseResponse res = new BaseResponse();
        try {
            MenuItemEntity menuItem = new MenuItemEntity();
            menuItem.setName(menuItemRequest.getMenu_item_name());
            menuItem.setMenu(menuRepository.findById(menuItemRequest.getMenu_id()).get());
            menuItem.setPage(pageRepository.findById(menuItemRequest.getPage_id()).get());
            if(menuItemRequest.getParent_id()!=0) {
                menuItem.setParentMenu(menuitemRepository.findById(menuItemRequest.getParent_id()).get());
            }
            MenuItemEntity menuitem = menuitemRepository.save(menuItem);
            for (PermissionRequest i: menuItemRequest.getPermissionRequestList()) {
                MenusToPermissionsEntity menusToPermissions = new MenusToPermissionsEntity();
                menusToPermissions.setMenuItem(menuItem);
                menusToPermissions.setPermissions(permissionRepository.findById(i.getId()).get());
                menuToPermissionRepository.save(menusToPermissions);
            }
            res.setStatusCode(200);
            res.setMessage("Data added successfully");
            res.setData(menuitem);
        }catch(Exception e){
            res.setStatusCode(400);
            res.setMessage("Can not add data");
            res.setData("");
        }
        return res;
    }
    @Override
    public BaseResponse createMenu(String name){
        BaseResponse res = new BaseResponse();
        try{
            MenuEntity menu = new MenuEntity();
            menu.setName(name);
            MenuEntity MenuReturn = menuRepository.save(menu);
            res.setData(MenuReturn);
            res.setMessage("Data added Successfully");
            res.setStatusCode(200);
        }catch(Exception e){
            res.setStatusCode(400);
            res.setMessage("Can not add data");
            res.setData("");
        }
        return res;
    }
    @Override
    public BaseResponse createMenuToPermissionGroups(CreateMenuToPermissionGroupsRequest createMenuToPermissionGroupsRequest) {
        BaseResponse res = new BaseResponse();
        try{
            for (PermissionAndPermissionGroups i: createMenuToPermissionGroupsRequest.getPermissionAndMenus()) {
                PermissionsEntity permissions = new PermissionsEntity();
                permissions.setName(i.getName());
                permissions.setPermissionGroups(permissionGroupsRepository.findById(i.getId()).get());
                permissions.setCode(i.getCode());
                PermissionsEntity permissions1 = permissionRepository.save(permissions);
                MenusToPermissionsEntity menusToPermissions = new MenusToPermissionsEntity();
                menusToPermissions.setActive(true);
                menusToPermissions.setMenuItem(menuitemRepository.findById(createMenuToPermissionGroupsRequest.getMenu_item_id()).get());
                menusToPermissions.setPermissions(permissionRepository.findById(permissions1.getId()).get());
                menuToPermissionRepository.save(menusToPermissions);
            }
            res.setData("");
            res.setMessage("Data added Successfully");
            res.setStatusCode(200);
        }catch(Exception e){
            res.setStatusCode(400);
            res.setMessage("Can not add data");
            res.setData("");
        }
        return res;
    }

    @Override
    public BaseResponse createPermissionGroups(String name) {
        BaseResponse res = new BaseResponse();
        try{
            PermissionGroupsEntity permissionGroups = new PermissionGroupsEntity();
            permissionGroups.setName(name);
            permissionGroupsRepository.save(permissionGroups);
            res.setData("");
            res.setMessage("Data added Successfully");
            res.setStatusCode(200);
        }catch(Exception e){
            res.setStatusCode(400);
            res.setMessage("Can not add data");
            res.setData("");
        }
        return res;
    }

    //  delete
    @Override
    public BaseResponse deletePermissionGroups(int id) {
        return null;
    }
    @Override
    public BaseResponse deleteMenuById(int id) {
        BaseResponse res = new BaseResponse();
        try{
            List<MenuItemEntity> menuItemEntities = menuitemRepository.findByMenuId(id);
            for (MenuItemEntity m:menuItemEntities) {
                menuToPermissionRepository.deleteByMenuItem(m);
            }
            menuitemRepository.deleteByMenu_Id(id);
            menuRepository.deleteById(id);
            res.setData("");
            res.setMessage("Delete successfully");
            res.setStatusCode(200);
        }catch(Exception e){
            res.setMessage("Can not delete data");
            res.setData("");
            res.setStatusCode(400);
        }
        return res;
    }

    @Override
    public BaseResponse updateMenu(String name, int id) {
        BaseResponse res = new BaseResponse();
        try{
            MenuEntity menu = menuRepository.findById(id).get();
            menu.setName(name);
            menuRepository.save(menu);
            res.setData("");
            res.setMessage("update successfully");
            res.setStatusCode(200);
        }catch(Exception e){
            res.setMessage("Can not update data");
            res.setData("");
            res.setStatusCode(400);
        }
        return res;
    }
}
