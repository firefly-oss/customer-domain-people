package com.firefly.domain.people.core.business.handlers;

import com.firefly.common.domain.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.domain.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.AddressesApi;
import com.firefly.core.customer.sdk.model.AddressDTO;
import com.firefly.domain.people.core.business.commands.RegisterAddressCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterAddressHandler extends CommandHandler<RegisterAddressCommand, UUID> {

    private final AddressesApi addressesApi;

    public RegisterAddressHandler(AddressesApi addressesApi) {
        this.addressesApi = addressesApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterAddressCommand cmd) {
        return addressesApi
                .createAddress(cmd.getPartyId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(addressDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(addressDTO).getAddressId()));
    }
}