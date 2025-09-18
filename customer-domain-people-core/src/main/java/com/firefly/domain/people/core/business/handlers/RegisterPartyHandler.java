package com.firefly.domain.people.core.business.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.common.domain.cqrs.command.ContextAwareCommandHandler;
import com.firefly.common.domain.cqrs.context.ExecutionContext;
import com.firefly.core.customer.sdk.api.PartiesApi;
import com.firefly.domain.people.core.business.commands.RegisterPartyCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterPartyHandler extends CommandHandler<RegisterPartyCommand, UUID> {

    private final PartiesApi partiesApi;

    public RegisterPartyHandler(PartiesApi partiesApi) {
        this.partiesApi = partiesApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterPartyCommand cmd) {

        return partiesApi
                .createParty(cmd, UUID.randomUUID().toString())
                .mapNotNull(partyDTOResponseEntity ->
                        Objects.requireNonNull(Objects.requireNonNull(partyDTOResponseEntity).getPartyId()));
    }
}
