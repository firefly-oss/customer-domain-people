package com.firefly.domain.people.core.party.services;

import com.firefly.domain.people.core.customer.commands.RegisterCustomerCommand;
import com.firefly.domain.people.core.customer.commands.UpdateCustomerCommand;
import com.firefly.domain.people.core.party.commands.RegisterPartyRelationshipCommand;
import com.firefly.domain.people.core.party.commands.UpdatePartyRelationshipCommand;
import com.firefly.transactional.core.SagaResult;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface PartyService {

    /**
     * Registers a new party relationship based on the details provided in the command.
     *
     * @param command the {@link RegisterPartyRelationshipCommand} containing the details of the party relationship to be registered
     * @return a {@link Mono} emitting the result of the saga execution, encapsulated in a {@link SagaResult}
     */
    Mono<SagaResult> registerPartyRelationship(RegisterPartyRelationshipCommand command);
    
    /**
     * Updates an existing party relationship based on the details provided in the command.
     *
     * @param command the {@link UpdatePartyRelationshipCommand} containing the updated details of the party relationship
     * @param relationId the unique identifier of the relationship
     * @return a {@link Mono} emitting the result of the saga execution, encapsulated in a {@link SagaResult}
     */
    Mono<SagaResult> updatePartyRelationship(UpdatePartyRelationshipCommand command, UUID relationId);
    
    /**
     * Removes an existing party relationship.
     *
     * @param relationId the unique identifier of the relationship to be removed
     * @return a {@link Mono} emitting the result of the saga execution, encapsulated in a {@link SagaResult}
     */
    Mono<SagaResult> removePartyRelationship(UUID relationId);
}
