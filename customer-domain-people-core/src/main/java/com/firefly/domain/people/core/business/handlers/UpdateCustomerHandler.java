package com.firefly.domain.people.core.business.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.NaturalPersonsApi;
import com.firefly.core.customer.sdk.api.PartyProvidersApi;
import com.firefly.domain.people.core.business.commands.RegisterPartyProviderCommand;
import com.firefly.domain.people.core.business.commands.UpdateCustomerCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class UpdateCustomerHandler extends CommandHandler<UpdateCustomerCommand, UUID> {

    private final NaturalPersonsApi naturalPersonsApi;

    public UpdateCustomerHandler(NaturalPersonsApi naturalPersonsApi) {
        this.naturalPersonsApi = naturalPersonsApi;
    }

    @Override
    protected Mono<UUID> doHandle(UpdateCustomerCommand cmd) {
        return naturalPersonsApi.updateNaturalPerson(cmd.getPartyId(), cmd.getNaturalPersonId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(naturalPersonDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(naturalPersonDTO).getNaturalPersonId()));
    }
}