package com.byakko.service.sales.service.authentication.application.rest;

import com.byakko.service.authentication.domain.domainapplication.dto.request.*;
import com.byakko.service.sales.service.authentication.domain.domaincore.services.AdminPageService;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.request.CreateMenuToPermissionGroupsRequest;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.request.CreatePermissionGroupsRequest;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.request.MenuItemRequest;
import com.byakko.service.sales.service.authentication.domain.domainapplication.dto.request.MenuRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminPage")
public class AdminPageController {
    
    @Autowired
    private AdminPageService adminservice;
//  Get
    @GetMapping("/getMenuItemById")
    public ResponseEntity<?> getMenuItemById(@RequestParam int Menuid){
        return new ResponseEntity<>(adminservice.getMenuItemById(Menuid), HttpStatus.OK);
    }
    @GetMapping("/getAllPage")
    public ResponseEntity<?> getAllPage(){
        return new ResponseEntity<>(adminservice.getAllPage(),HttpStatus.OK);
    }
    @GetMapping("/getMenuById")
    public ResponseEntity<?> getMenuById(@RequestParam int Menuid){
        return new ResponseEntity<>(adminservice.getMenuById(Menuid),HttpStatus.OK);
    }
    @GetMapping("/getAllPermissions")
    public ResponseEntity<?> getAllPermissions(){
        return new ResponseEntity<>(adminservice.getAllPermission(),HttpStatus.OK);
    }
    @GetMapping("/getAllMenu")
    public ResponseEntity<?> getAllMenu(){
        return new ResponseEntity<>(adminservice.getAllMenu(),HttpStatus.OK);
    }
//    Post
    @PostMapping("/createMenu")
    public ResponseEntity<?> createMenu(@RequestBody MenuRequest menuRequest){
        return new ResponseEntity<>(adminservice.createMenu(menuRequest.getName()),HttpStatus.OK);
    }
    @PostMapping("/createMenuItem")
    public ResponseEntity<?> createMenuItem(@RequestBody MenuItemRequest menuItemRequest){
        return new ResponseEntity<>(adminservice.createMenuItem(menuItemRequest),HttpStatus.OK);
    }
    @PostMapping("/createPermissionGroups")
    public ResponseEntity<?> createPermissionGroups(@RequestBody CreatePermissionGroupsRequest createPermissionGroupsRequest){
        return new ResponseEntity<>(adminservice.createPermissionGroups(createPermissionGroupsRequest.getName()),HttpStatus.OK);
    }
    @PostMapping("/createMenuToPermissionGroup")
    public ResponseEntity<?> createMenuToPermissionGroup(@RequestBody CreateMenuToPermissionGroupsRequest createMenuToPermissionGroupsRequest){
        return new ResponseEntity<>(adminservice.createMenuToPermissionGroups(createMenuToPermissionGroupsRequest),HttpStatus.OK);
    }
//    delete
    @DeleteMapping("/deleteMenuById")
    public ResponseEntity<?> deleteMenuById(@RequestParam int Menuid){
        return new ResponseEntity<>(adminservice.deleteMenuById(Menuid),HttpStatus.OK);
    }
    @DeleteMapping("/deleteMenuItemById")
    public ResponseEntity<?> deleteMenuItemById(@RequestParam int MenuItemId){
        return new ResponseEntity<>(adminservice.deleteMenuItemById(MenuItemId),HttpStatus.OK);
    }
//    update
    @PostMapping("/updateMenu")
    public ResponseEntity<?> updateMenu(@RequestParam String name,@RequestParam int menuId){
        return new ResponseEntity<>(adminservice.updateMenu(name,menuId),HttpStatus.OK);
    }
}
