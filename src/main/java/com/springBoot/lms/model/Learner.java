package com.springBoot.lms.model;

import jakarta.persistence.*;

@Entity
@Table(name = "learner")
public class Learner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String contact;

    @OneToOne
    private NUser user;

    public NUser getUser() {
        return user;
    }

    public void setUser(NUser user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
