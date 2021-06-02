package com.example.meetime.Service;

import com.example.meetime.Domain.Lead;
import com.example.meetime.Domain.Organization;
import com.example.meetime.Domain.Request.OrganizationRequest;
import com.example.meetime.Utils.PipedriveRestConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    @Autowired
    private PipedriveRestConnection pipedriveRestConnection;

    private List<Organization> organizationsOnMemory = new ArrayList<>();

    public Organization create(Lead lead) {
        Optional<Organization> organization = organizationsOnMemory.stream().filter(o -> o.getName().equals(lead.getCompanyName())).findAny();
        if (organization.isPresent()) {
            return organization.get();
        }
        OrganizationRequest organizationRequest = new OrganizationRequest();
        organizationRequest.setName(lead.getCompanyName());

        Organization createdOrganization = pipedriveRestConnection.post("/organizations", organizationRequest, Organization.class).getData();
        organizationsOnMemory.add(createdOrganization);
        return createdOrganization;
    }
}
