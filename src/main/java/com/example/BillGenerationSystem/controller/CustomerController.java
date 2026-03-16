package com.example.BillGenerationSystem.controller;

import com.example.BillGenerationSystem.DTO.OrderRequest;
import com.example.BillGenerationSystem.DTO.OrderResponse;
import com.example.BillGenerationSystem.model.Customer;
import com.example.BillGenerationSystem.service.CustomerService;
import com.example.BillGenerationSystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Autowired
    OrderService orderService;

    @PostMapping("register")
    public String register(@RequestBody Customer customer) {
        customerService.register(customer);
        return "registration successful";
    }

    @PostMapping("place-order")
    public OrderResponse placeOrder(@RequestBody OrderRequest request) {
        return orderService.placeOrder(request);
    }
}
