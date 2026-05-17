package com.hossam.novacommerce.shipment;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shipments")
public class ShipmentController {
    private final ShipmentService service;

    public ShipmentController(ShipmentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ShipmentDto.Response create(@Valid @RequestBody ShipmentDto.CreateRequest request) {
        return ShipmentDto.Response.from(service.create(request));
    }

    @PostMapping("/{id}/dispatch")
    ShipmentDto.Response dispatch(@PathVariable Long id) {
        return ShipmentDto.Response.from(service.dispatch(id));
    }

    @PostMapping("/{id}/deliver")
    ShipmentDto.Response deliver(@PathVariable Long id) {
        return ShipmentDto.Response.from(service.deliver(id));
    }
}
