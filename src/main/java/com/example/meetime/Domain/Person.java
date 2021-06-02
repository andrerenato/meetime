package com.example.meetime.Domain;

import java.io.Serializable;
import java.util.List;

public class Person implements Serializable {

    private static final long serialVersionUID = 7459640684641028354L;

    private Integer id;

    private String name;

    private List<Email> email;

    public Person() {
    }

    public Person(String name, List<Email> email) {
        this.name = name;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Email> getEmail() {
        return email;
    }

    public void setEmail(List<Email> email) {
        this.email = email;
    }
}
