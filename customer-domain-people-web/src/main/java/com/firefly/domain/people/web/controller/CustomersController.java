package com.firefly.domain.people.web.controller;

import com.firefly.domain.people.core.business.commands.*;
import com.firefly.domain.people.core.business.commands.UpdateCustomerCommand;
import com.firefly.domain.people.core.business.services.RegisterCustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@Tag(name = "Customers", description = "CQ queries and registration for customers")
public class CustomersController {

    private final RegisterCustomerService customerService;


    @PostMapping
    @Operation(summary = "Register a customer", description = "Registers a customer")
    public Mono<ResponseEntity<Object>> registerCustomer(@Valid @RequestBody RegisterCustomerCommand command) {
        return customerService.registerCustomer(command)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @PutMapping
    @Operation(summary = "Update customer", description = "Updates the name of an existing customer")
    public Mono<ResponseEntity<Object>> updateCustomer(
            @Valid @RequestBody UpdateCustomerCommand command) {
        return customerService.updateCustomer(command)
                .thenReturn(ResponseEntity.ok().build());
    }

}
