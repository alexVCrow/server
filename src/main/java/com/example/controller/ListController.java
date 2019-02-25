package com.example.controller;

import com.example.dao.CustomerDao;
import com.example.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ListController {

    @Autowired
    CustomerDao customerDao;

    @GetMapping("/list")
    public ResponseEntity getList() {
        return ResponseEntity.ok(customerDao.getAllCustomer());
    }
    @PostMapping("/delete")
    public ResponseEntity deleteCustomer(@RequestBody Customer customer) {
        customerDao.deleteCustomer(customer);
        return ResponseEntity.ok("{ \"result\":\"success\" }");
    }
}
