package com.service.impl;

import com.dao.ContactDAO;
import com.entity.Contact;
import com.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactDAO contactDAO;

    @Override
    public List<Contact> findAll() {
        return (List<Contact>)this.contactDAO.findAll();
    }

    @Override
    public Contact createContact(Contact contact) {

        Contact c  = findContactByEmail(contact.getEmailAddress());
        if(null != c){
            throw new IllegalArgumentException("Email Address already exists. Please provide unique email address");
        }

        return contactDAO.save(contact);
    }

    @Override
    public Contact findContactByName(String name) {

        return contactDAO.findByName(name);
    }

    @Override
    public Contact findContactByEmail(String email) {

        return contactDAO.findByEmail(email);
    }

    @Override
    public int deleteContactByEmail(String email) {

        return contactDAO.deleteByEmail(email);
    }

    @Override
    public Contact updateContact(Contact contact) throws ValidationException {
        Contact existingContact = contactDAO.findById(contact.getId()).orElse(null);

        if(null == existingContact){
            throw new IllegalArgumentException("Contact does not exist");
        }

        existingContact.setEmailAddress(contact.getEmailAddress());
        existingContact.setName(contact.getName());
        existingContact.setPhoneNumber(contact.getPhoneNumber());

        List<Contact> contactList = contactDAO.findContactWithEmailWithoutId
                (existingContact.getId(), existingContact.getEmailAddress());
        if (null != contactList && !contactList.isEmpty()) {
            throw new ValidationException("Email Address exists");
        }

        return contactDAO.save(existingContact);
    }
}
