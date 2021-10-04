package com.miss.api.service;

import com.miss.api.auth.entity.User;
import com.miss.api.auth.repository.UserRepository;
import com.miss.api.model.Comite;
import com.miss.api.repos.ComiteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ComiteService {
    final ComiteRepository comiteRepository;
    final UserRepository userRepository;
    final UploadImageService uploadImageService;
    Map<String, Object> response = new HashMap<>();
    private JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;

    public ComiteService(ComiteRepository comiteRepository, UserRepository userRepository, UploadImageService uploadImageService, JavaMailSender javaMailSender, PasswordEncoder passwordEncoder) {
        this.comiteRepository = comiteRepository;
        this.userRepository = userRepository;
        this.uploadImageService = uploadImageService;
        this.javaMailSender = javaMailSender;
        this.passwordEncoder = passwordEncoder;
    }

    public void sendEmailWithAttachment(Comite comite) {
        try {
            System.out.println(comite.toString());
            MimeMessage msg = javaMailSender.createMimeMessage();

            // true = multipart message
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
            helper.setTo(comite.getMail());

            helper.setSubject("Inscription au back-office de MISS SCIENCES.");

            // default = text/plain
            //helper.setText("Check attachment for image!");

            helper.setText("<p>Bonjour " + comite.getPrenom() + " " + comite.getPrenom() + ", </p>\n" +
                    "<p>Votre inscription est prise en charge, vous trouverrez ci-joint votre mot de passe que vous devez modifier dès votre première connexion. </p>\n" +
                    "Votre Mot de passe est le suivant: ", true);
            javaMailSender.send(msg);

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }


    public ResponseEntity<Map<String, Object>> saveComite(Comite comite, MultipartFile photo) {
        try {
            User user = new User();
            user.setPassword(passwordEncoder.encode(comite.getTelephone()));
            user.setUsername(comite.getMail());
            user.setDateCreation(new Date());
            user.setDateModification(new Date());
            user.setAdmin(false);
            user.setActive(true);

            User user1 = userRepository.save(user);
            comite.setUser(user1);
            comite.setPhoto(uploadImageService.uploadImage(photo));

            Comite comite1 = comiteRepository.save(comite);
            response.put("message", "Comite enregistrée avec succès.");
            response.put("response", comite1);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de l'école échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> updateWithPhoto(Comite comite, MultipartFile photo) {
        try {
            Optional<Comite> comiteData = comiteRepository.findById(comite.getId());
            comite.setPhoto(uploadImageService.updateUploadImage(photo, comite.getPhoto()));


            if(comiteData.isPresent()) {
                Comite comite1 = comiteRepository.save(comite);
                response.put("message", "Comite mise à jour avec succès.");
                response.put("response", comite1);
                response.put("code", 100);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de l'école échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Map<String, Object>> updateComite(Comite comite) {
        try {
            Optional<Comite> comiteData = comiteRepository.findById(comite.getId());
            if(comiteData.isPresent()) {
                comiteRepository.save(comite);
                response.put("message", "Comite mise à jour avec succès.");
                response.put("response", comite);
                response.put("code", 100);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Modification échouée.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> getAllComite(int page, int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            Page<Comite> comites = comiteRepository.findAll(paging);
            response.put("message", "Comite trouvées");
            response.put("response", comites);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Comite());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> getCurrentUser(Long userId) {
        try {
            Comite comites = comiteRepository.findByUserId(userId);
            response.put("message", "Comite trouvées");
            response.put("response", comites);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Comite());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
