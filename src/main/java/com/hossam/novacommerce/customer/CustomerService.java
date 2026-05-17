package com.hossam.novacommerce.customer;

import com.hossam.novacommerce.common.NotFoundException;
import com.hossam.novacommerce.tenant.TenantService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository customers;
    private final TenantService tenantService;

    public CustomerService(CustomerRepository customers, TenantService tenantService) {
        this.customers = customers;
        this.tenantService = tenantService;
    }

    @Transactional
    public Customer create(CustomerDto.CreateRequest request) {
        tenantService.get(request.tenantId());
        return customers.save(new Customer(request.tenantId(), request.name(), request.email()));
    }

    @Transactional(readOnly = true)
    public Customer get(Long id) {
        return customers.findById(id).orElseThrow(() -> new NotFoundException("Customer not found: " + id));
    }

    @Transactional(readOnly = true)
    public List<Customer> listByTenant(Long tenantId) {
        tenantService.get(tenantId);
        return customers.findByTenantId(tenantId);
    }
}
