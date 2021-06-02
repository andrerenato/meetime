package com.example.meetime.Domain.Enum;

public enum LeadStatus {

    OPEN ("OPEN"),
    WON ("WON"),
    LOST ("LOST");

    private String name;

    LeadStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
