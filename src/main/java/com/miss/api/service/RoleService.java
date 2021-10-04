package com.miss.api.service;

import com.miss.api.auth.entity.Permission;
import com.miss.api.auth.entity.Role;
import com.miss.api.repos.RoleRepository;
import netscape.security.Privilege;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService {
    final RoleRepository roleRepository;
    Map<String, Object> response = new HashMap<>();

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public ResponseEntity<Map<String, Object>> saveRole(Role role) {
        System.out.println("role name "+ role.getName()) ;
        try {
            Set<Permission> permissions = role.getPermissions();
            role.setPermissions(permissions);

            roleRepository.save(role);
            response.put("message", "Role enregistrée avec succès.");
            response.put("response", role);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de l'année échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    public ResponseEntity<Map<String, Object>> updateRole(Role role) {
//        try {
//            Optional<Role> roleData = roleRepository.findById(role.getId());
//            if(roleData.isPresent()) {
//                roleRepository.save(role);
//                response.put("message", "Role enregistré avec succès.");
//                response.put("response", role);
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

    public ResponseEntity<Map<String, Object>> getAllRole() {
        try {
            List<Role> roles = roleRepository.findAll();
            response.put("message", "Roles trouves");
            response.put("response", roles);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Role());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
