package com.firefly.domain.people.core.customer.workflows;

import com.firefly.common.domain.cqrs.command.CommandBus;
import com.firefly.domain.people.core.customer.commands.UpdateCustomerCommand;
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
 * Saga orchestrator for customer registration processes.
 * 
 * This orchestrator manages the distributed transaction for registering customers,
 * coordinating multiple steps including party creation, person details registration,
 * contact information setup, and relationship establishment. Each step is designed
 * to be compensatable to ensure data consistency in case of failures.
 * 
 * The orchestrator handles both natural persons and legal entities, with conditional
 * logic to process only relevant information based on the customer type.
 */
@Saga(name = SAGA_UPDATE_LEGAL_NAME_NAME)
@Service
public class UpdateCustomerNameSaga {

    private final CommandBus commandBus;

    @Autowired
    public UpdateCustomerNameSaga(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @SagaStep(id = STEP_UPDATE_CUSTOMER_NAME)
    @StepEvent(type = EVENT_CUSTOMER_NAME_CHANGED)
    public Mono<UUID> updateName(UpdateCustomerCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd);
    }


}
