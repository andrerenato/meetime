package com.example.meetime.Domain.Request;

import com.example.meetime.Domain.Enum.LeadStatus;

public class LeadFinishRequest {

    private String uuid;

    private String email;

    private LeadStatus status;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LeadStatus getStatus() {
        return status;
    }

    public void setStatus(LeadStatus status) {
        this.status = status;
    }
}
