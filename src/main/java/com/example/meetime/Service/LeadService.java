package com.example.meetime.Service;

import com.example.meetime.Domain.Enum.LeadStatus;
import com.example.meetime.Domain.Lead;
import com.example.meetime.Domain.Response.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class LeadService {

    List<Lead> leadsOnMemory = new ArrayList<>();

    @Autowired
    private DealService dealService;

    public ResponseDto create(Lead lead) {
        List<String> errors = new ArrayList<>();

        if (!isValidEmail(lead.getEmail())) {
            errors.add("Email is invalid!");
        }

        Optional<Lead> leadWithEmail = leadsOnMemory.stream().filter(l -> l.getEmail().equals(lead.getEmail())).findAny();
        if (leadWithEmail.isPresent() && leadWithEmail.get().getStatus().equals(LeadStatus.OPEN)) {
            errors.add("Email already exists with a OPEN lead!");
        }

        if (!errors.isEmpty()) {
            return new ResponseDto(400L, errors);
        }

        if (leadWithEmail.isPresent()) {
            Lead updateLead = leadWithEmail.get();
            updateLead.setEmail(lead.getEmail());
            updateLead.setCompanyName(lead.getCompanyName());
            updateLead.setName(lead.getName());
            updateLead.setNote(lead.getNote());
            updateLead.setPhones(lead.getPhones());
            updateLead.setStatus(LeadStatus.OPEN);
            leadsOnMemory.removeIf(l -> l.getId().equals(updateLead.getId()));
            leadsOnMemory.add(updateLead);
            return new ResponseDto(200L, updateLead);
        }

        UUID uuid = UUID.randomUUID();
        lead.setId(uuid.toString());
        lead.setStatus(LeadStatus.OPEN);
        leadsOnMemory.add(lead);
        return new ResponseDto(201L, lead);
    }

    public static boolean isValidEmail(String email) {
        boolean isEmailValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailValid = true;
            }
        }
        return isEmailValid;
    }

    public List<Lead> findAll() {
        return leadsOnMemory;
    }

    public ResponseDto findByEmail(String email) {
        Optional<Lead> lead = leadsOnMemory.stream().filter(l -> l.getEmail().equals(email)).findAny();
        return lead.map(value -> new ResponseDto(200L, value)).orElseGet(() -> new ResponseDto(404L, null));
    }

    public ResponseDto findByUuid(String uuid) {
        Optional<Lead> lead = leadsOnMemory.stream().filter(l -> l.getId().equals(uuid)).findAny();
        return lead.map(value -> new ResponseDto(200L, value)).orElseGet(() -> new ResponseDto(404L, null));
    }

    public ResponseDto finish(String uuid, LeadStatus status) {
        Optional<Lead> lead = leadsOnMemory.stream().filter(l -> l.getId().equals(uuid)).findAny();

        if (lead.isPresent() && lead.get().getStatus().equals(LeadStatus.OPEN)) {
            Lead finishedLead = lead.get();
            finishedLead.setStatus(status);
            leadsOnMemory.removeIf(l -> l.getId().equals(finishedLead.getId()));
            leadsOnMemory.add(finishedLead);
            dealService.create(finishedLead);
            return new ResponseDto(200L, finishedLead);
        }
        return new ResponseDto(400L, "Lead not found with status OPEN");
    }
}
