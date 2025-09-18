package com.firefly.domain.people.core.business.workflows;

import com.firefly.common.domain.cqrs.command.CommandBus;
import com.firefly.common.domain.cqrs.query.QueryBus;
import com.firefly.domain.people.core.business.commands.RegisterEmailCommand;
import com.firefly.transactional.annotations.FromStep;
import com.firefly.transactional.annotations.Saga;
import com.firefly.transactional.annotations.SagaStep;
import com.firefly.transactional.annotations.StepEvent;
import com.firefly.transactional.core.SagaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

import static com.firefly.domain.people.core.business.workflows.constants.RegisterCustomerConstants.*;

/**
 * Saga orchestrator for adding email to existing customers.
 * 
 * This orchestrator manages the distributed transaction for adding emails to customers,
 * ensuring data consistency by coordinating the email registration step.
 * The step is designed to be compensatable to ensure data consistency in case of failures.
 */
@Saga(name = SAGA_ADD_EMAIL_NAME)
@Service
public class AddEmailSaga {

    private final CommandBus commandBus;

    @Autowired
    public AddEmailSaga(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @SagaStep(id = STEP_REGISTER_EMAIL)
    @StepEvent(type = EVENT_EMAIL_REGISTERED)
    public Mono<UUID> registerEmail(RegisterEmailCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd);
    }

}