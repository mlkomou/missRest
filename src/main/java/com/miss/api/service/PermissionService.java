package com.miss.api.service;

import com.miss.api.auth.entity.Permission;
import com.miss.api.repos.PermissionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionService {
    final PermissionRepository permissionRepository;
    Map<String, Object> response = new HashMap<>();

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }


    public ResponseEntity<Map<String, Object>> savePermission(Permission permission) {
        System.out.println("permission name "+ permission.getName()) ;
        try {
            permissionRepository.save(permission);
            response.put("message", "Permission enregistrée avec succès.");
            response.put("response", permission);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de l'année échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    public ResponseEntity<Map<String, Object>> updatePermission(Permission permission) {
//        try {
//            Optional<Permission> permissionData = permissionRepository.findById(permission.getId());
//            if(permissionData.isPresent()) {
//                permissionRepository.save(permission);
//                response.put("message", "Permission enregistré avec succès.");
//                response.put("response", permission);
//                response.put("code", 100);
//            }
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            response.put("message", "Enregistrement du rôle échoué.");
//            response.put("response", new Object());
//            response.put("code", 200);
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    public ResponseEntity<Map<String, Object>> getAllPermission() {
        try {
            List<Permission> permissions = permissionRepository.findAll();
            response.put("message", "Permissions trouves");
            response.put("response", permissions);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Permission());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
