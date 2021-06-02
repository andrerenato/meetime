package com.example.meetime.Service;

import com.example.meetime.Domain.Deal;
import com.example.meetime.Domain.Enum.LeadStatus;
import com.example.meetime.Domain.Lead;
import com.example.meetime.Domain.Organization;
import com.example.meetime.Domain.Person;
import com.example.meetime.Domain.Request.DealRequest;
import com.example.meetime.Utils.PipedriveRestConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DealService {

    @Autowired
    private PipedriveRestConnection pipedriveRestConnection;

    @Autowired
    private PersonService personService;

    @Autowired
    private OrganizationService organizationService;

    private List<Deal> dealsOnMemory = new ArrayList<>();

    public void create(Lead lead) {
        if (!LeadStatus.WON.equals(lead.getStatus())) {
            return;
        }

        Person person = personService.create(lead);
        Organization organization = organizationService.create(lead);

        DealRequest request = new DealRequest();
        request.setTitle(organization.getName());
        request.setPerson_id(person.getId());
        request.setOrg_id(organization.getId());

        Deal createdDeal = pipedriveRestConnection.post("/deals", request, Deal.class).getData();
        dealsOnMemory.add(createdDeal);
    }

    public List<Deal> findAll() {
        return dealsOnMemory;
    }
}
