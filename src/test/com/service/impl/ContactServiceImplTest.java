package com.service.impl;

import com.dao.ContactDAO;
import com.entity.Contact;
import com.service.ContactService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;

@RunWith(SpringRunner.class)
public class ContactServiceImplTest {

    public List<Contact> dummyContactList;
    Contact contact1 = new Contact(Long.parseLong("1"), "some", "asdf", Long.parseLong("123123"));
    Contact contact2 = new Contact(Long.parseLong("2"), "some", "diff", Long.parseLong("123123"));

    @TestConfiguration
    static class ContactServiceImplTestContextConfiguration {

        @Bean
        public ContactService contactService() {
            return new ContactServiceImpl();
        }
    }

    @Autowired
    private ContactService contactService;

    @MockBean
    private ContactDAO contactDAO;

    @Before
    public void setUp() {
        Mockito.reset();
        dummyContactList = Arrays.asList(contact1, contact2);
    }

    @Test
    public void findAll_findAllContact() {
        List<Contact> contactList = contactService.findAll();

        Mockito.when(contactDAO.findAll())
                .thenReturn(dummyContactList);

        for (Contact contact : contactList) {
            assertThat(contact, isIn(dummyContactList));
        }
    }


    @Test
    public void findContactByName() {

        Mockito.when(contactDAO.findByName(anyString()))
                .thenReturn(contact1);

        Contact expectedContact = contactService.findContactByName(contact1.getName());
        assertThat(expectedContact.getName(), is(equalToIgnoringCase(contact1.getName())));
    }

    @Test
    public void findContactByEmail() {
        Mockito.when(contactDAO.findByEmail(anyString()))
                .thenReturn(contact1);

        Contact expectedContact = contactService.findContactByEmail(contact1.getName());
        assertThat(expectedContact.getName(), is(equalToIgnoringCase(contact1.getName())));
    }

    @Test
    public void deleteContactByEmail() {
        Mockito.when(contactDAO.deleteByEmail(anyString()))
                .thenReturn(1);

        int num = contactService.deleteContactByEmail(contact1.getName());
        assertThat(num, is(1));
    }

    @Test
    public void createContact() {
        Mockito.when(contactDAO.findByEmail(anyString()))
                .thenReturn(null);

        Mockito.when(contactDAO.save(notNull()))
                .thenReturn(contact2);

        Contact expectedContact = contactService.createContact(contact2);
        assertThat(expectedContact.getName(), is(equalToIgnoringCase(contact2.getName())));
    }


}