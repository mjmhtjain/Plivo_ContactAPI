package com.dao;

import com.entity.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactDAO extends CrudRepository<Contact, Long> {

    @Query("Select c from Contact c where c.name = ?1")
    public Contact findByName(String name);

    @Query("Select c from Contact c where c.emailAddress = ?1")
    public Contact findByEmail(String email);
}
