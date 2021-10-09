package com.miss.api.controller;

import com.miss.api.auth.entity.User;
import com.miss.api.service.UserRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRoleController {
    final UserRoleService userService;

    public UserRoleController(UserRoleService userService) {
        this.userService = userService;
    }

    @PutMapping(value = "/user/update")
    public ResponseEntity<Map<String, Object>> updateParticipante(@RequestBody User user) {
        return userService.saveUserRole(user);
    }
}
