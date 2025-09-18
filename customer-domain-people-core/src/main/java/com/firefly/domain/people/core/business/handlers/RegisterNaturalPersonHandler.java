package com.firefly.domain.people.core.business.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.NaturalPersonsApi;
import com.firefly.core.customer.sdk.model.NaturalPersonDTO;
import com.firefly.domain.people.core.business.commands.RegisterNaturalPersonCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterNaturalPersonHandler extends CommandHandler<RegisterNaturalPersonCommand, UUID> {

    private final NaturalPersonsApi naturalPersonsApi;

    public RegisterNaturalPersonHandler(NaturalPersonsApi naturalPersonsApi) {
        this.naturalPersonsApi = naturalPersonsApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterNaturalPersonCommand cmd) {
        return naturalPersonsApi
                .createNaturalPerson(cmd.getPartyId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(naturalPersonDTOResponseEntity ->
                        Objects.requireNonNull(Objects.requireNonNull(naturalPersonDTOResponseEntity)).getNaturalPersonId());
    }
}