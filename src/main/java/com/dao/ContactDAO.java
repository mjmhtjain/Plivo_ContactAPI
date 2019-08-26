package com.dao;

import com.entity.Contact;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ContactDAO extends CrudRepository<Contact, Long> {

    @Query("Select c from Contact c where c.name = ?1")
    public Contact findByName(String name);

    @Query("Select c from Contact c where c.emailAddress = ?1")
    public Contact findByEmail(String email);

    @Transactional
    @Modifying
    @Query("delete from Contact c where c.emailAddress = ?1")
    public int deleteByEmail(String email);

    @Query("Select c from Contact c where c.emailAddress = ?2 and c.id != ?1")
    public List<Contact> findContactWithEmailWithoutId(Long id, String email);
}
