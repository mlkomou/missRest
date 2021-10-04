package com.miss.api.controller;

import com.miss.api.auth.entity.Role;
import com.miss.api.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RoleController {
    final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @PostMapping(value = "/roless/create")
    public ResponseEntity<Map<String, Object>> saveRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }
    @GetMapping(value = "/roless/liste")
    public ResponseEntity<Map<String, Object>> getRoles() {
        return roleService.getAllRole();
    }
}
