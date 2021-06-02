package com.example.meetime.Controller;

import com.example.meetime.Domain.Deal;
import com.example.meetime.Domain.Response.ResponseDto;
import com.example.meetime.Service.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deals")
public class DealController {

    @Autowired
    private DealService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Deal> deals = service.findAll();
        if (deals.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(new ResponseDto(200L, deals));
    }

}
