package com.miss.api.service;

import com.miss.api.model.Contact;
import com.miss.api.repos.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ContactService {
    final ContactRepository contactRepository;
    Map<String, Object> response = new HashMap<>();

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    public ResponseEntity<Map<String, Object>> saveContact(Contact contact) {
        try {
            contactRepository.save(contact);
            response.put("message", "Contacts enregistrée avec succès.");
            response.put("response", contact);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de classe échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public ResponseEntity<Map<String, Object>> updateContact(Contact contact) {
        try {
            Optional<Contact> contactData = contactRepository.findById(contact.getId());
            if(contactData.isPresent()) {
                contactRepository.save(contact);
                response.put("message", "Contacts enregistrée avec succès.");
                response.put("response", contact);
                response.put("code", 100);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Enregistrement de classe échoué.");
            response.put("response", new Object());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Map<String, Object>> getAllContact() {
        try {
            List<Contact> contacts = contactRepository.findAll();
            response.put("message", "Contacts trouvées");
            response.put("response", contacts);
            response.put("code", 100);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Erreur de recupération.");
            response.put("response", new Contact());
            response.put("code", 200);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
