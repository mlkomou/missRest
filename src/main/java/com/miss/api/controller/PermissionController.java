package com.miss.api.controller;

import com.miss.api.auth.entity.Permission;
import com.miss.api.service.PermissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class PermissionController {
    final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }


    @PostMapping(value = "/permissionss/create")
    public ResponseEntity<Map<String, Object>> savePermission(@RequestBody Permission permission) {
        return permissionService.savePermission(permission);
    }
    @GetMapping(value = "/permissionss/liste")
    public ResponseEntity<Map<String, Object>> getPermissions() {
        return permissionService.getAllPermission();
    }
}
