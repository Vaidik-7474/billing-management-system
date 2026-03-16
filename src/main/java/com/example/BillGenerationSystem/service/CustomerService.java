package com.example.BillGenerationSystem.service;

import com.example.BillGenerationSystem.model.Customer;
import com.example.BillGenerationSystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public void register(Customer customer) {
        customerRepository.save(customer);
    }
}
