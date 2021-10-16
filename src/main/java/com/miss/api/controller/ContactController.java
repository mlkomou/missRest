package com.miss.api.controller;
import com.miss.api.model.Contact;
import com.miss.api.service.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ContactController {
    final ContactService contactService;


    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping(value = "/contacts/create")
    public ResponseEntity<Map<String, Object>> saveAca(@RequestBody Contact contact) {
        return contactService.saveContact(contact);
    }
    @GetMapping(value = "/contacts/liste")
    public ResponseEntity<Map<String, Object>> getClasses() {
        return contactService.getAllContact();
    }
}
