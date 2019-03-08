package com.example.controller;

import com.example.dao.CustomerDao;
import com.example.dao.GamesStepsDao;
import com.example.dto.GameStartResponseDto;
import com.example.entity.Customer;
import com.example.entity.GamesSteps;
import com.example.service.BuildNumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


@RestController
public class ListController {

    @Autowired
    CustomerDao customerDao;
    @Autowired
    BuildNumbersService buildNumbersService;

    @GetMapping("/list")
    public ResponseEntity getList() {;
        return ResponseEntity.ok(customerDao.getAllCustomer());
    }
    @PostMapping("/delete")
    public ResponseEntity deleteCustomer(@RequestBody Customer customer) {
        customerDao.deleteCustomer(customer);
        return ResponseEntity.ok("{ \"result\":\"success\" }");
    }
    @GetMapping("/start")
    public ResponseEntity getStart() {
        GameStartResponseDto start = new GameStartResponseDto();
        start.setUuid(UUID.randomUUID().toString());
        StringBuffer sb = new StringBuffer();
        ThreadLocalRandom.current().ints(0, 10).distinct().limit(4).forEach(a->{
            sb.append(a);
        });
        Map<String,Integer> tm = new HashMap<>();
        start.setNumber(sb.toString());
        start.setUseNum(buildNumbersService.getUseNumber(sb.toString(), tm));
        return ResponseEntity.ok(start);
    }
}
