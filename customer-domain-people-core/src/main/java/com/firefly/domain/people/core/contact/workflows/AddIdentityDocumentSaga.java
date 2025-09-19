package com.firefly.domain.people.core.contact.workflows;

import com.firefly.common.domain.cqrs.command.CommandBus;
import com.firefly.domain.people.core.contact.commands.RegisterIdentityDocumentCommand;
import com.firefly.transactional.annotations.Saga;
import com.firefly.transactional.annotations.SagaStep;
import com.firefly.transactional.annotations.StepEvent;
import com.firefly.transactional.core.SagaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.firefly.domain.people.core.utils.constants.RegisterCustomerConstants.*;

/**
 * Saga orchestrator for adding identity document to existing customers.
 * 
 * This orchestrator manages the distributed transaction for adding identity documents to customers,
 * ensuring data consistency by coordinating the identity document registration step.
 * The step is designed to be compensatable to ensure data consistency in case of failures.
 */
@Saga(name = SAGA_ADD_IDENTITY_DOCUMENT_NAME)
@Service
public class AddIdentityDocumentSaga {

    private final CommandBus commandBus;

    @Autowired
    public AddIdentityDocumentSaga(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @SagaStep(id = STEP_REGISTER_IDENTITY_DOCUMENT)
    @StepEvent(type = EVENT_IDENTITY_DOCUMENT_REGISTERED)
    public Mono<UUID> registerIdentityDocument(RegisterIdentityDocumentCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd);
    }

}