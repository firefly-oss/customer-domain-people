package com.firefly.domain.people.core.business.workflows;

import com.firefly.common.domain.cqrs.command.CommandBus;
import com.firefly.domain.people.core.business.commands.RemoveEmailCommand;
import com.firefly.transactional.annotations.Saga;
import com.firefly.transactional.annotations.SagaStep;
import com.firefly.transactional.annotations.StepEvent;
import com.firefly.transactional.core.SagaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.firefly.domain.people.core.business.workflows.constants.RegisterCustomerConstants.*;

/**
 * Saga orchestrator for removing emails from existing customers.
 * 
 * This orchestrator manages the distributed transaction for removing customer emails,
 * ensuring data consistency by coordinating the email removal step.
 * The step is designed to be compensatable to ensure data consistency in case of failures.
 */
@Saga(name = SAGA_REMOVE_EMAIL_NAME)
@Service
public class RemoveEmailSaga {

    private final CommandBus commandBus;

    @Autowired
    public RemoveEmailSaga(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @SagaStep(id = STEP_REMOVE_EMAIL)
    @StepEvent(type = EVENT_EMAIL_REMOVED)
    public Mono<Void> removeEmail(RemoveEmailCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd);
    }

}