package com.service;

import com.entity.Contact;

import java.util.List;

public interface ContactService {

    public List<Contact> findAll();

    public Contact createContact(Contact contact);

    public Contact findContactByName(String name);

    public Contact findContactByEmail(String email);

    public int deleteContactByEmail(String email);
}
