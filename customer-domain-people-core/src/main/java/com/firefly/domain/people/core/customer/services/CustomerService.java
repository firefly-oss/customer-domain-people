package com.firefly.domain.people.core.customer.services;

import com.firefly.domain.people.core.customer.commands.RegisterCustomerCommand;
import com.firefly.domain.people.core.customer.commands.UpdateCustomerCommand;
import com.firefly.transactional.core.SagaResult;
import reactor.core.publisher.Mono;

public interface CustomerService {
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
