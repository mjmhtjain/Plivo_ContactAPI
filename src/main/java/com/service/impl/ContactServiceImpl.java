package com.service.impl;

import com.dao.ContactDAO;
import com.entity.Contact;
import com.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
