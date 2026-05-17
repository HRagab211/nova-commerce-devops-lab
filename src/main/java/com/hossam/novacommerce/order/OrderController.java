package com.hossam.novacommerce.order;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    OrderDto.Response create(@Valid @RequestBody OrderDto.CreateRequest request) {
        return OrderDto.Response.from(service.create(request));
    }

    @GetMapping("/{id}")
    OrderDto.Response get(@PathVariable Long id) {
        return OrderDto.Response.from(service.get(id));
    }

    @GetMapping
    List<OrderDto.Response> list(@RequestParam Long tenantId) {
        return service.listByTenant(tenantId).stream().map(OrderDto.Response::from).toList();
    }

    @PostMapping("/{id}/pay")
    OrderDto.Response pay(@PathVariable Long id) {
        return OrderDto.Response.from(service.pay(id));
    }
}
