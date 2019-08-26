package com.controller;

import com.entity.Contact;
import com.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GreetingController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(method = RequestMethod.GET, path = "/getAllContacts")
    public ResponseEntity<List<Contact>> findAllContact() {
        List<Contact> contactList = contactService.findAll();
        return new ResponseEntity<List<Contact>>(contactList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/saveContact")
    public ResponseEntity<Contact> saveContact(@Valid @RequestBody Contact contact) {
        Contact newContact = contactService.createContact(contact);
        return new ResponseEntity<Contact>(newContact, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getContactByName")
    public ResponseEntity<Contact> getContactByName(@RequestParam(value = "name") String name) {
        Contact contact = contactService.findContactByName(name);
        return new ResponseEntity<Contact>(contact, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getContactByEmail")
    public ResponseEntity<Contact> getContactByEmail(@RequestParam(value = "email") String email) {
        Contact contact = contactService.findContactByEmail(email);
        return new ResponseEntity<Contact>(contact, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/deleteContact")
    public ResponseEntity<Integer> deleteContact(@RequestParam(value = "email") String email) {
        int count = contactService.deleteContactByEmail(email);
        return new ResponseEntity<Integer>(count, HttpStatus.OK);
    }
}
