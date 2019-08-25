package com.controller;

import com.beans.Greetings;
import com.entity.Contact;
import com.entity.User;
import com.service.ContactService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    @Autowired
    private UserService userService;

    @Autowired
    private ContactService contactService;

    @RequestMapping("/testing")
    public String defaultGreeting() {
        return "hello world";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.findAll();
        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

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
        Contact newContact = contactService.findContactByName(name);
        return new ResponseEntity<Contact>(newContact, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getContactByEmail")
    public ResponseEntity<Contact> getContactByEmail(@RequestParam(value = "email") String email) {
        Contact newContact = contactService.findContactByEmail(email);
        return new ResponseEntity<Contact>(newContact, HttpStatus.OK);
    }
}
