package com.hossam.novacommerce.customer;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CustomerDto.Response create(@Valid @RequestBody CustomerDto.CreateRequest request) {
        return CustomerDto.Response.from(service.create(request));
    }

    @GetMapping
    List<CustomerDto.Response> list(@RequestParam Long tenantId) {
        return service.listByTenant(tenantId).stream().map(CustomerDto.Response::from).toList();
    }
}
