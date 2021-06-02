package com.example.meetime.Domain.Request;

import java.util.List;

public class PersonRequest {

    private String name;

    private List<String> email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }
}
