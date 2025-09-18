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


}
