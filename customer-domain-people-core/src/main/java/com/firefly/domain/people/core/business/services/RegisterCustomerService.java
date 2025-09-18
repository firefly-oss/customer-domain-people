package com.firefly.domain.people.core.business.services;

import com.firefly.domain.people.core.business.commands.*;
import com.firefly.transactional.core.SagaResult;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface RegisterCustomerService {
    /**
     * Registers a new customer using the provided registration command.
     * This operation is orchestrated as a saga to ensure data consistency across multiple steps.
     *
     * @param command the registration command containing all customer information
     * @return a Mono containing the saga execution result
     */
    Mono<SagaResult> registerCustomer(RegisterCustomerCommand command);


    /**
     * Updates the information of an existing customer based on the provided update command.
     * This operation is part of a saga to ensure data consistency across multiple related systems.
     *
     * @param command the command containing the updated customer details
     * @return a Mono containing the result of the saga execution
     */
    Mono<SagaResult> updateCustomer(UpdateCustomerCommand command);

    /**
     * Adds a new address to an existing customer.
     * This operation is orchestrated as a saga to ensure data consistency.
     *
     * @param partyId the ID of the party/customer to add the address to
     * @param addressCommand the command containing the address information
     * @return a Mono containing the saga execution result
     */
    Mono<SagaResult> addAddress(UUID partyId, RegisterAddressCommand addressCommand);

    /**
     * Updates an existing address for a given customer. The operation modifies
     * the specified address fields while maintaining version history to ensure data consistency.
     *
     * @param partyId the ID of the customer/party whose address is being updated
     * @param addressId the ID of the address to be updated
     * @param addressData the data of the address to update, encapsulated in an UpdateAddressCommand
     * @return a Mono of Void, completing when the address update is successful
     */
    Mono<Void> updateAddress(UUID partyId, UUID addressId, UpdateAddressCommand addressData);

    /**
     * Removes an address for a specified customer. This operation can either remove
     * the address entirely or mark it with an end-date, preserving audit and historical data.
     *
     * @param partyId the unique identifier of the customer/party owning the address
     * @param addressId the unique identifier of the address to be removed
     * @return a Mono containing the saga execution result, indicating success or failure of the operation
     */
    Mono<SagaResult> removeAddress(UUID partyId, UUID addressId);

    /**
     * Adds a new email to an existing customer.
     * This operation is orchestrated as a saga to ensure data consistency.
     *
     * @param partyId the ID of the party/customer to add the email to
     * @param emailCommand the command containing the email information
     * @return a Mono containing the saga execution result
     */
    Mono<SagaResult> addEmail(UUID partyId, RegisterEmailCommand emailCommand);

    /**
     * Updates an existing email for a given customer. The operation modifies
     * the specified email fields while maintaining version history to ensure data consistency.
     *
     * @param partyId the ID of the customer/party whose email is being updated
     * @param emailId the ID of the email to be updated
     * @param emailData the data of the email to update, encapsulated in an UpdateEmailCommand
     * @return a Mono of Void, completing when the email update is successful
     */
    Mono<Void> updateEmail(UUID partyId, UUID emailId, UpdateEmailCommand emailData);

    /**
     * Removes an email for a specified customer. This operation can either remove
     * the email entirely or mark it with an end-date, preserving audit and historical data.
     *
     * @param partyId the unique identifier of the customer/party owning the email
     * @param emailId the unique identifier of the email to be removed
     * @return a Mono containing the saga execution result, indicating success or failure of the operation
     */
    Mono<SagaResult> removeEmail(UUID partyId, UUID emailId);

}
