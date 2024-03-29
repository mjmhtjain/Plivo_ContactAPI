package com.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "contact", schema = "public")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_id_gen")
    @SequenceGenerator(name="contact_id_gen", sequenceName = "contact_id_seq")
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Invalid contact name") @NotBlank(message = "Invalid contact name")
    @NotEmpty(message = "Invalid contact name")
    @Column(name = "name")
    private String name;

    @NotNull(message = "Invalid email") @NotBlank(message = "Invalid email")
    @NotEmpty(message = "Invalid email")
    @Column(name = "emailaddress")
    private String emailAddress;

    @NotNull(message = "Invalid phone number")
    @Digits(integer=10, fraction=0, message = "Give valid 10 digit phone number")
    @Column(name = "phonenumber")
    private Long phoneNumber;

    //constructor
    public Contact(Long id, String name, String emailAddress, Long phoneNumber) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
    }

    public Contact() {
    }

    //getter setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
