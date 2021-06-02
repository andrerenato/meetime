package com.example.meetime.Controller;

import com.example.meetime.Domain.Enum.LeadStatus;
import com.example.meetime.Domain.Lead;
import com.example.meetime.Domain.Request.LeadFinishRequest;
import com.example.meetime.Domain.Response.ResponseDto;
import com.example.meetime.Service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leads")
public class LeadController {

    @Autowired
    private LeadService service;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Lead lead) {
        return ResponseEntity.ok(service.create(lead));
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Lead> leads = service.findAll();
        if (leads.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(leads);
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<?> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @GetMapping(value = "/uuid/{uuid}")
    public ResponseEntity<?> findByUuid(@PathVariable String uuid) {
        return ResponseEntity.ok(service.findByUuid(uuid));
    }

    @PostMapping(value = "/finish/uuid/{uuid}/status/{status}")
    public ResponseEntity<?> finishByUuid(@PathVariable String uuid, @PathVariable String status) {
        return ResponseEntity.ok(service.finish(uuid, LeadStatus.valueOf(status)));
    }

    @PostMapping(value = "/finish")
    public ResponseEntity<?> finish(@RequestBody LeadFinishRequest request) {
        return ResponseEntity.ok(service.finish(request.getUuid(), request.getStatus()));
    }

}
