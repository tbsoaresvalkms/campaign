package com.tbsoares.customer.controllers;

import com.tbsoares.campaign.models.ResponseWrapper;
import com.tbsoares.customer.models.CustomerResponseWrapper;
import com.tbsoares.customer.models.QueryParams;
import com.tbsoares.customer.resources.CustomerResource;
import com.tbsoares.customer.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerResponseWrapper create(@Valid @RequestBody CustomerResource customerResource) {
        return customerService.create(customerResource);
    }

    @PutMapping
    public ResponseWrapper register(@RequestBody QueryParams params) {
        String email = params.getEmail();
        customerService.register(email);
        return ResponseWrapper.status(HttpStatus.CREATED).message("Customer enrolled in the campaign successfully");
    }
}
