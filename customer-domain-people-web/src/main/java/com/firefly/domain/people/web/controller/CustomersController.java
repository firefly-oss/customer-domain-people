package com.firefly.domain.people.web.controller;

import com.firefly.core.customer.sdk.model.PartyStatusDTO;
import com.firefly.domain.people.core.business.commands.*;
import com.firefly.domain.people.core.business.commands.UpdateCustomerCommand;
import com.firefly.domain.people.core.business.services.RegisterCustomerService;
import com.firefly.domain.people.core.business.workflows.constants.StatusTypeEnum;
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

    // Address endpoints
    @PostMapping("/{partyId}/addresses")
    @Operation(summary = "Add customer address", description = "Add an address with validity period; prevent primary overlaps.")
    public Mono<ResponseEntity<Object>> addCustomerAddress(
            @PathVariable("partyId") UUID partyId,
            @RequestBody RegisterAddressCommand addressCommand) {
        return customerService.addAddress(partyId, addressCommand)
                .thenReturn(ResponseEntity.ok().build());
    }

    @PutMapping("/{partyId}/addresses/{addressId}")
    @Operation(summary = "Update customer address", description = "Modify address fields keeping version history.")
    public Mono<ResponseEntity<Object>> updateCustomerAddress(
            @PathVariable("partyId") UUID partyId,
            @PathVariable("addressId") UUID addressId,
            @RequestBody UpdateAddressCommand addressData) {
        return customerService.updateAddress(partyId, addressId, addressData)
                .thenReturn(ResponseEntity.ok().build());
    }

    @DeleteMapping("/{partyId}/addresses/{addressId}")
    @Operation(summary = "Remove customer address", description = "Remove or end-date an address; preserve audit.")
    public Mono<ResponseEntity<Object>> removeCustomerAddress(
            @PathVariable("partyId") UUID partyId,
            @PathVariable("addressId") UUID addressId) {
        return customerService.removeAddress(partyId, addressId)
                .thenReturn(ResponseEntity.ok().build());
    }

    // Email endpoints
    @PostMapping("/{partyId}/emails")
    @Operation(summary = "Add customer email", description = "Add email; validate format and uniqueness within customer.")
    public Mono<ResponseEntity<Object>> addCustomerEmail(
            @PathVariable("partyId") UUID partyId,
            @RequestBody @Valid RegisterEmailCommand emailCommand) {
        return customerService.addEmail(partyId, emailCommand)
                .thenReturn(ResponseEntity.ok().build());
    }

    @PutMapping("/{partyId}/emails/{emailId}")
    @Operation(summary = "Update customer email", description = "Modify email fields keeping version history.")
    public Mono<ResponseEntity<Object>> updateCustomerEmail(
            @PathVariable("partyId") UUID partyId,
            @PathVariable("emailId") UUID emailId,
            @RequestBody UpdateEmailCommand emailData) {
        return customerService.updateEmail(partyId, emailId, emailData)
                .thenReturn(ResponseEntity.ok().build());
    }

    @DeleteMapping("/{partyId}/emails/{emailId}")
    @Operation(summary = "Remove customer email", description = "Remove email from profile.")
    public Mono<ResponseEntity<Object>> removeCustomerEmail(
            @PathVariable("partyId") UUID partyId,
            @PathVariable("emailId") UUID emailId) {
        return customerService.removeEmail(partyId, emailId)
                .thenReturn(ResponseEntity.ok().build());
    }

    // Phone endpoints
    @PostMapping("/{partyId}/phones")
    @Operation(summary = "Add customer phone", description = "Add phone in E.164 with label (mobile/landline).")
    public Mono<ResponseEntity<Object>> addCustomerPhone(
            @PathVariable("partyId") UUID partyId,
            @RequestBody @Valid RegisterPhoneCommand phoneCommand) {
        return customerService.addPhone(partyId, phoneCommand)
                .thenReturn(ResponseEntity.ok().build());
    }

    @PutMapping("/{partyId}/phones/{phoneId}")
    @Operation(summary = "Update customer email", description = "Modify phone fields keeping version history.")
    public Mono<ResponseEntity<Object>> updateCustomerPhone(
            @PathVariable("partyId") UUID partyId,
            @PathVariable("phoneId") UUID phoneId,
            @RequestBody UpdatePhoneCommand phoneData) {
        return customerService.updatePhone(partyId, phoneId, phoneData)
                .thenReturn(ResponseEntity.ok().build());
    }

    @DeleteMapping("/{partyId}/phones/{phoneId}")
    @Operation(summary = "Remove customer phone", description = "Remove phone from profile.")
    public Mono<ResponseEntity<Object>> removeCustomerPhone(
            @PathVariable("partyId") UUID partyId,
            @PathVariable("phoneId") UUID phoneId) {
        return customerService.removePhone(partyId, phoneId)
                .thenReturn(ResponseEntity.ok().build());
    }

    // Preferred channel endpoint
    @PostMapping("/{partyId}/preferred-channel")
    @Operation(summary = "Set preferred channel", description = "Set preferred contact channel (email/phone); one per type.")
    public Mono<ResponseEntity<Object>> setPreferredChannel(
            @PathVariable("partyId") UUID partyId,
            @RequestBody UpdatePreferredChannelCommand channelData) {
        return customerService.setPreferredChannel(partyId, channelData)
                .thenReturn(ResponseEntity.ok().build());
    }

    // Status management endpoints
    @PostMapping("/{partyId}/dormant")
    @Operation(summary = "Mark customer dormant", description = "Flag profile as dormant due to inactivity.")
    public Mono<ResponseEntity<Object>> markCustomerDormant(@PathVariable("partyId") UUID partyId) {
        return customerService.updateStatus(new UpdateStatusCommand()
                        .withPartyId(partyId)
                        .withStatusCode(PartyStatusDTO.StatusCodeEnum.INACTIVE)
                        .withReason("User has been marked as dormant due to inactivity"))
                .thenReturn(ResponseEntity.ok().build());
    }

    @PostMapping("/{partyId}/reactivate")
    @Operation(summary = "Reactivate customer", description = "Reactivate a dormant profile.")
    public Mono<ResponseEntity<Object>> reactivateCustomer(@PathVariable("partyId") UUID partyId) {
        return customerService.updateStatus(new UpdateStatusCommand()
                        .withPartyId(partyId)
                        .withStatusCode(PartyStatusDTO.StatusCodeEnum.ACTIVE)
                        .withReason("User account has been reactivated and is now fully usable"))
                .thenReturn(ResponseEntity.ok().build());
    }

    @PostMapping("/{partyId}/deceased")
    @Operation(summary = "Mark customer deceased", description = "Mark as deceased and block dependent operations.")
    public Mono<ResponseEntity<Object>> markCustomerDeceased(@PathVariable("partyId") UUID partyId) {
        return customerService.updateStatus(new UpdateStatusCommand()
                        .withPartyId(partyId)
                        .withStatusCode(PartyStatusDTO.StatusCodeEnum.CLOSED)
                        .withReason("User account is permanently closed because the user is deceased"))
                .thenReturn(ResponseEntity.ok().build());
    }

    @PostMapping("/{partyId}/closure-request")
    @Operation(summary = "Request customer closure", description = "Request customer closure once obligations are zero.")
    public Mono<ResponseEntity<Object>> requestCustomerClosure(@PathVariable("partyId") UUID partyId) {
        return customerService.updateStatus(new UpdateStatusCommand()
                        .withPartyId(partyId)
                        .withStatusCode(PartyStatusDTO.StatusCodeEnum.PENDING)
                        .withReason("A closure request has been submitted but is not yet confirmed"))
                .thenReturn(ResponseEntity.ok().build());
    }

    @PostMapping("/{partyId}/confirm-closure")
    @Operation(summary = "Confirm customer closure", description = "Confirm closure after checks pass.")
    public Mono<ResponseEntity<Object>> confirmCustomerClosure(@PathVariable("partyId") UUID partyId) {
        return customerService.updateStatus(new UpdateStatusCommand()
                        .withPartyId(partyId)
                        .withStatusCode(PartyStatusDTO.StatusCodeEnum.CLOSED)
                        .withReason("Closure has been confirmed and the account is permanently closed"))
                .thenReturn(ResponseEntity.ok().build());
    }

    @PostMapping("/{partyId}/lock")
    @Operation(summary = "Lock customer profile", description = "Lock profile for audit/investigation; block writes.")
    public Mono<ResponseEntity<Object>> lockCustomerProfile(@PathVariable("partyId") UUID partyId) {
        return customerService.updateStatus(new UpdateStatusCommand()
                        .withPartyId(partyId)
                        .withStatusCode(PartyStatusDTO.StatusCodeEnum.SUSPENDED)
                        .withReason("Profile is temporarily locked, restricting access and activity"))
                .thenReturn(ResponseEntity.ok().build());
    }

    @PostMapping("/{partyId}/unlock")
    @Operation(summary = "Unlock customer profile", description = "Unlock profile for changes.")
    public Mono<ResponseEntity<Object>> unlockCustomerProfile(@PathVariable("partyId") UUID partyId) {
        return customerService.updateStatus(new UpdateStatusCommand()
                        .withPartyId(partyId)
                        .withStatusCode(PartyStatusDTO.StatusCodeEnum.ACTIVE)
                        .withReason("Lock has been removed; user profile is restored to active status"))
                .thenReturn(ResponseEntity.ok().build());
    }

}
