package com.firefly.domain.people.core.business.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PartyProvidersApi;
import com.firefly.core.customer.sdk.model.PartyProviderDTO;
import com.firefly.domain.people.core.business.commands.RegisterPartyProviderCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterPartyProviderHandler extends CommandHandler<RegisterPartyProviderCommand, UUID> {

    private final PartyProvidersApi partyProvidersApi;

    public RegisterPartyProviderHandler(PartyProvidersApi partyProvidersApi) {
        this.partyProvidersApi = partyProvidersApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterPartyProviderCommand cmd) {

        return partyProvidersApi
                .createPartyProvider(cmd.getPartyId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(partyProviderDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(partyProviderDTO).getPartyProviderId()));
    }
}