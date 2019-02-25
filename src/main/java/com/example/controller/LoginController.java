package com.example.controller;


import com.example.dao.CustomerDao;
import com.example.dto.UserFormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    CustomerDao customerDao;

    @PostMapping("/login")
    public ResponseEntity sayHello(@RequestBody UserFormDto userForm) {
        System.out.println(customerDao.getAllCustomer().get(3).toString());
        return ResponseEntity.ok("{\"result\": " + customerDao.getCheckClient(userForm.getLogin()) + "}");
    }
}
