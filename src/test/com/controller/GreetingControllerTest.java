package com.controller;

import com.entity.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.ContactService;
import com.service.impl.ContactServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class GreetingControllerTest {

    Contact contact1 = new Contact(Long.parseLong("1"), "name1", "asdf", Long.parseLong("123123"));
    Contact contact2 = new Contact(Long.parseLong("2"), "name2", "diff", Long.parseLong("123123"));

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ContactService contactService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void findAllContact() throws Exception {

        List<Contact> allContacts = Arrays.asList(contact1, contact2);
        contactService.createContact(contact1);
        contactService.createContact(contact2);

        mvc.perform(get("/getAllContacts")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void saveContact() throws Exception {

        mvc.perform(post("/saveContact").content(asJsonString(contact1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void getContactByName() throws Exception {

        contactService.createContact(contact1);
        contactService.createContact(contact2);

        mvc.perform(get("/getContactByName").param("name", "name1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(equalToIgnoringCase("name1"))));

    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void getContactByEmail() throws Exception {

        contactService.createContact(contact1);
        contactService.createContact(contact2);

        mvc.perform(get("/getContactByEmail").param("email", "asdf")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.emailAddress", is(equalToIgnoringCase("asdf"))));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void deleteContact() throws Exception {

        contactService.createContact(contact1);
        contactService.createContact(contact2);

        mvc.perform(get("/deleteContact").param("email", "asdf")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1)));
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = "USER")
    public void editContact() throws Exception {

        contactService.createContact(contact1);
        contactService.createContact(contact2);

        contact1.setName("qwe");

        mvc.perform(post("/editContact").content(asJsonString(contact1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(equalToIgnoringCase("qwe"))));
    }
}