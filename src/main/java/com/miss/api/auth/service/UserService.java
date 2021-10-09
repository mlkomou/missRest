package com.miss.api.auth.service;


import com.miss.api.auth.entity.User;
import com.miss.api.auth.repository.UserRepository;
import com.miss.api.response.CResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.*;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
//    Map<String, Object> response = new HashMap<>();

    public CResponse<List<User>> listOfUsers() {
        try {
            List<User> users = userRepository.findAll();
            return CResponse.success(users, users.size() + " utilisateur.s trouve.s");
        } catch (Exception e) {
            e.printStackTrace();
            return CResponse.error("Une erreur est servenue!");
        }
    }

    public CResponse<User> create(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            User utilisateur = userRepository.save(user);
            return CResponse.success(utilisateur, "Utilisateur ajoutee avec succes");
        } else {
            return CResponse.error("Ce nom d'utilisateur est déjà utilisé");
        }
    }

    public CResponse<User> edit(User user) {
        System.out.println("user " + user.getUsername());
        try {
            // if empty so => not exist, else => exist
            boolean existingUser = userRepository.checkExistingUser(user.getId(), user.getUsername()).isEmpty();
            if (!existingUser) {
                return CResponse.error("Ce nom d'utilisateur existe deja");
            }
            User oldUser = userRepository.findUserById(user.getId());
            if (oldUser == null) {
                return CResponse.error("Cet utilisateur n'existe pas");
            }
            if (!StringUtils.isEmpty(user.getPassword())) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            } else {
                user.setPassword(oldUser.getPassword());
            }
            userRepository.save(user);
            return CResponse.success(user, "Utilisateur modifie avec succes!");

        } catch (Exception e) {
            e.printStackTrace();
            return CResponse.error("Une erreur est servenue!");
        }
    }

    public CResponse<User> remove(Long id) {
        try {
            if (userRepository.findById(id).isPresent()) {
                userRepository.deleteById(id);
            }
            return CResponse.validate("Suppression avec succes");

        } catch (Exception e) {
            e.printStackTrace();
            return CResponse.error("Une erreur est servenue");
        }
    }

    public CResponse<User> getUser(Long id) {
        try {
            return CResponse.success(userRepository.findUserById(id), "Recuperer avec succes");
        } catch (Exception e) {
            e.printStackTrace();
            return CResponse.error("Ce nom existe deja!");
        }
    }

//    public ResponseEntity<Map<String, Object>> setUserRole(User user) {
//        System.out.println("user name "+ user.getUsername()) ;
//        User user2 = new User();
//        try {
//
//
//            Optional<User> user1 = userRepository.findById(user.getId());
//            if(user1.isPresent()) {
//                Set<Role> roles = user.getRoles();
//                user.setRoles(roles);
//              user2 = userRepository.save(user);
//            }
//
//            response.put("message", "Role enregistrée avec succès.");
//            response.put("response", user2);
//            response.put("code", 100);
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            response.put("message", "Enregistrement de l'année échoué.");
//            response.put("response", new Object());
//            response.put("code", 200);
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}

