package com.firefly.domain.people.core.business.services;

import com.firefly.domain.people.core.business.commands.RegisterBusinessCommand;
import com.firefly.domain.people.core.business.commands.UpdateBusinessCommand;
import com.firefly.domain.people.core.customer.commands.RegisterCustomerCommand;
import com.firefly.domain.people.core.customer.commands.UpdateCustomerCommand;
import com.firefly.transactional.core.SagaResult;
import reactor.core.publisher.Mono;

public interface BusinessService {

    /**
     * Registers a new business and initiates the corresponding saga process.
     *
     * @param command the command containing all necessary details for registering a business,
     *                including party, legal entity, status history, identity documents,
     *                and associated information such as addresses, emails, phones,
     *                economic activities, providers, relationships, and group memberships.
     * @return a {@code Mono} emitting the result of the saga process for registering the business,
     *         which encapsulates the outcome and details of the operation.
     */
    Mono<SagaResult> registerBusiness(RegisterBusinessCommand command);
    
    /**
     * Updates an existing business based on the provided command and initiates the corresponding saga process.
     *
     * @param command the command containing the updated details for the business,
     *                including changes to party information, legal entity, status history,
     *                identity documents, and associated details such as addresses, emails,
     *                phones, economic activities, providers, relationships, and group memberships.
     * @return a {@code Mono} emitting the result of the saga process for updating the business,
     *         which encapsulates the outcome and details of the operation.
     */
    Mono<SagaResult> updateBusiness(UpdateBusinessCommand command);
}
