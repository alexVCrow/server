package com.example.dao;

import com.example.entity.Customer;

import java.util.List;

public interface CustomerDao {

    List<Customer> getAllCustomer();

    boolean getCheckClient(String login);

    void deleteCustomer(Customer customer);

}
