package com.example.meetime.Domain;

import java.io.Serializable;

public class Email implements Serializable {

    private static final long serialVersionUID = -2033417263209361697L;

    private String value;

    private boolean primary;

    private String label;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isPrimary() {
        return primary;
    }

    public void setPrimary(boolean primary) {
        this.primary = primary;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
