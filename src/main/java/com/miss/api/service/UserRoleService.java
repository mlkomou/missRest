package com.miss.api.service;

import com.miss.api.auth.entity.Role;
import com.miss.api.auth.entity.User;
import com.miss.api.auth.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class UserRoleService {
    final UserRepository userRepository;
    Map<String, Object> response = new HashMap<>();

    public UserRoleService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity<Map<String, Object>> saveUserRole(User user) {
        System.out.println("user name "+ user.getUsername()) ;
        try {
            Set<Role> roles = user.getRoles();
            user.setRoles(roles);

            Optional<User> userToUpdate = userRepository.findById(user.getId());
            if (userToUpdate.isPresent()) {
                user.setPassword(userToUpdate.get().getPassword());
                userRepository.save(user);
                response.put("message", "Role enregistrée avec succès.");
                response.put("response", user);
                response.put("code", 100);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de l'année échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
