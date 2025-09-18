package com.firefly.domain.people.core.business.workflows;

import com.firefly.common.domain.cqrs.command.CommandBus;
import com.firefly.domain.people.core.business.commands.UpdateAddressCommand;
import com.firefly.domain.people.core.business.commands.UpdatePreferredChannelCommand;
import com.firefly.transactional.annotations.Saga;
import com.firefly.transactional.annotations.SagaStep;
import com.firefly.transactional.annotations.StepEvent;
import com.firefly.transactional.core.SagaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.firefly.domain.people.core.business.workflows.constants.RegisterCustomerConstants.*;


/**
 * Saga orchestrator for updating customer address processes.
 * 
 * This orchestrator manages the distributed transaction for updating customer addresses,
 * coordinating the address update step. The step is designed
 * to be compensatable to ensure data consistency in case of failures.
 */
@Saga(name = SAGA_SET_PREFERRED_CHANNEL_NAME)
@Service
public class SetPreferredChannelSaga {

    private final CommandBus commandBus;

    @Autowired
    public SetPreferredChannelSaga(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @SagaStep(id = STEP_UPDATE_CHANNEL)
    @StepEvent(type = EVENT_PREFERRED_CHANNEL_UPDATED)
    public Mono<Void> updateChannel(UpdatePreferredChannelCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd).then();
    }

}