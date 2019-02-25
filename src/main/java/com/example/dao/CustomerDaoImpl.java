package com.example.dao;

import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerDaoImpl implements CustomerDao {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public boolean getCheckClient(String login) {
        return customerRepository.getCheckBlackList(login);
    }

    @Override
    public void deleteCustomer(Customer сustomer) {
        customerRepository.delete(сustomer);
    }
}
