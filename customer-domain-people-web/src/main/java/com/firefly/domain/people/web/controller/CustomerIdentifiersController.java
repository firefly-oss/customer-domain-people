package com.firefly.domain.people.web.controller;

import com.firefly.domain.people.core.contact.commands.RegisterIdentityDocumentCommand;
import com.firefly.domain.people.core.contact.services.ContactService;
import com.firefly.domain.people.core.customer.commands.RegisterCustomerCommand;
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
@Tag(name = "Customer Identifiers", description = "CQ queries and registration for customer identifiers")
public class CustomerIdentifiersController {

    private final ContactService contactService;

    @PostMapping("/{partyId}/tax-ids")
    @Operation(summary = "Add tax id", description = "Add secondary tax ID; validate format/uniqueness")
    public Mono<ResponseEntity<Object>> addTaxId(
            @PathVariable("partyId") UUID partyId,
            @Valid @RequestBody RegisterIdentityDocumentCommand command) {
        return contactService.addIdentityDocument(partyId, command)
                .thenReturn(ResponseEntity.noContent().build());
    }

    @DeleteMapping("/{partyId}/tax-ids/{taxId}")
    @Operation(summary = "Remove tax id", description = "Remove a tax ID from the profile")
    public Mono<ResponseEntity<Object>> removeTaxId(
            @PathVariable("partyId") UUID partyId,
            @PathVariable("taxId") UUID taxId) {
        return contactService.removeIdentityDocument(partyId, taxId)
                .thenReturn(ResponseEntity.ok().build());
    }
}
