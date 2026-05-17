package com.hossam.novacommerce.tenant;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {
    private final TenantService service;

    public TenantController(TenantService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    TenantDto.Response create(@Valid @RequestBody TenantDto.CreateRequest request) {
        return TenantDto.Response.from(service.create(request));
    }

    @GetMapping
    List<TenantDto.Response> list() {
        return service.list().stream().map(TenantDto.Response::from).toList();
    }
}
