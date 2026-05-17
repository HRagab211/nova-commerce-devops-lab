package com.hossam.novacommerce.product;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductDto.Response create(@Valid @RequestBody ProductDto.CreateRequest request) {
        return ProductDto.Response.from(service.create(request));
    }

    @GetMapping
    List<ProductDto.Response> list(@RequestParam Long tenantId) {
        return service.listByTenant(tenantId).stream().map(ProductDto.Response::from).toList();
    }
}
