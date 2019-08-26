package com.service;

import com.entity.Contact;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface ContactService {

    public List<Contact> findAll();

    public Contact createContact(Contact contact);

    public Contact findContactByName(String name);

    public Contact findContactByEmail(String email);

    public int deleteContactByEmail(String email);

    public Contact updateContact(Contact contact) throws ValidationException;
}
